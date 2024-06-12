package com.example.challenge.presentation.view.compose

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.challenge.presentation.viewmodel.VideoPlayerViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoPlayerScreen(
    videoUrl: String,
    viewModel: VideoPlayerViewModel
) {
    // Obtém o contexto
    val context = LocalContext.current

    // Lembre-se do ExoPlayer e inicializa-o
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
            prepare()
            playWhenReady = true
        }
    }

    // Interface do Usuário
    Scaffold {
        // Usamos um Container genérico em vez de LazyColumn para apenas um vídeo
        VideoPlayer(
            videoUrl = videoUrl,
            exoPlayer = exoPlayer,
            viewModel = viewModel,
            modifier = Modifier.fillMaxSize().padding(it)
        )
    }

    // Libera o ExoPlayer quando o Composable for removido
    DisposableEffect(key1 = exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@Composable
fun VideoPlayer(
    videoUrl: String,
    exoPlayer: ExoPlayer,
    viewModel: VideoPlayerViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Atualiza a posição do player periodicamente no ViewModel
    LaunchedEffect(exoPlayer) {
        snapshotFlow { exoPlayer.currentPosition }
            .collect { position ->
                viewModel.updatePlayerPosition(position)
            }
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewVideoPlayer() {
    //VideoPlayerScreen("string")
}
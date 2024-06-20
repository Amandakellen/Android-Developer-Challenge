package com.example.challenge.presentation.view.compose

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.challenge.helper.ExoPlayerHelper
import com.google.android.exoplayer2.ui.PlayerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayerScreen(
    videoUrl: String?,
    isPlaying: Boolean,
    currentPlayerPosition: Long,
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
) {
    val context = LocalContext.current
    val exoPlayer = remember(videoUrl) {
        videoUrl?.let {
            ExoPlayerHelper.createExoPlayer(context, it)
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer?.release()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Video Player") }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                videoUrl?.let {
                    AndroidView(
                        factory = {
                            PlayerView(it).apply {
                                player = exoPlayer
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                    )
                } ?: run {
                    Text("Loading video URL...")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onPlay
                    ) {
                        Text(text = "Play")
                    }

                    Button(
                        onClick = onPause
                    ) {
                        Text(text = "Pause")
                    }

                    Button(
                        onClick = onReset
                    ) {
                        Text(text = "Reset")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                BasicText("Current Position: $currentPlayerPosition ms")
                BasicText("Video URL: ${videoUrl ?: "Loading..."}")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun VideoPlayerScreenPreview() {
    VideoPlayerScreen(
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
        isPlaying = false,
        currentPlayerPosition = 0L,
        onPlay = {},
        onPause = {},
        onReset = {}
    )
}

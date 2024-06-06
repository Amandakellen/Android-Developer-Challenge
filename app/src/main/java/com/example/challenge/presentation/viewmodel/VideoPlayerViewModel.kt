package com.example.challenge.presentation.viewmodel

import com.example.challenge.domain.repository.VideoURLRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VideoPlayerViewModel(private val repository: VideoURLRepository) {

    private val _videoUrl = MutableStateFlow<String?>(null)
    val videoUrl: StateFlow<String?> get() = _videoUrl

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    fun getVideoUrl() {
        _videoUrl.value = repository.getVideoURL()
    }

    fun setPlaying(playing: Boolean) {
        _isPlaying.value = playing

    }
}
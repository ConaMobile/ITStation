package com.conamobile.itstation.ui.splash

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.conamobile.itstation.R
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.imherrera.videoplayer.VideoPlayer
import com.imherrera.videoplayer.VideoPlayerControl
import com.imherrera.videoplayer.rememberVideoPlayerState

@Composable
fun SplashScreen(navController: NavController, activity: Activity) {
    Surface(modifier = Modifier
        .fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.ic_its_logo),
            contentDescription = null,
            alignment = Alignment.Center)
    }
}


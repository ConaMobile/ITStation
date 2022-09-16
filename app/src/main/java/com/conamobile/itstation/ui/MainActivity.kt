package com.conamobile.itstation.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.conamobile.itstation.core.utils.NetworkHelper
import com.conamobile.itstation.ui.utils.navigation.Navigation
import com.conamobile.itstation.ui.utils.theme.Theme
import com.conamobile.itstation.ui.utils.theme.Yellow
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            statusBarTransparent(systemUiController)
            val networkHelper = NetworkHelper(this)
            Theme {
                if (networkHelper.isNetworkConnected()) {
                    Navigation(this)
                } else {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Internet not connected")
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(onClick = {
                                if (networkHelper.isNetworkConnected()) {
                                    val intent = intent
                                    overridePendingTransition(0, 0)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                    finish()
                                    overridePendingTransition(0, 0)
                                    startActivity(intent)
                                }
                            }) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun statusBarTransparent(systemUiController: SystemUiController) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            systemUiController.setStatusBarColor(color = Yellow)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}
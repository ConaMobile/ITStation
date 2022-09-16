package com.conamobile.itstation.ui.main

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.conamobile.itstation.ui.main.models.Models
import com.conamobile.itstation.ui.main.vm.MainViewModel
import com.conamobile.itstation.ui.utils.theme.Yellow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val state = rememberLazyListState()
    val scrollToTopLazyColumn by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val list = ArrayList<Models>()
    val viewModel = hiltViewModel<MainViewModel>()
    var imageIsLoading by remember {
        mutableStateOf(false)
    }
    for (i in 0..20) list.add(Models("name $i", "number $i"))
    val imageList by viewModel.itStationImages.observeAsState(emptyList())
    if (imageList.isNotEmpty()) imageIsLoading = true

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = state) {
            item {
                Box(modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()) {
                    if (imageList.isNotEmpty()) {
                        HorizontalPager(count = imageList.size,
                            state = pagerState,
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth()) { page ->
                            val item = imageList[page]
                            Card(Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    val pageOffset =
                                        calculateCurrentOffsetForPage(page).absoluteValue
                                    lerp(start = 0.85f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)).also { scale ->
                                        scaleX = scale
                                        scaleY = scale
                                    }
                                    alpha = lerp(start = 0.5f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f))
                                }) {
                                GlideImage(imageModel = item.it_station_image,
                                    modifier = Modifier.fillMaxSize(),
                                    requestOptions = {
                                        RequestOptions().diskCacheStrategy(
                                            DiskCacheStrategy.ALL)
                                    })
                            }

                            alwaysReturnFun {
                                scope.launch {
                                    if (imageIsLoading) {
                                        if (pagerState.currentPage != imageList.size - 1) pagerState.animateScrollToPage(
                                            pagerState.currentPage + 1)
                                        else pagerState.animateScrollToPage(0)
                                    }
                                }
                            }
                        }
                    } else {
                        CircularProgressIndicator(color = Yellow,
                            strokeWidth = 10.dp,
                            modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
            items(list.size) {
                val item = list[it]
                LazyItem(models = item)
            }
        }
    }
}

@Composable
fun LazyItem(models: Models) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp) {
        Box(modifier = Modifier.clickable {}) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(text = models.name, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = models.number, fontSize = 20.sp)
            }
        }
    }
}

fun alwaysReturnFun(delayMs: Long = 4000, onReturn: () -> Unit) {
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed(object : Runnable {
        override fun run() {
            onReturn()
            handler.postDelayed(this, delayMs)
        }
    }, 0)
}
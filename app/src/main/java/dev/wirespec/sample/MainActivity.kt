package dev.wirespec.sample

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.wirespec.sample.ui.CatView
import dev.wirespec.sample.ui.MainViewModel
import dev.wirespec.sample.ui.theme.AppTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                MainUIHandler()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainUIHandler() {
    val vm: MainViewModel = viewModel()

    MainUI(onBitmapCreated = { bitmap ->
        vm.bitmapCreated(bitmap = bitmap)
    })
}

@ExperimentalMaterialApi
@Composable
fun MainUI(onBitmapCreated: (bitmap: Bitmap?) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text("Image and text below is a bitmap", fontSize = 18.sp, modifier = Modifier.padding(bottom = 20.dp))

        AndroidView(
            factory = { context ->
                val catView = CatView(ctx = context) { bitmap ->
                    onBitmapCreated(bitmap)
                }

                catView
            })

        CatImageHandler()
    }
}

@Composable
fun CatImageHandler() {
    val vm: MainViewModel = viewModel()
    val bitmap = vm.onBitmapGenerated.observeAsState().value

    CatImage(bitmap = bitmap)
}

@Composable
fun CatImage(bitmap: Bitmap?) {
    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}
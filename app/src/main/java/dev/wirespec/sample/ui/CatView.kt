package dev.wirespec.sample.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import dev.wirespec.sample.App
import dev.wirespec.sample.R
import dev.wirespec.sample.utils.GraphicUtils

@SuppressLint("ViewConstructor")
@ExperimentalMaterialApi
class CatView(ctx: Context, onBitmapCreated: (bitmap: Bitmap?) -> Unit) : LinearLayoutCompat(ctx) {

    var bitmap: Bitmap? = null

    init {
        val view = App.context.currentActivity?.getLayoutInflater()?.inflate(R.layout.layout_cat_info, null) as View
        this.addView(view)
        val cview = view.findViewById<ComposeView>(R.id.compose_view)

        cview.setContent {
            CatInfo()
        }

        viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                bitmap = graphicUtils.createBitmapFromView(view = cview, width = 600, height = 670)
                onBitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}

@Composable
fun CatInfo() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        Text("Trixy the Cat", fontSize = 14.sp)
    }
}
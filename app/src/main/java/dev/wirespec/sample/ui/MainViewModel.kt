package dev.wirespec.sample.ui

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var _onBitmapCreated = MutableLiveData<Bitmap?>(null)
    var onBitmapGenerated: LiveData<Bitmap?> = _onBitmapCreated

    fun bitmapCreated(bitmap: Bitmap?) {
        _onBitmapCreated.value = bitmap
    }
}
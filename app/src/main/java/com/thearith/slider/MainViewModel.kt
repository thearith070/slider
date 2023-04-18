package com.thearith.slider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var currentIndex = 0

    private val _index: MutableLiveData<Int> = MutableLiveData(currentIndex)
    val index: LiveData<Int> = _index

    val list = listOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
    )

    init {
        setup()
    }

    fun setIndex(index: Int) {
        if (index > list.size) return
        currentIndex = index
        _index.value = currentIndex
    }

    private fun setup() {
        viewModelScope.launch {
            while (true) {
                delay(SLIDER_DURATION)
                if (currentIndex == (list.size - 1)) currentIndex = 0
                else currentIndex++
                _index.value = currentIndex
            }
        }
    }

    companion object {
        private const val SLIDER_DURATION = 3000L
    }
}
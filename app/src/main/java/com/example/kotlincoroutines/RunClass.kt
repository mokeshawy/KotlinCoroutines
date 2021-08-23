package com.example.kotlincoroutines

import android.util.Log
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RunClass {

    suspend fun receive(){
        val kotlinChannel = Channel<String>()
        runBlocking {
            launch {
                for(char in kotlinChannel){
                    Log.d("here",char)
                    delay(2000)
                }
            }
        }
    }
}
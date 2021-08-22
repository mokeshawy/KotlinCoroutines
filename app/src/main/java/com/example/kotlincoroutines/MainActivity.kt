package com.example.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val parentJob : Job = Job()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView( this ,R.layout.activity_main)

        // GlobalScope coroutines will work in all application when off application coroutines will off work.
        // hear will launch on DefaultDispatcher
//        GlobalScope.launch { //===> example 1
//            Log.d("fun"," current thread : ${Thread.currentThread().name}")
//            printMyTextAfterDelay("Mohamed")
//        }

        // example fro create Thread manual write.
//        GlobalScope.launch (newSingleThreadContext("Mohamed Thread")){ //===> example 1
//            Log.d("fun"," current thread : ${Thread.currentThread().name}")
//            printMyTextAfterDelay("Mohamed")
//        }


//        runBlocking {
//            printMyTextAfterDelay("Mohamed Keshawy") //===> example 2
//        }


//        runBlocking {
//            // example 3
//            printMyTextAfterDelay("Mohamed1")
//            printMyTextAfterDelay("Mohamed2")
//        }


        /* Async Await */
//        GlobalScope.launch {
//            val time = measureTimeMillis {
//                val dataUser = async { getUserFromNetwork() }
//                val localUser = async { getUserFromDatabase() }
//                if (dataUser.await() == localUser.await()) {
//                    Log.d("Here", "Equals")
//                } else {
//                    Log.d("Here", "not Equals")
//                }
//            }
//            Log.d("Here" , "${time}")
//        }


        /* job */
                    // parent
        //val parentJob : Job = Job()
            // child for parent job.
        val coroutinesScope : CoroutineScope = CoroutineScope(Dispatchers.IO+parentJob)
        coroutinesScope.launch(parentJob) {

            // child for job for child job parent.
            val child1 = launch { getUserFromNetwork() } // child1
            val child2 =  launch { getUserFromDatabase() } // child2

            // child1.join() // work on wait child1 finish all work and work delay after finish child1 all work.
            // child2.join() // delay will wait child1 and child2 finish work and work after finish all operation for it.
            // joinAll(child1 , child2) // work on finish child1 and child2 all operation and start work operation for delay.

            // child1.cancelAndJoin() // wait finish all operation for this function and cancel work.
            launch { delay(2000) }
        }
        //parentJob.cancel() //==> work on cancel for job where cancel parent job will cancel all operation for coroutines.
    }

//    suspend fun printMyTextAfterDelay( myText : String){ //===> example 1
//        delay(2000)
//        Log.d("my Fun" , myText)
//    }


//    suspend fun printMyTextAfterDelay( myText : String) { //===> example 2
//        // this will print myText on UI view using Dispatchers.IO and Dispatchers.Main
//        GlobalScope.launch(Dispatchers.IO) {
//            delay(2000)
//            withContext(Dispatchers.Main){
//                binding.tvHelloWorld.text = myText
//            }
//        }
//    }


//   suspend fun printMyTextAfterDelay( myText : String){ //===> example 3
//       GlobalScope.launch{
//           delay(2000)
//           Log.d("MainActivity",myText)
//       }
//    }


//    /* Async Await */
//    suspend fun getUserFromNetwork() : String{
//        delay(200)
//        return "Mohamed"
//    }
//
//    suspend fun getUserFromDatabase() : String{
//        delay(300)
//        return "Mohamed"
//    }


    /* Job */
    suspend fun getUserFromNetwork() : String{
        delay(200)
        return "Mohamed"
    }

    suspend fun getUserFromDatabase() : String{
        delay(300)
        return "Mohamed"
    }

    /* Job */
//    override fun onStop() { // when stop this activity will cancel any coroutines in this activit.
//        super.onStop()
//        parentJob.cancel()
//    }

}
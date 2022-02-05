package com.example.myapplication6

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.myapplication6.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        activityMainBinding.button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity,"Button Click",Toast.LENGTH_SHORT).show();
            }
        })

        activityMainBinding.imageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity,"Image Click",Toast.LENGTH_SHORT).show();
                activityMainBinding.imageView.animate().setDuration(2000).rotation(360f).start()
            }
        })

        activityMainBinding.text.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity,"Text Click",Toast.LENGTH_SHORT).show();

            }
        })
        activityMainBinding.text.setText("This is ABC text")
        activityMainBinding.text.textSize = 5.2f
        activityMainBinding.text.setTextColor(Color.BLUE)
        activityMainBinding.imageView.setImageDrawable(getDrawable(R.drawable.add_green))




        var const = Const(15)
        runBlocking {
            launch {
                Log.e("ThreadName 5.", "${Thread.currentThread().name}" + "  " + System.currentTimeMillis())// launch a new coroutine and continue
                delay(500)
                Log.e("ThreadName 6.", "${Thread.currentThread().name}" + "  " + System.currentTimeMillis())// non-blocking delay for 1 second (default time unit is ms)
                println("World!") // print after delay
                val jobnew:Job = launch {
                    try {


                    for(i in 0..100){
                        if(!isActive){
                            break
                        }
                        Log.e("i .", "$i")
                      //  delay(1)
                        yield()
                    }
                    }catch (ex:CancellationException)
                    {
                        ex.printStackTrace()
                        Log.e("Coroutine.","cancelled")
                    }finally {
                        withContext(NonCancellable){
                            delay(1)//without withcontext delay will throw exception

                        }
                    }
                }
                print("cancel coroutine delay")
                delay(1)
                print("cancel coroutine cancelled")
                jobnew.cancelAndJoin()
            }
           // delay(100)

            //Timeout
         val s:String? = withTimeoutOrNull(2000){
                try {
                    for (i in 0..500) {
                        Log.e("time", " $i")
                        delay(100)
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    Log.e("Timeout","Exeception")
                }
                finally {
                    Log.e("Timeout","Finally")
                }
             "ABC"
            }
            Log.e("Timeout","Result:"+s)
        }
       /* Log.e("ThreadName 1.","${Thread.currentThread().name}")
        GlobalScope.launch {
            mySuspend()

            Log.e("ThreadName 2","${Thread.currentThread().name}")
            runBlocking {
                launch {
                    Log.e("ThreadName 15.", "${Thread.currentThread().name}" + "  " + System.currentTimeMillis())
                }
            }
        }*/
        /*runBlocking {

            var job:Job = launch {
            Log.e("ThreadName 3.","${Thread.currentThread().name}"+ "  " +System.currentTimeMillis())// launch a new coroutine and continue
            delay(4000L)
            Log.e("ThreadName 4.","${Thread.currentThread().name}"+"  "+System.currentTimeMillis())// non-blocking delay for 1 second (default time unit is ms)
            println("World!")
            mySuspend()

// print after delay
        }

           var jobd:Deferred<Int> = async {
                Log.e("ThreadName async.","${Thread.currentThread().name}"+ "  " +System.currentTimeMillis())// l
                100
            }
            job.join()
            //jobd.join()
            var c:Int = jobd.await()
            println("Hello")

        }*/

        //Cancel
        runBlocking {
            print("cancel coroutine")
            val job:Job = launch {
                for(i in 0..100){
                    print("$i")
                    //Thread.sleep(1)
                }
                doWorld()
            }
            print("cancel coroutine delay")
            delay(15)
            print("cancel coroutine cancelled")
            job.join()
            
           // job.cancelAndJoin()
        }

    }

    suspend fun mySuspend(){
        delay(1000L)
    }
    suspend fun abc() = coroutineScope {

    }
    suspend fun doWorld() = coroutineScope { // this: CoroutineScope
        launch {
            delay(2000L)
            println("World 2")
        }
        launch {
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }
}
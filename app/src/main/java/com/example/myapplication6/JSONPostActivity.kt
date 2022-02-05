package com.example.myapplication6

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication6.databinding.JsonphotoactivityBinding
import com.example.myapplication6.databinding.JsonpostdataBinding
import com.google.gson.JsonArray
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JSONPostActivity :AppCompatActivity(){
    var postmodellist:List<PostModel>?=null
    var abcd:Int = 6
    var job:Job?=null;
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    var acquired = 0
    var  postDataAdapter = PostDataAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        var generic= generic<Int>(2);
        var b:Int = 100
        var a:Long = b.toLong()



        val jsonphotoactivityBinding = DataBindingUtil.setContentView<JsonphotoactivityBinding>(this,R.layout.jsonphotoactivity)
      //  JsonphotoactivityBinding.re
        jsonphotoactivityBinding.recycleview.layoutManager = LinearLayoutManager(this)
        jsonphotoactivityBinding.recycleview.adapter = postDataAdapter
        jsonphotoactivityBinding.Databtn.setOnClickListener(View.OnClickListener {
            view ->
            Toast.makeText(this@JSONPostActivity,"Adapter refresh",Toast.LENGTH_SHORT).show()
            postDataAdapter.notifyDataSetChanged()
            runBlocking {

                repeat(1) { // Launch 100K coroutines
                    coroutineScope.launch {
                        val resource = withTimeout(60) { // Timeout of 60 ms
                          //  delay(10) // Delay for 50 ms
                            Resource() // Acquire a resource and return it from withTimeout block
                        }
                        resource.close() // Release the resource
                    }
                }
                var def:Deferred<String> = async {

                  /*  for(i in 0..500){
                        //Log.e("async","value:"+i)

                    }*/
                    "ABC"
                }
                Log.e("Return val","Num:${acquired}")

                job = coroutineScope.launch {
                    for(i in 0..1000){
                        Log.e("job","jobvalue:"+i)
                        jsonphotoactivityBinding.value.setText(i.toString())
                      //  jsonphotoactivityBinding.value.setText(i.toString())
                       // delay(10)
                       // withContext(Dispatchers.IO){
                           /* withContext(Dispatchers.Main){
                                jsonphotoactivityBinding.value.setText(i.toString())
                            }*/
                        //}

                        Log.e("job","jobvalue:"+i)
                    }
                }
                job?.invokeOnCompletion {
                    it.run {
                        if(!this@JSONPostActivity.isDestroyed) {
                            HandlerCompat.createAsync(Looper.getMainLooper()!!).post(Runnable {
                                Toast.makeText(this@JSONPostActivity, "Work finish", Toast.LENGTH_LONG).show()
                            })

                            Log.e("job", "job invokeOnCompletion:" + job?.isCompleted)
                        }
                    }
                }

                Log.e("job","job isActive:"+job?.isActive)
                Log.e("job","job isCancelled:"+job?.isCancelled)
                Log.e("job","job isCompleted:"+job?.isCompleted)
               // job?.cancelAndJoin()
            }

        })
       /* jsonphotoactivityBinding.Databtn.setOnClickListener { View.OnClickListener{

            Toast.makeText(this@JSONPostActivity,"Adapter refresh",Toast.LENGTH_SHORT).show()
            postDataAdapter.notifyDataSetChanged()
        } }*/
       /* jsonphotoactivityBinding.Databtn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@JSONPostActivity,"Adapter refresh",Toast.LENGTH_SHORT).show()
                postDataAdapter.notifyDataSetChanged()
            }
        })*/
        jsonphotoactivityBinding.button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                RetrofitClass.getApiInterface().getpostsArray().enqueue(object : Callback<List<PostModel>>{
                    override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<List<PostModel>>, response: Response<List<PostModel>>) {
                         postmodellist = response.body()!!

                        Toast.makeText(this@JSONPostActivity,"Data received",Toast.LENGTH_SHORT).show()
                    }
                })


            }
        })


    }
    inner class Resource {
        init { acquired++ } // Acquire the resource
        fun close() { acquired-- } // Release the resource
    }
    suspend fun des(){
       /* if(job?.isActive == true){
            Log.e("destry","onDestroy 11")
            job?.cancel()
            Log.e("destry","cancel 22")
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("destry","onDestroy")
        if(coroutineScope.isActive){
            coroutineScope.cancel()
        }
        Log.e("destry"," 33")
    }

    override fun onStart() {
        super.onStart()
        var test:TempCalss = TempCalss()
    }

    override fun onPause() {
        super.onPause()
        Log.e("onStop","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("onStop","onDestroy")


    }
    fun MutableList<String>.swap( i:Int, f:Int){
        var temp = this[i];
        this[f] = this[i]
        this[i] = temp

    }

    override fun onResume() {
        super.onResume()

    }



    inner class PostDataAdapter : RecyclerView.Adapter<PostDataAdapter.PostViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder{

        val jsonpostdataBinding:JsonpostdataBinding = DataBindingUtil.inflate<JsonpostdataBinding>(LayoutInflater.from(parent.context),R.layout.jsonpostdata,parent,false)
       return PostViewHolder(jsonpostdataBinding)

        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
          //  holder.jsonpostdataBinding.textBody =
            holder.jsonpostdataBinding.setVariable(BR.postmodel,postmodellist?.get(position))
           // holder.jsonpostdataBinding.textBody.setText(postmodellist?.get(position)?.body)
         //   holder.jsonpostdataBinding.textId.setText(postmodellist?.get(position)?.id.toString())
           // holder.jsonpostdataBinding.textTitle.setText(postmodellist?.get(position)?.title)
        }

        override fun getItemCount(): Int {
            return postmodellist?.size?:0
        }

        inner  class PostViewHolder(binding: JsonpostdataBinding) : RecyclerView.ViewHolder(binding.root) {
            val jsonpostdataBinding = binding
            init {
                // binding.setVariable()
            }


        }

    }

    fun PostDataAdapter.getval():Int{
        return postDataAdapter.varrr!!
    }

    var PostDataAdapter.varrr: Int?
        get() = postmodellist?.size
        set(value) = TODO()
}
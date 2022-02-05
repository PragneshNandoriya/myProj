package com.example.myapplication6

import android.app.Person
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiActivity:AppCompatActivity() {

    data class Person(var age:Int,var name:String="",var addr:String="")
    private fun getlist():ArrayList<String>{
       var arr=  ArrayList<String>()
        arr.add("AA")
        arr.add("BB")
        arr.add("CC")
        return arr
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var list = arrayOf("A0","A1","A2")
        for (s in list){
            //Log.e("List","val:"+s)
        }
        var person =  Person(4).apply {
            name = "ABC"
            addr = "XXXX"
        }
        Log.e("Person 1","Name:${person.name} Age:${person.age} Add:${person.addr}")
        var  personB = person.apply {
            this.addr = "ddd"
            this.age = 100
        }
        Log.e("Person 2 ","Name:${person.name} Age:${person.age} Add:${person.addr}")
        var person1 = Person(10).also {
            it.name = "XXXXX"
            it.addr ="YYYY"
        }
        Log.e("person1","Name:${person1.name} Age:${person1.age} Add:${person1.addr}")
        Log.e("Person","Name:${person.name} Age:${person.age} Add:${person.addr}")
        var r1 = person.addr.let {
            Log.e("Length","ABC ${it}")
             it+ "This is home"
        }
        Log.e("Return","val:"+r1)
        var person33:Person= person.age.run {
            Log.e("Person","value age is ${this}")
            person.age = 99
            person.name = "Mr.Mack"
            person
        }
        Log.e("Return","Run: Name:${person33.name} Age:${person33.age}")
        Log.e("Person","Name:${person.name} Age:${person.age} Add:${person.addr}")
        with(person){
            this.age = 10+10
            this.name = "ANCDERFTTT"
            this.addr = "This is home address"
        }
        Log.e("Person","Modify : Name:${person.name} Age:${person.age} Add:${person.addr}")
        /*for (s in getlist()){
            Log.e("ListAAA","val:"+s)
        }*/

        var customlist = arrayOf("ABC",1,45.0,"XyZ",true)
        customlist.forEach {
            when(it){
                is String -> println("This is string ${it}")
                is Double -> println("This is double ${it}")
                is Int -> println("This is Int ${it}")
                is Boolean -> println("This is boolean ${it}")
            }
        }

        var map1 = mapOf<Int,String>(1 to "ABC",2 to "DEF",3 to "HIJ")
        for(keys in map1.keys){
            Log.e("MapData","MapData ${map1.get(keys)}")
        }

        var testmap = mutableMapOf<Int,String>()
        testmap.put(1,"1000")
        testmap.put(2,"2000")
        for(valuse in map1.values){
            Log.e("MapVal"," val :"+valuse)
        }

        var listset = mutableSetOf<String>()
        listset.add("AAA")
        listset.add("BBBB")
        listset.add("CCCCC")
        for(s in listset){
            println(" This is set data ${s}")
        }

        var listA = byteArrayOf(0x55,55,55)
        var listB  = charArrayOf('a','c')
        var listC = doubleArrayOf(4.0,5.0,55.0)
        var listD = longArrayOf(55,565565656665)
       /* RetrofitClass.getApiInterface().getposts().enqueue(object :Callback<JsonArray>{
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.e("RetrofitClass","onFailure:")
                // TODO("Not yet implemented")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                Log.e("RetrofitClass","onResponse:")
                // TODO("Not yet implemented")
               /// var s:String = response.body().toString()
                var jsonObject: JsonArray? = response.body()!!
               (0..(jsonObject?.size()!!-1)).forEach{
                   i ->var  objectnew = jsonObject.get(i) as JsonObject
                   Log.e("id","id:"+objectnew.get("id"))
                   Log.e("title","title:"+objectnew.get("title"))
                   Log.e("body","body:"+objectnew.get("body"))
               }
               
                }
        })*/
    }


}
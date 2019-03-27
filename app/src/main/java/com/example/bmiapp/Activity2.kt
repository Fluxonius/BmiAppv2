package com.example.bmiapp


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

         /* val sharedPreference:SharedPreference=SharedPreference(this)
        val users:ArrayList<String> = ArrayList()
                val names = sharedPreference.getValueStringList("sNames",intent.getStringArrayListExtra("imie"))
               // names.union(intent.getStringArrayListExtra("imie"))
                val butts = sharedPreference.getValueStringList("sButts",intent.getStringArrayListExtra("wynik"))
              //  butts.union(intent.getStringArrayListExtra("wynik"))
             //   val colors = sharedPreference.getValueStringList("sColors",intent.getIntegerArrayListExtra("color"))
               // colors.union(intent.getStringArrayListExtra("color"))
                val dates = sharedPreference.getValueStringList("sDates",intent.getStringArrayListExtra("dates"))
                //dates.union(intent.getStringArrayListExtra("dates"))
        val andrei=12
                val last10:Int
        if (names.size-10<0){
       last10=0} else last10=names.size-10
         val names10=names.subList(last10,names.size)
         val butts10=butts.subList(last10,names.size)
        // val colors10=colors.subList(last10,names.size)
         val dates10=dates.subList(last10,names.size)

 sharedPreference.save("sNames",names10)
         sharedPreference.save("sButts",butts10)
     //   sharedPreference.save("sColors",colors10.toString())
         sharedPreference.save("sDates",dates10)
*/
       val users:ArrayList<String> = ArrayList()
        val butts:ArrayList<String> = intent.getStringArrayListExtra("wynik")
        val names:ArrayList<String> = intent.getStringArrayListExtra("imie")
        val colors:ArrayList<Int> = intent.getIntegerArrayListExtra("color")
       val dates: ArrayList<String> = intent.getStringArrayListExtra("dates")
        val last10:Int

        if (names.size-10<0){
       last10=0} else last10=names.size-10


        val names10=names.subList(last10,names.size)
        val butts10=butts.subList(last10,names.size)
        val colors10=colors.subList(last10,names.size)
        val dates10=dates.subList(last10,names.size)

        // Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        for (i in 1..butts10.size){
            users.add("$i")

        }

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=UsersAdapter(users,butts10,names10,colors10,dates10)



    }


}

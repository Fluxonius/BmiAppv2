package com.example.bmiapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import com.example.bmiapp.logic.BmiForInPo
import com.example.bmiapp.logic.BmiForKgCm
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){


    var bmiList:ArrayList<String> = ArrayList()
    var nameList:ArrayList<String> = ArrayList()
    var colorList:ArrayList<Int> = ArrayList()
    var dateList:ArrayList<String> = ArrayList()
    var cat:Int= 0
    var switch:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
       if(switch)  switch=mode0()
       else switch=mode1()





        wylicz.setOnClickListener {
            if (dataPuzo.text.isNotEmpty() &&dataRost.text.isNotEmpty()  ) {
                var res:Double

               if(switch){
                var bmi = BmiForKgCm(dataPuzo.text.toString().toInt(), dataRost.text.toString().toInt())
                    res=bmi.countBmi()
                }
                else {
                    var bmi=BmiForInPo(dataPuzo.text.toString().toInt(), dataRost.text.toString().toInt())
                    res=bmi.countBmi()
                }
                details.setVisibility(VISIBLE)
                when(res){
                       in 0.0..18.49->{cat=0
                           wynik.text = String.format("%.2f",res)
                           wynik.setTextColor(Color.parseColor("#5ABCE3"))}
                    in 18.5..24.9->{
                        cat=1
                        wynik.text = String.format("%.2f",res)
                        wynik.setTextColor(Color.parseColor("#9EB33A"))
                    }
                    in 25.0..29.9->{
                        cat = 2
                        wynik.text = String.format("%.2f",res)
                        wynik.setTextColor(Color.parseColor("#F68121"))
                    }
                    in 30.0..34.9->{
                        cat = 3
                        wynik.text = String.format("%.2f",res)
                        wynik.setTextColor(Color.parseColor("#E74536"))
                    }

                    else->{
                        cat = 4
                        wynik.text = String.format("%.2f",res)
                        wynik.setTextColor(Color.parseColor("#BE3B8B"))
                    }


                }


                bmiList.add(String.format("%.2f",res))
                if (dataImie.text.isNotEmpty()){
                    nameList.add(dataImie.text.toString())
                } else nameList.add("Unknown")
                colorList.add(wynik.currentTextColor)
                dateList.add(LocalDate.now().toString())
            }
            details.setOnClickListener {
                val intentDetails=Intent(this,DetailsActivity::class.java)
                intentDetails.putExtra("wynik",wynik.text)
            intentDetails.putExtra("category",cat)
                intentDetails.putExtra("mass",dataPuzo.text)
                intentDetails.putExtra("height",dataRost.text)

                intentDetails.putExtra("color",wynik.currentTextColor)
                startActivity(intentDetails)
            }
            saveData()


        }

    }
    fun saveData(){
        val sp= getSharedPreferences("savedData", Context.MODE_PRIVATE)
        val ed=sp!!.edit()
        val gson= Gson()
        val bmiString:String=gson.toJson(bmiList)
        val nameString:String=gson.toJson(nameList)
        val colorString:String=gson.toJson(colorList)
        val dateString:String=gson.toJson(dateList)
        ed.putString("sBmi",bmiString)
        ed.putString("sName",nameString)
        ed.putString("sColor",colorString)
        ed.putString("sDate",dateString)
        ed.apply()
    }
    fun loadData(){
        val sp= getSharedPreferences("savedData", Context.MODE_PRIVATE)
        val gson= Gson()
        val bmiString:String=sp.getString("sBmi",gson.toJson(bmiList))
        val nameString:String=sp.getString("sName",gson.toJson(nameList))
        val colorString:String=sp.getString("sColor",gson.toJson(colorList))
        val dateString:String=sp.getString("sDate",gson.toJson(dateList))
        val type = object :TypeToken<ArrayList<String>>(){}.type
        val type1 = object :TypeToken<ArrayList<Int>>(){}.type
        bmiList=gson.fromJson(bmiString,type)
        nameList=gson.fromJson(nameString,type)
        colorList=gson.fromJson(colorString,type1)
        dateList=gson.fromJson(dateString,type)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_action_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_about-> {
         val intentAbout = Intent(this,About_me::class.java)
            startActivity(intentAbout)
            true

        }
       R.id.action_change -> {
            if (switch) switch=mode1()
            else switch=mode0()
            true
        }
        R.id.action_history->{

            val intentHistory = Intent(this, Activity2::class.java)
            intentHistory.putExtra("wynik",bmiList)
            intentHistory.putExtra("imie",nameList)
            intentHistory.putExtra("color",colorList)
            intentHistory.putExtra("dates",dateList)


            startActivity(intentHistory)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }
   fun mode0():Boolean{
       rost.text=getText(R.string.bmi_main_height_cm)
       puzo.text=getText(R.string.bmi_main_weight_kg)
        dataPuzo.text=null
        dataRost.text=null
        dataImie.text=null
        wynik.text=null
        details.visibility=INVISIBLE
        return true
    }
    fun  mode1():Boolean{
        rost.text=getText(R.string.bmi_main_height_in)
        puzo.text=getText(R.string.bmi_main_weight_po)
        dataPuzo.text=null
        dataRost.text=null
        dataImie.text=null
        wynik.text=null
        details.visibility=INVISIBLE
        return false
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val wynikText = savedInstanceState?.getCharSequence("savedWynik")
        val wynikColor = savedInstanceState?.getInt("savedColor")
        val namesList = savedInstanceState?.getStringArrayList("savedNamesList")
        val wynikList = savedInstanceState?.getStringArrayList("savedWynikList")
        val detButt = savedInstanceState?.getInt("savedVis")
       val colorsList= savedInstanceState?.getIntegerArrayList("savedColorsList")
        val dateSList= savedInstanceState?.getStringArrayList("dateList")


        val switched=savedInstanceState?.getBoolean("mode")
        val sdRost=savedInstanceState?.getCharSequence("savedRost")
        val sdPuzo=savedInstanceState?.getCharSequence("savedPuzo")
        val sdImie =savedInstanceState?.getCharSequence("savedImie")
       if(switched!=null){
            if (switched) switch=mode0()
            else switch=mode1()
        }
        if (dateSList!=null)
        dateList=dateSList
        dataRost.setText(sdRost)
        dataPuzo.setText(sdPuzo)
        dataImie.setText(sdImie)



        wynik.setText(wynikText)
        if(wynikColor!=null)
            wynik.setTextColor(wynikColor)
       if (colorsList!=null)
           colorList=colorsList
        if (wynikList!=null)
            bmiList=wynikList
        if(namesList!=null)
            nameList=namesList
        if(detButt!=null)
            details.visibility=detButt


    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val wynikText = wynik.text
        val wynikColor=wynik.currentTextColor
        val wynikList=bmiList
        val namesList=nameList
        val detButt=details.visibility
        val colorsList=colorList



        outState?.putStringArrayList("savedWynikList",wynikList)
        outState?.putStringArrayList("savedNamesList",namesList)
        outState?.putIntegerArrayList("savedColorsList",colorsList)
        outState?.putCharSequence("savedWynik", wynikText)
        outState?.putInt("savedColor",wynikColor)
        outState?.putInt("savedVis",detButt)
        outState?.putStringArrayList("dateList",dateList)


       outState?.putCharSequence("savedRost",dataRost.text)
        outState?.putCharSequence("savedPuzo",dataPuzo.text)
        outState?.putCharSequence("savedImie",dataImie.text)
        outState?.putBoolean("mode",switch)
    }
}

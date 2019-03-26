package com.example.bmiapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        textRost.text=("Height: "+intent.getCharSequenceExtra("height"))
        textWynik.text=intent.getCharSequenceExtra("wynik")
        textPuzo.text=("Mass: "+intent.getCharSequenceExtra("mass"))
        val colorText:Int=intent.getIntExtra("color",0)
        textWynik.setTextColor(colorText)
        val category=intent.getIntExtra("category",0)
        when(category){
            0->textInfo.text=("UNDERWEIGHT")
            1->textInfo.text=("NORMAL")
            2->textInfo.text=("OVERWEIGHT")
            3->textInfo.text=("OBESE")
            4->textInfo.text=("EXTREMLY OBESE")
        }


    }
}

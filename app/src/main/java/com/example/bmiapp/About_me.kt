package com.example.bmiapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import kotlinx.android.synthetic.main.activity_about_me.*


class About_me : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)
        internat.settings.javaScriptEnabled=true
        internat.settings.cacheMode=WebSettings.LOAD_CACHE_ELSE_NETWORK
        internat.loadUrl("https://www.meme-arsenal.com/memes/5c996e2c08496d57b6cc245acb480074.jpg")


    }
}

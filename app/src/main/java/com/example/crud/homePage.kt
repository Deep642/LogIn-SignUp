package com.example.crud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class homePage : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val tv=findViewById<TextView>(R.id.tv2)
        if((intent.getStringExtra(landingpage.KEY3)!=null)&&(intent.getStringExtra(landingpage.KEY4)!=null)){
            val name=intent.getStringExtra(landingpage.KEY3)
            var email=intent.getStringExtra(landingpage.KEY4)
            tv.text="Welcome "+name.toString()+"! your mail is "+email.toString();
        }else if((intent.getStringExtra(loginpage.KEY1)!=null)&&(intent.getStringExtra(loginpage.KEY2)!=null)){
            val name= intent.getStringExtra(loginpage.KEY1)
            var email=intent.getStringExtra(loginpage.KEY2)
            tv.text="Welcome "+name.toString()+"! your mail is "+email.toString();
        }
    }
}

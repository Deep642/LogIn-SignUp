package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class landingpage : AppCompatActivity() {

    companion object{
      const val KEY3="com.example.crud.name"
      const val KEY4="com.example.crud.mail"
    }
    lateinit var database:DatabaseReference //connecting to firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landingpage)

        val logintext=findViewById<TextView>(R.id.textView4)
        logintext.setOnClickListener{
            val intent=Intent(this,loginpage::class.java)
            startActivity(intent)
        }

        val etname=findViewById<TextInputEditText>(R.id.name)
        val etmail=findViewById<TextInputEditText>(R.id.email)
        val etuserId=findViewById<TextInputEditText>(R.id.userId)
        val etpassword=findViewById<TextInputEditText>(R.id.password)

        val signUpBtn=findViewById<Button>(R.id.signupbutton)

        signUpBtn.setOnClickListener {
            val name=etname.text.toString()
            val mail=etmail.text.toString()
            val userId=etuserId.text.toString()
            val password=etpassword.text.toString()

            val user=allUsers(name,mail,userId,password)
            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(userId).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"Signed up successfully",Toast.LENGTH_SHORT).show()
                val welcomeintent= Intent(this,homePage::class.java)
                welcomeintent.putExtra(KEY3,name)
                welcomeintent.putExtra(KEY4,mail)
                startActivity(welcomeintent)
                etname.text?.clear()
                etmail.text?.clear()
                etuserId.text?.clear()
                etpassword.text?.clear()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
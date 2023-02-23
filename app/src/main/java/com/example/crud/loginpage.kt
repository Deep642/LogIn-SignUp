package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class loginpage : AppCompatActivity() {

    companion object{
        const val KEY1="com.example.crud.package.Name"
        const val KEY2="com.example.crud.package.email"
    }

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)

        val useidinput=findViewById<TextInputEditText>(R.id.userIdlogin)
        val passwordinput=findViewById<TextInputEditText>(R.id.passwordlogin)
        val loginbtn=findViewById<Button>(R.id.buttonlogin)

        loginbtn.setOnClickListener {
            val givenId=useidinput.text.toString()
            val givenPassword=passwordinput.text.toString()
            if(givenPassword.isNotEmpty()&&givenId.isNotEmpty()){
               recovery(givenId)
                useidinput.text?.clear()
                passwordinput.text?.clear()
            }
            else{
                Toast.makeText(this,"Please fill all the details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun recovery(givenId:String){
        val passwordInput=findViewById<TextInputEditText>(R.id.passwordlogin).text.toString()
        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(givenId).get().addOnSuccessListener {
            if(it.exists()){
                val temp=it.child("password").value.toString()
                if(temp==passwordInput){
                    val Name=it.child("name").value.toString()
                    val email=it.child("mail").value.toString()
                    val welcomintent= Intent(this,homePage::class.java)
                    welcomintent.putExtra(KEY1,Name)
                    welcomintent.putExtra(KEY2,email)
                    startActivity(welcomintent)
                }else{
                    val temp=it.child("password").value.toString()
                    Toast.makeText(this,"Enter correct password", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"no user found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Database Not connected", Toast.LENGTH_SHORT).show()
        }
    }
}
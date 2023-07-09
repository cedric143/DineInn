package com.example.dineinn


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class registrationActivity : AppCompatActivity() {
    lateinit var text_gotologin:TextView
    lateinit var Edtregemail:EditText
    lateinit var Edtregpass:EditText
    lateinit var Edtrsgconfirmpass:EditText
    lateinit var Btn_SignIn:Button
    lateinit var auth:FirebaseAuth
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        text_gotologin=findViewById(R.id.txt_login)
        Edtregemail=findViewById(R.id.Edit_regemail)
        Edtregpass=findViewById(R.id.Edit_regpass)
        Edtrsgconfirmpass=findViewById(R.id.Edit_cnfmpass)
        Btn_SignIn=findViewById(R.id.btn_Register)
        auth= Firebase.auth

        Edtregpass.transformationMethod = PasswordTransformationMethod.getInstance()
        Edtrsgconfirmpass.transformationMethod = PasswordTransformationMethod.getInstance()

        text_gotologin.setOnClickListener {
            val intent=Intent(this,loginactivity::class.java)
            startActivity(intent)
        }
        Btn_SignIn.setOnClickListener {
            Signupuser()
        }

    }
    private fun Signupuser(){
        val email=Edtregemail.text.toString()
        val password=Edtregpass.text.toString()
        val confirmpassword=Edtrsgconfirmpass.text.toString()

        if (email.isBlank()||password.isBlank()||confirmpassword.isBlank()){
            Toast.makeText(this,"Password and Email can't be blank",Toast.LENGTH_LONG)
            return
        }else if (password!=confirmpassword){
            Toast.makeText(this,"password do not match",Toast.LENGTH_LONG).show()
        }
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if (it.isSuccessful){
                val intent=Intent(this,loginactivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"signed up successfully",Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,"Failed to create user",Toast.LENGTH_LONG).show()
            }
        }
    }
}
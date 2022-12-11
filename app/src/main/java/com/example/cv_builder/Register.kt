package com.example.cv_builder

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        createAccount.setOnClickListener {
            if (firstName.text.toString() == null || firstName.text.toString() == "") {
                Toast.makeText(this, "Please Enter Your First Name", Toast.LENGTH_LONG).show()
            } else if (lastName.text.toString() == null || firstName.text.toString() == "") {
                Toast.makeText(this, "Please Enter Your Last Name", Toast.LENGTH_LONG).show()
            } else if (emailAddress.text.toString() == null || emailAddress.text.toString() == "") {
                Toast.makeText(this, "Please Enter Your Email Address", Toast.LENGTH_LONG).show()
            } else if (password.text.toString() == null || password.text.toString() == "") {
                Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_LONG).show()
            } else {
                var firstNameText = firstName.text.toString()
                var lastNameText = lastName.text.toString()
                var emailAddressText = emailAddress.text.toString()
                var passwordText = password.text.toString()
                var user = User(firstNameText, lastNameText, emailAddressText, passwordText, false)

                val gson = Gson()
                val jUser = gson.toJson(user)
                val spf = getSharedPreferences("loginspf", Context.MODE_PRIVATE)
                val spe = spf.edit()
                spe.putString("user", jUser)
                spe.apply()

                finish()
            }
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}
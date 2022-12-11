package com.example.cv_builder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.view.menu.MenuBuilder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        var spf = getSharedPreferences("loginspf", 0)
        var res = spf.getString("user", "")
        var user = gson.fromJson(res, User::class.java)

        if(user!= null){
            if(user.stayLoggedIn){
                var intent = Intent(this, CV_Activity::class.java)
                intent.putExtra("username", "${user.firstName} ${user.lastName}")
                startActivity(intent)
            }
        }

        register.setOnClickListener {
            var intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {

            var email = email.text.toString()
            var password = password.text.toString()
            var stayLoggedInValue = stayLoggedIn.isChecked

            spf = getSharedPreferences("loginspf", 0)
            res = spf.getString("user", "")
            user = gson.fromJson(res, User::class.java)
            var value= user.emailAddress
            value = value.toString()
            if (email != null || email != "" || password != null || password != "") {
                if(user != null) {
                    if (user.emailAddress == email) {
                        if (user.passWord == password) {
                            var updateUser = User(user.firstName, user.lastName, user.emailAddress, user.passWord, stayLoggedInValue)
                            val updatedValues = gson.toJson(updateUser)
                            val spe = spf.edit()
                            spe.putString("user", updatedValues)
                            spe.apply()

                            var intent = Intent(this, CV_Activity::class.java)
                            intent.putExtra("username", "${user.firstName} ${user.lastName}")
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please Enter Email and Password to Login", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
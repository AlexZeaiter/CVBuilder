package com.example.cv_builder

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebViewClient
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.AppBarConfiguration
import com.example.cv_builder.databinding.ActivityCvBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_cv.*

class CV_Activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCvBinding
    lateinit var fmanager: FragmentManager
    lateinit var tx: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intt = intent // Similar to  Intent intent = getIntent()
        val output = intt.getStringExtra("username")
        setTitle(output)

        fmanager = supportFragmentManager
        tx = fmanager.beginTransaction()
        tx.add(R.id.frame1, FirstFragment())
        tx.commit()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        // Code to get the title and icon on the option overflow
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        var URL = ""
        when(item.itemId){
            R.id.fb -> URL ="http://facebook.com"
            R.id.tw -> URL ="http://twitter.com"
            R.id.gg -> URL ="http://google.com"
            R.id.li -> URL ="http://linkedin.com"
            R.id.wts -> {
                val whatsAppAppId = "com.whatsapp"
                try {
                    intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    val text = "Cv"
                    intent.`package`= whatsAppAppId
                    intent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(intent, "Finish the action with:"));

                } catch (e: PackageManager.NameNotFoundException) {

                }
            }
            R.id.logout -> {
                val gson = Gson()
                val spf = getSharedPreferences("loginspf", 0)
                val res = spf.getString("user", "")
                val user = gson.fromJson(res, User::class.java)
                var updateUser = User(user.firstName, user.lastName, user.emailAddress, user.passWord, false)
                val updatedValues = gson.toJson(updateUser)
                val spe = spf.edit()
                spe.putString("user", updatedValues)
                spe.apply()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        if(URL != "") {
            val intent = Intent(this, WebView::class.java)
            intent.putExtra("url", URL)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    fun home(view: View) {
        tx = fmanager.beginTransaction()
        tx.replace(R.id.frame1, FirstFragment())
        tx.commit()
    }
    fun education(view: View) {
        tx = fmanager.beginTransaction()
        tx.replace(R.id.frame1, SecondFragment())
        tx.commit()
    }
    fun workExperience(view: View) {
        tx = fmanager.beginTransaction()
        tx.replace(R.id.frame1, Work())
        tx.commit()
    }
    fun contactus(view: View) {
        tx = fmanager.beginTransaction()
        tx.replace(R.id.frame1, ContactUs())
        tx.commit()
    }
}
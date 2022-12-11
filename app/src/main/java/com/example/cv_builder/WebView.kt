package com.example.cv_builder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_cv.*
import kotlinx.android.synthetic.main.activity_web_view.*

class WebView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val intt = intent // Similar to  Intent intent = getIntent()
        val output = intt.getStringExtra("url")
        wview.settings.javaScriptEnabled = true;
        wview.settings.builtInZoomControls = true;

        wview.webViewClient = WebViewClient()
        wview.loadUrl(output!!)
    }
}
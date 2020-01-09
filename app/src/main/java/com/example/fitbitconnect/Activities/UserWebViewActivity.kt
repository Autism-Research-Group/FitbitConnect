package com.example.fitbitconnect.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitbitconnect.R
import com.example.fitbitconnect.SignUpWebviewClient
import kotlinx.android.synthetic.main.activity_user_webwindow.*


class UserWebViewActivity: AppCompatActivity() {

    val LOGIN_URI = Uri.parse("http://10.0.2.2:3000/auth/authorize")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_webwindow)


        val myWebView: WebView = web_view
        val settings = myWebView.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        myWebView.webViewClient = SignUpWebviewClient(this)

        web_view.loadUrl("http://10.0.2.2:3000/auth/authorize")



/*
        if(intent != null && intent.data != null && "custom-scheme".equals(intent.data!!.scheme)) {
            val code: String = intent.data!!.getQueryParameter("code") as String
            println("Still work to do.")
        } else {
            val browserIntent: Intent = Intent(Intent.ACTION_VIEW, LOGIN_URI)
            browserIntent.flags =
                Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_FROM_BACKGROUND
            startActivity(browserIntent)
            finish()

        }


        AddUser(this).execute(URL("http://10.0.2.2:3000/auth/authorize"))
*/

    }
}
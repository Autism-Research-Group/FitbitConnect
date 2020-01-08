package com.example.fitbitconnect.WebFetches

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fitbitconnect.Activities.UserWebViewActivity
import kotlinx.android.synthetic.main.activity_user_webwindow.*
import java.net.URL

class AddUser(val context: UserWebViewActivity) : FetchGetUrl<String>(context) {

    override fun onPostExecute(url: String) {
        openWebPage(url)
    }

    override fun handleResponse(data: String, url: String): String{
        return url
    }

    private fun openWebPage(url: String){
        url.replace("localhost:3000","10.0.2.2:3000")
        println(url)
        val myWebView: WebView = context.web_view
        myWebView.loadUrl(url)




        /*
        url.replace("localhost:3000","10.0.2.2:3000")
        println()
        val webpage: Uri = Uri.parse(url)

        val intent = Intent(Intent.ACTION_VIEW, webpage)
        context.startActivity(intent)

         */
    }
}
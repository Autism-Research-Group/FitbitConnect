package com.example.fitbitconnect

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.*
import kotlinx.android.synthetic.main.activity_user_webwindow.*

class SignUpWebviewClient(val activity: Activity) : WebViewClient() {

    val VALID_URLS = listOf("fitbit.com/oauth", "/auth/authorize",
        "fitbit.com/login/transferpage")


    var webIntent: Intent? = null

    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {

        val isValidURL = VALID_URLS.any { ele -> url.contains(ele) }
        Log.i("WEBVIEW", isValidURL.toString())
        Log.i("WEBVIEW", url)
        return if (isValidURL) {
            // We are at the proper site and can allow the user to view the webView
            //webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            // view!!.context.startActivity(webIntent)
            activity.web_view.loadUrl(url)
            return true
        } else {
            // The site is no longer valid so we will close the browser after we get the response
            Log.i("WEBVIEW", "Activity finished")
            //activity.finish()
            //false
            true
        }
    }
}
package com.example.fitbitconnect.WebFetches

import com.example.fitbitconnect.Activities.UserWebViewActivity

class AddUser(val context: UserWebViewActivity) : FetchGetUrl<String>(context) {

    override fun onPostExecute(url: String) {

    }

    override fun handleResponse(data: String, url: String): String{
        return url
    }

    private fun openWebPage(url: String){

        /*
        url.replace("localhost:3000","10.0.2.2:3000")
        println()
        val webpage: Uri = Uri.parse(url)

        val intent = Intent(Intent.ACTION_VIEW, webpage)
        context.startActivity(intent)

         */
    }
}
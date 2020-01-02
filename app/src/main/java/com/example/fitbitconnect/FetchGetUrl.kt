package com.example.fitbitconnect

import android.app.Activity
import android.os.AsyncTask
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

abstract class FetchGetUrl<E>(context: Activity): AsyncTask<URL, String, E>() {

    protected val activityReference: WeakReference<Activity> = WeakReference(context)

    /**
     * Creates a get HttpURLConnection for the given url
     */
    protected fun createHttpURLConnection(url: URL): HttpURLConnection {
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("User-agent", "Mozilla/5.0")
        return connection
    }

}
package com.example.fitbitconnect

import android.app.Activity
import android.os.AsyncTask
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL
import kotlin.Exception

abstract class FetchGetUrl<E>(context: Activity): IFetchGet<E>, AsyncTask<URL, String, E>() {

    protected val activityReference: WeakReference<Activity> = WeakReference(context)

    override fun doInBackground(vararg url: URL): E  {
        val httpURLConnection = createHttpURLConnection(url[0])

        if(!isValidConnectionResponseCode(httpURLConnection))
            throw Exception("Invalid server response code: ${httpURLConnection.responseCode}")

        return handleServerResponse(httpURLConnection)
    }


    // Handles the response from the server
    private fun handleServerResponse(httpURLConnection: HttpURLConnection): E {
        // The mutated data from the server to be returned
        val returnedData: E

        // Try to get the data from the body of the GET request and then process the data
        try {
            // Get the response from the server as a string and call handleData on the response
            val data: String = httpURLConnection.inputStream.bufferedReader().use{ it.readText() }
            returnedData = handleResponse(data)
        } catch (exception: Exception){
            throw Exception("There was an error parsing the data: $exception")
        } finally {
            httpURLConnection.disconnect() // disconnect from the server
        }

        return returnedData
    }


    // Determines if the responseCode is valid
    private fun isValidConnectionResponseCode(httpURLConnection: HttpURLConnection): Boolean {
        return httpURLConnection.responseCode == 200
    }


    // Creates a get HttpURLConnection for the given url
    private fun createHttpURLConnection(url: URL): HttpURLConnection {
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("User-agent", "Mozilla/5.0")
        return connection
    }
}
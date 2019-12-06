package com.example.fitbitconnect

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class FetchURL() : AsyncTask<String, Void, String>() {


    override fun doInBackground(vararg path: String): String {
        val connection = makeURLCall(path[0])
        val response = responseCode(connection)
        return response
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.i("URL_API", result)
    }

    // Creates a url connection when given a url link and method
    private fun makeURLCall(path: String): HttpURLConnection {
        val url = URL(path)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("User-agent", "Mozilla/5.0")

        return connection
    }

    // Receives the data from the server
    private fun responseCode(connection: HttpURLConnection): String {

        val responseCode: Int = connection.responseCode
        Log.i("API_URL", "The response code was: $responseCode")

        val bufferedReader: BufferedReader = BufferedReader(InputStreamReader(connection.inputStream))

        var response: String = ""
        var line: String = bufferedReader.readLine()

        while(line != null) {
            response += (line)
        }

        return response
    }
}

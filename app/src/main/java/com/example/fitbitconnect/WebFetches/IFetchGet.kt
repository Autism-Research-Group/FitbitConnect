package com.example.fitbitconnect.WebFetches

import java.net.URL

interface IFetchGet<E> {

    /**
     * Handles the json object that is returned from the url
     * - data : The body of the url response
     * - url: The url the response. Only used for redirects
     */
    fun handleResponse(data: String, url: String): E

}
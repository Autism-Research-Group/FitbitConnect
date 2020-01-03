package com.example.fitbitconnect

interface IFetchGet<E> {

    /**
     * Handles the json object that is returned from the url
     */
    fun handleResponse(data: String): E

}
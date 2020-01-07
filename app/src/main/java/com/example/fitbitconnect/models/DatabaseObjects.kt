package com.example.fitbitconnect.models


/**
 * DatabaseUser is an object that simulates how a user is stroed in the SQLite database. The object
 *   contains the following properties:
 *      - id: The primary key of the user
 *      - username: The username of the user
 *      - userID: The Fitbit user id associated with the user
 */
class DatabaseUser(val id: Int, val username: String, val userID: String) {

    /**
     * Determines if two DatabaseUser objects are identical
     */
    override fun equals(other: Any?): Boolean {
        return (other is DatabaseUser) &&
                this.id == other.id &&
                this.username == other.username &&
                this.userID == other.userID
    }


    /**
     * Determines if two Database User objects have the same username and userID
     */
    fun softEquals(other: DatabaseUser): Boolean {
        return this.username == other.username && this.userID == other.userID
    }
}

/**
 * DatabaseUserList is a custom list that extends the ArrayList class. This custom list holds a list
 *   of DatabaseUser objects and provides special equality checks to help make database checks easier
 */
class DatabaseUserList: ArrayList<DatabaseUser>(){

    /**
     * Returns true if the given DatabaseUser is in the DatabaseUserList
     */
    override fun contains(user: DatabaseUser): Boolean {
        for (i in 0 until this.size){
            val currentUser = this[i]
            if(currentUser.equals(user)){
                return true
            }
        }
        return false
    }

    /**
     * Returns the location of the the DatabaseUser in the DatabaseUserList. Will return -1 if
     *   the object is not in the list
     */
    fun getIndex(user: DatabaseUser): Int {
        for(i in 0 until this.size) {
            if(this[i].softEquals(user)){
                return i
            }
        }
        return -1
    }
}
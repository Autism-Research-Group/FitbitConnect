package com.example.fitbitconnect.Database

class DatabaseUser(val id: Int, val username: String, val userID: String) {

    override fun equals(other: Any?): Boolean {
        return (other is DatabaseUser) &&
                this.id == other.id &&
                this.username == other.username &&
                this.userID == other.userID
    }

    fun softEquals(other: DatabaseUser): Boolean {
        return this.username == other.username && this.userID == other.userID
    }
}


class DatabaseUserList: ArrayList<DatabaseUser>(){

    override fun contains(user: DatabaseUser): Boolean {
        for (i in 0 until this.size){
            val currentUser = this[i]
            if(currentUser.equals(user)){
                return true
            }
        }
        return false
    }


    fun getIndex(user: DatabaseUser): Int {
        for(i in 0 until this.size) {
            if(this[i].softEquals(user)){
                return i
            }
        }
        return -1
    }
}
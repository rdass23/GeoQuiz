package com.example.geoquiz.persistence

import com.example.geoquiz.User

object CurrentUser{
    private lateinit var user:User

    init{
        val emptyUser = User("", 0, 0)
        setUser(emptyUser)
    }

    fun getUser(): User {
        return user
    }

    fun setUser(newUser: User){
        user = newUser
    }
}
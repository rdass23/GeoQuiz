package com.example.geoquiz

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class MainRepo {
    private val db = Firebase.firestore
    suspend fun getAllUsers(): List<User> {
        return db.collection("users")
            .get().await().map { it.toObject(User::class.java) }
    }

    suspend fun getUser(userName : String): User? {
        return db.collection("users").whereEqualTo("userName",userName)
            .get().await().documents.first().toObject(User::class.java)
    }
}
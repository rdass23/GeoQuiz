package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.example.geoquiz.persistence.CurrentUser

class LoginActivity : AppCompatActivity(){
    private lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.button)
        db = Firebase.database.reference
        val thisAct = this

        button.setOnClickListener(){
            val editUsername = findViewById<EditText>(R.id.editUsername)
            val username = editUsername.text.toString()
            val userExists =  db.child("users").orderByChild("userName").equalTo(username)

            userExists.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists() || username == "") {
                        // if username exists show error
                        val error = findViewById<TextView>(R.id.errorText)
                        error.visibility = View.VISIBLE

                    } else {
                        //if username doesn't exist, create user
                        setUsername(username,username)
                        val intent = Intent(thisAct, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    throw error.toException();
                }
            })
        }
    }

    private fun setUsername(userId: String, userName: String) {
        val user = User(userName, 0, 0)

        CurrentUser.setUser(user)
        db.child("users").child(userId).setValue(user)
    }
}
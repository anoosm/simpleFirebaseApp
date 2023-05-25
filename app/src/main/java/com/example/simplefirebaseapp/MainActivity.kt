package com.example.simplefirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplefirebaseapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val firstName = binding.editTextFirstName.text.toString()
            val lastName = binding.editTextLastName.text.toString()
            val age = binding.editTextAge.text.toString().toInt()
            val userName = binding.editTextUsername.text.toString()

//            database = FirebaseDatabase.getInstance().getReference("Users")

            database = Firebase.database.reference

            val user = User(firstName, lastName, age, userName)
            database.child(userName).setValue(user).addOnSuccessListener {
                binding.editTextFirstName.text.clear()
                binding.editTextLastName.text.clear()
                binding.editTextAge.text.clear()
                binding.editTextUsername.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "error occured", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
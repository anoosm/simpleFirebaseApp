package com.example.simplefirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplefirebaseapp.databinding.ActivityViewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnFetchUser.setOnClickListener {

            val userName = binding.edtUserName.text.toString()

            if (userName.isNotEmpty()) {
                fetchData(userName)
            } else {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun fetchData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
//        database = Firebase.database.reference
        database.child(userName).get().addOnSuccessListener {
            if (it.exists()) {
                val firstName = it.child("firstName").value
                val lastName = it.child("lastName").value
                val age = it.child("age").value

                binding.edtUserName.text.clear()
                binding.tvDisplayFirstname.text = firstName.toString()
                binding.tvDisplayLastname.text = lastName.toString()
                binding.tvDisplayAge.text = age.toString()
            } else {
                Toast.makeText(this, "User doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "User fetching failed", Toast.LENGTH_SHORT).show()
        }
    }
}

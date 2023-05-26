package com.example.simplefirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplefirebaseapp.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {

            val userName = binding.edtUpdateUsername.text.toString()
            val firstName = binding.edtUpdateFirstname.text.toString()
            val lastName = binding.edtUpdateLastname.text.toString()
            val age = binding.edtUpdateAge.text.toString()

            updateDetails(userName, firstName, lastName, age)
        }
    }

    private fun updateDetails(userName: String, firstName: String, lastName: String, age: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
//            database = Firebase.database.reference
        val user = mapOf<String, String>(
            "firstName" to firstName,
            "lastName" to lastName,
            "age" to age
        )
        database.child(userName).updateChildren(user).addOnSuccessListener {
            binding.edtUpdateUsername.text.clear()
            binding.edtUpdateFirstname.text.clear()
            binding.edtUpdateLastname.text.clear()
            binding.edtUpdateAge.text.clear()

            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "User doesn't exist", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.simplefirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplefirebaseapp.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DeleteActivity : AppCompatActivity() {
    lateinit var binding : ActivityDeleteBinding
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDeleteUser.setOnClickListener {

            val userName = binding.edtDltUserName.text.toString()
            if(userName.isNotEmpty()) {
                deleteUser(userName)
            } else {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteUser(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
//        database = Firebase.database.reference
        database.child(userName).removeValue().addOnSuccessListener {
            Toast.makeText(this, "Successfully Deleted the User", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to delete the User", Toast.LENGTH_SHORT).show()
        }
    }
}
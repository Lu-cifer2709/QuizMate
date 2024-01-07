package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.R
import com.example.quizapp.adapters.QuizAdapter
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViews()
    }

    fun setUpViews() {
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecyclerView()
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this, "Error Fetching Data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        adapter = QuizAdapter(this, quizList)
        binding.quizRecylerView.layoutManager = GridLayoutManager(this, 2)
        binding.quizRecylerView.adapter = adapter
    }

    fun setUpDrawerLayout() {
        setSupportActionBar(binding.appBar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, binding.mainDrawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
        binding.navigationView.setNavigationItemSelectedListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            binding.mainDrawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
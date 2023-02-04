package com.example.codepathmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var emails: MutableList<Email>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emailsRv = findViewById<RecyclerView>(R.id.emailsRv)
        emails = EmailFetcher.getEmails()
        val emailAdapter = EmailAdapter(emails)
        emailsRv.adapter = emailAdapter
        emailsRv.layoutManager = LinearLayoutManager(this)
        //Load More Button implementation
        val button = findViewById<Button>(R.id.loadMoreButton)
        button.setOnClickListener {
            val newEmails = EmailFetcher.getNext5Emails()
            emails.addAll(newEmails)
            emailsRv.adapter?.notifyItemInserted(emails.size)
        }
    }
}
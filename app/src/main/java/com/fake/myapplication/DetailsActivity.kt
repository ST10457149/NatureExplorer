package com.fake.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.fake.myapplication.model.NatureItem

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val toolbar: Toolbar = findViewById(R.id.details_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val item = intent.getSerializableExtra("nature_item") as? NatureItem

        item?.let {
            findViewById<ImageView>(R.id.details_image).setImageResource(it.imageResId)
            findViewById<TextView>(R.id.details_title).text = it.title
            findViewById<TextView>(R.id.details_description).text = it.description
            title = it.title
        }
    }
}

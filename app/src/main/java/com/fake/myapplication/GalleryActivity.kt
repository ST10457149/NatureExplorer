package com.fake.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fake.myapplication.adapter.NatureAdapter
import com.fake.myapplication.model.NatureItem

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val toolbar: Toolbar = findViewById(R.id.gallery_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val recyclerView: RecyclerView = findViewById(R.id.gallery_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val natureItems = listOf(
            NatureItem(1, "Forest", "Deep green forest", R.drawable.nature),
            NatureItem(2, "Explorer", "Nature exploration", R.drawable.explorer),
            NatureItem(3, "Mountain", "High snowy peaks", R.drawable.nature),
            NatureItem(4, "River", "Clear mountain stream", R.drawable.explorer)
        )

        recyclerView.adapter = NatureAdapter(natureItems) { item ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("nature_item", item)
            startActivity(intent)
        }
    }
}

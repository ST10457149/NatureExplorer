package com.fake.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fake.myapplication.adapter.NatureAdapter
import com.fake.myapplication.model.NatureItem
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // RecyclerView implementation
        val recyclerView: RecyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val natureItems = listOf(
            NatureItem(1, "Forest", "Explore the serene beauty of the deep forest.", R.drawable.nature),
            NatureItem(2, "Explorer", "Navigate through the wild wonders of nature.", R.drawable.explorer),
            NatureItem(3, "Mountain", "Scale the heights of majestic mountain peaks.", R.drawable.nature),
            NatureItem(4, "River", "Follow the flow of clear mountain streams.", R.drawable.explorer)
        )

        recyclerView.adapter = NatureAdapter(natureItems) { item ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("nature_item", item) // Passing data using putExtra
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Already Home", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_gallery -> {
                // Explicit Intent
                val intent = Intent(this, GalleryActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_favorites -> {
                // Implicit Intent 1: Open Website
                val websiteIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nature.org"))
                startActivity(websiteIntent)
            }
            R.id.nav_settings -> {
                // Implicit Intent 2: Send Email
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("contact@natureexplorer.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Nature Explorer Feedback")
                }
                if (emailIntent.resolveActivity(packageManager) != null) {
                    startActivity(emailIntent)
                } else {
                    Toast.makeText(this, "No email app installed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

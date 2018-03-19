package com.org.drawer

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.org.drawer.view.CustomTypefaceSpan

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{



    private var navigationView: NavigationView? = null
    private var drawer: DrawerLayout? = null
    private var  drawerHeader: View? = null

    private var usernameTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initUI()
    }

    override fun onStart() {
        super.onStart()
    }

    /***
     *
     */
    private fun initUI() {


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)
        drawer = findViewById<DrawerLayout>(R.id.drawer_layout) as DrawerLayout
        drawer!!.setBackgroundColor(Color.WHITE)
        navigationView = findViewById<NavigationView>(R.id.nav_view) as NavigationView
        drawerHeader = navigationView!!.getHeaderView(0)
        populateDrawerHeader()
        applyFont()
    }

    private fun populateDrawerHeader(){

        usernameTextView = drawerHeader!!.findViewById<TextView>(R.id.usernameTextView)as TextView
        usernameTextView!!.setText("Kamran Ali")

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_home -> {
                // Handle the Home action
                Toast.makeText(this, "Handle the Home action", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_camera -> {
                // Handle the camera action
                Toast.makeText(this, "Handle the camera action", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_gallery -> {
                // Handle the Gallery action
                Toast.makeText(this, "Handle the Gallery action", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_share -> {
                // Handle the share action
                Toast.makeText(this, "Handle the Share action", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_send -> {
                // Handle the Send action
                Toast.makeText(this, "Handle the Send action", Toast.LENGTH_SHORT).show()
            }

        }
        closeDrawer()
        return true
    }


    /**
     *
     */
    private fun closeDrawer(){
        drawer_layout.closeDrawer(GravityCompat.END)
    }

    /**
     *
     */
    private fun openDrawer(){
        drawer_layout.openDrawer(GravityCompat.START)
    }

    /**
     *
     */
    private fun isOpenDrawer(): Boolean{
        return drawer_layout.isDrawerOpen(GravityCompat.END)
    }

    /**
     *
     */
    private fun isDrawerVisible(): Boolean{
        return drawer_layout.isDrawerVisible(GravityCompat.END)
    }

    /**
     * This method apply the custom font on Drawer text view.
     */
    private fun applyFont() {

        val m = navigationView!!.getMenu()
        for (i in 0 until m.size()) {
            val mi = m.getItem(i)

            //for applying a font to subMenu ...
            val subMenu = mi.subMenu
            if (subMenu != null && subMenu.size() > 0) {
                for (j in 0 until subMenu.size()) {
                    val subMenuItem = subMenu.getItem(j)
                    applyFontToMenuItem(subMenuItem)
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi)
        }

    }

    /**
     *
     */
    private fun applyFontToMenuItem(mi: MenuItem) {
        val font = Typeface.createFromAsset(assets, "fonts/roboto.light.ttf")
        val mNewTitle = SpannableString(mi.title)
        mNewTitle.setSpan(CustomTypefaceSpan("", font), 0,  mNewTitle.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        mi.title = mNewTitle
    }

    override fun onBackPressed() {
        if (isOpenDrawer()) {
            closeDrawer()
        } else {
            super.onBackPressed()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }



}

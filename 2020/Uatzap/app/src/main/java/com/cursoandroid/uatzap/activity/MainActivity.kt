package com.cursoandroid.uatzap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.cursoandroid.uatzap.R
import com.cursoandroid.uatzap.config.ConfiguracaoFirebase
import com.cursoandroid.uatzap.fragment.ContatosFragment
import com.cursoandroid.uatzap.fragment.ConversasFragment
import com.google.firebase.auth.FirebaseAuth
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbarPrincipal)
        this.auth = ConfiguracaoFirebase.getFirebaseAutenticacao()

        toolbar.title = "Whatsapp"

        setSupportActionBar(toolbar)

        val adapter: FragmentPagerItemAdapter = FragmentPagerItemAdapter(
            supportFragmentManager,
            FragmentPagerItems.with(this)
                .add("Conversas", ConversasFragment::class.java)
                .add("Contatos", ContatosFragment::class.java)
                .create()
        )

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter

        val viewPagerTab: SmartTabLayout = findViewById(R.id.viewPagerTab)
        viewPagerTab.setViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menuSair -> {
                deslogarUsuario()
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun deslogarUsuario() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

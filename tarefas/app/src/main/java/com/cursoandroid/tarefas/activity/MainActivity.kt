package com.cursoandroid.tarefas.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.tarefas.R
import com.cursoandroid.tarefas.adapter.TarefaAdapter
import com.cursoandroid.tarefas.model.Tarefa

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var tarefaAdapter: TarefaAdapter? = null
    private var listaTarefas = mutableListOf<Tarefa>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Configurar recycler
        recyclerView = recyclerListaDeTarefas

        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun carregarListaDeTarefas() {
        //Listar Tarefas
        val tarefa1 = Tarefa(1, "Ir ao mercado")
        val tarefa2 = Tarefa(2, "Fazer comida")
        val tarefa3 = Tarefa(3, "Estudar kotlin")
        val tarefa4 = Tarefa(4, "Estudar Android")
        val tarefa5 = Tarefa(5, "Estudar guitarra")

        listaTarefas.add(1, tarefa1)
        listaTarefas.add(2, tarefa2)
        listaTarefas.add(3, tarefa3)
        listaTarefas.add(4, tarefa4)
        listaTarefas.add(5, tarefa5)
        /**
         * Exibe Lista de tarefas no recyclerView
         */

        //configurar um adapter
        tarefaAdapter = TarefaAdapter(listaTarefas)
        //configurar um recyclerView
        var layoutManager = LinearLayoutManager(applicationContext)

        recyclerView?.layoutManager = layoutManager
        recyclerView?.setHasFixedSize(true)
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                LinearLayout.VERTICAL
            )
        )

        recyclerView?.adapter = tarefaAdapter.get

    }
}

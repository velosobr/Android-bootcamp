package com.cursoandroid.tarefas.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.tarefas.model.Tarefa

class TarefaAdapter(private var listaTarefas: List<Tarefa>) :
    RecyclerView.Adapter<TarefaAdapter.MyViewHolder>() {


    class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

    }

    override fun getItemCount(): Int {
        return this.listaTarefas.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}

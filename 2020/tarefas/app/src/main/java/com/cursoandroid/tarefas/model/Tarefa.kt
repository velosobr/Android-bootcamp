package com.cursoandroid.tarefas.model

import java.io.Serializable

class Tarefa(
    private val id: Long,
    private val nomeTarefa: String
) : Serializable
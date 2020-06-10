package com.cursoandroid.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            //Criar Banco de dados
            val database: SQLiteDatabase = openOrCreateDatabase("sqLite", Context.MODE_PRIVATE, null)


            //Criar tabela
            database.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade INT(3) ) ")

            //Inserir dados
//            database.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Lino', 26)")
//            database.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Karla', 42)")
//            database.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Duda', 21)")
//            database.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Alexandre', 21)")

            //recuperar pessoas
            /** var consulta = "SELECT nome, idade FROM pessoas " +
                    "WHERE nome = 'Lino'"
            */
            var consulta = "SELECT nome, idade FROM pessoas WHERE idade >=35"
            var cursor = database.rawQuery(consulta, null)
            cursor.moveToFirst()
            while (cursor != null){

               Log.i("Resultado nome",cursor.getString(0))
               Log.i("Resultado idade",cursor.getString(1))
                cursor.moveToNext()
            }
//            helloTextId.text = cursor.getString(0)
        } catch (e: Exception) {

        }


    }
}

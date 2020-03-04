package com.cursoandroid.recyclerview.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.cursoandroid.recyclerview.R;
import com.cursoandroid.recyclerview.activity.adapter.Adapter;
import com.cursoandroid.recyclerview.activity.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Filme> listaFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //Listagem de filmes
        this.criarFilmes();

        //set an adapter
        Adapter adapter = new Adapter(listaFilmes);

        //  set a layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);


    }

    public void criarFilmes() {

        Filme filme = new Filme("2001: Uma Odisseia no Espaço", "Geral", "1968", "teste");
        listaFilmes.add(filme);
        filme = new Filme("Cidadão Kane", "Geral", "1941", null);
        listaFilmes.add(filme);
        filme = new Filme("O Poderoso Chefão", "Geral", "1972", null);
        listaFilmes.add(filme);
        filme = new Filme("Andrei Rublev", "Geral", "1966", null);
        listaFilmes.add(filme);
        filme = new Filme("Os Sete Samurais", "Geral", "1954", null);
        listaFilmes.add(filme);
        filme = new Filme("O Sétimo Selo", "Geral", "1956", null);
        listaFilmes.add(filme);
        filme = new Filme("O Anjo Exterminador", "Geral", "1962", null);
        listaFilmes.add(filme);
        filme = new Filme("A Doce Vida", "Geral", "1960", null);
        listaFilmes.add(filme);
        filme = new Filme(" Blade Runner, O Caçador de Androides", "Geral", "1982", null);
        listaFilmes.add(filme);
        filme = new Filme("Ladrões de Bicicleta", "Geral", "1948", null);
        listaFilmes.add(filme);
        filme = new Filme("Laranja Mecânica", "Geral", "1972", null);
        listaFilmes.add(filme);
        filme = new Filme(" Apocalypse Now", "Geral", "1979", "teste");
        listaFilmes.add(filme);
        filme = new Filme("O Leopardo", "Geral", "1963", null);
        listaFilmes.add(filme);
        filme = new Filme("Aurora", "Geral", "1927", null);
        listaFilmes.add(filme);
        filme = new Filme("Cidadão Kane", "Geral", "1941", null);
        listaFilmes.add(filme);
        filme = new Filme("O Poderoso Chefão", "Geral", "1972", null);
        listaFilmes.add(filme);
        filme = new Filme("Andrei Rublev", "Geral", "1966", null);
        listaFilmes.add(filme);
        filme = new Filme("Os Sete Samurais", "Geral", "1954", null);
        listaFilmes.add(filme);
        filme = new Filme("O Sétimo Selo", "Geral", "1956", null);
        listaFilmes.add(filme);
        filme = new Filme("O Anjo Exterminador", "Geral", "1962", null);
        listaFilmes.add(filme);
        filme = new Filme("A Doce Vida", "Geral", "1960", null);
        listaFilmes.add(filme);
        filme = new Filme(" Blade Runner, O Caçador de Androides", "Geral", "1982", "Teste");
        listaFilmes.add(filme);
        filme = new Filme("Ladrões de Bicicleta", "Geral", "1948", null);
        listaFilmes.add(filme);
        filme = new Filme("Laranja Mecânica", "Geral", "1972", null);
        listaFilmes.add(filme);
        filme = new Filme(" Apocalypse Now", "Geral", "1979", null);
        listaFilmes.add(filme);
        filme = new Filme("O Leopardo", "Geral", "1963", null);
        listaFilmes.add(filme);
        filme = new Filme("Aurora", "Geral", "1927", null);
        listaFilmes.add(filme);
        filme = new Filme("Cidadão Kane", "Geral", "1941", null);
        listaFilmes.add(filme);
        filme = new Filme("O Poderoso Chefão", "Geral", "1972", null);
        listaFilmes.add(filme);
        filme = new Filme("Andrei Rublev", "Geral", "1966", null);
        listaFilmes.add(filme);


    }
}

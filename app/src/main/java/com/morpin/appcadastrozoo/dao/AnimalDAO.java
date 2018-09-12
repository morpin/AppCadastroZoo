package com.morpin.appcadastrozoo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.morpin.appcadastrozoo.model.Animal;
import com.morpin.appcadastrozoo.model.Especie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 10/09/2018.
 */

public class AnimalDAO {
    public static void inserir(Context contexto, Animal animal) {
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", animal.getNome());
        valores.put("idade", animal.getIdade());
        valores.put("codEspecie", animal.getEspecie().getId());

        banco.insert("animais", null, valores);

    }

    public static List<Animal> getAnimais(Context contexto) {
        List<Animal> listaDeAnimais = new ArrayList<>();
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getReadableDatabase();
        Cursor tabela = banco.rawQuery
                ("SELECT a.id,a.nome,a.idade,a.codEspecie,e.nome" +
                        " FROM animais a" +
                        " INNER JOIN especies e" +
                        " ON a.codEspecie=e.id" +
                        " ORDER BY a.nome", null);
        if (tabela.getCount() > 0) {
            tabela.moveToFirst();
            do {
                Especie esp = new Especie();
                esp.setId(tabela.getInt(3));

                Animal animal = new Animal();
                animal.setId(tabela.getInt(0));
                animal.setNome(tabela.getString(1));
                animal.setIdade(tabela.getDouble(2));
                animal.setEspecie(esp);

                listaDeAnimais.add(animal);

            } while (tabela.moveToNext());
        }

        return listaDeAnimais;
    }
}



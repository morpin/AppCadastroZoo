package com.morpin.appcadastrozoo.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.morpin.appcadastrozoo.model.Especie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 10/09/2018.
 */

public class EspecieDAO {

    public static void inserir(Context contexto, Especie especie){
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getWritableDatabase();

        banco.execSQL("INSERT INTO especies (nome) VALUES " +
                " ( '" + especie.getNome() + "' )  ");

    }

    public static List<Especie> getEspecies(Context contexto){
        List<Especie> listaDeEspecies = new ArrayList<>();
        Conexao conn = new Conexao(contexto);
        SQLiteDatabase banco = conn.getReadableDatabase();

        Cursor tabela = banco.rawQuery
                ("SELECT * FROM especies", null);
        if ( tabela.getCount() > 0){

            tabela.moveToFirst();

            do {
                Especie esp = new Especie();
                esp.setId( tabela.getInt(0) );
                esp.setNome( tabela.getString(1) );

                listaDeEspecies.add(esp);

            }while (tabela.moveToNext());
        }

        return listaDeEspecies;
    }

}

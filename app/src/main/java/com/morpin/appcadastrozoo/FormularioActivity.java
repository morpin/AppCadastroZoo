package com.morpin.appcadastrozoo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.morpin.appcadastrozoo.dao.AnimalDAO;
import com.morpin.appcadastrozoo.dao.EspecieDAO;
import com.morpin.appcadastrozoo.model.Animal;
import com.morpin.appcadastrozoo.model.Especie;

import java.util.List;

public class FormularioActivity extends AppCompatActivity {


    private EditText etNome, etIdade;
    private Button btnSalvar;
    private Spinner spEspecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = (EditText) findViewById(R.id.etNomeAnimal);
        etIdade = (EditText) findViewById(R.id.etIdadeAnimal);
        btnSalvar = (Button) findViewById(R.id.btnSalvarAnimal);
        spEspecie = (Spinner) findViewById(R.id.spEspecieAnimal);

        carregarEspecies();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void  salvar(){
        String nome = etNome.getText().toString();
        if (nome.isEmpty()||spEspecie.getSelectedItemPosition()==0){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle(getResources().getString(R.string.txtAtencao));
            alerta.setMessage(R.string.txtCamposObrigatorios);
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setNeutralButton("OK",null);
            alerta.show();
        }else {
            String quantidade = etIdade.getText().toString();
            if(quantidade.isEmpty())
                quantidade="0";
            quantidade=quantidade.replace(",",".");
            double qtd=Double.valueOf(quantidade);

            Animal prod=new Animal();
            prod.setNome(nome);
            prod.setIdade(qtd);
            prod.setEspecie((Especie) spEspecie.getSelectedItem());

            AnimalDAO.inserir(this,prod);

            finish();
        }
    }


    private void carregarEspecies(){

        List<Especie> lista = EspecieDAO.getEspecies(this);
        Especie fake = new Especie();
        fake.setId( 0 );
        fake.setNome( getResources().getString(R.string.txtselecioneespecie) );
        lista.add(0, fake);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, lista);
        spEspecie.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.menu_nova_especie ){
            cadastrarEspecie();
        }

        return super.onOptionsItemSelected(item);
    }

    private void cadastrarEspecie(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle( getResources().getString(R.string.txtnovaespecie) );
        alerta.setIcon( android.R.drawable.ic_menu_edit );

        final EditText etnomeespecie = new EditText(this);
        etnomeespecie.setHint( R.string.hintnovaespecie );
        etnomeespecie.setTextColor(Color.BLACK);

        alerta.setView( etnomeespecie );

        alerta.setNeutralButton(
                getResources().getString(R.string.txtcancelar) ,
                null );
        alerta.setPositiveButton(
                getResources().getString(R.string.txtsalvar),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nome = etnomeespecie.getText().toString();
                        if ( ! nome.isEmpty() ){
                            Especie esp = new Especie();
                            esp.setNome( nome );
                            EspecieDAO.inserir(FormularioActivity.this, esp);
                            carregarEspecies();
                        }
                    }
                });
        alerta.show();

    }
}

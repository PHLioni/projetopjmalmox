package pjm.projetoalmox.com.projetopjmalmox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Random;

import static android.widget.Toast.LENGTH_LONG;

public class TelaPedido extends AppCompatActivity {

    TextView nome, area;
    String mostraTexto, mostraTexto2,qtd;
    Button btnEnviar,btnCancelar;


    ListView listaItens;
    ItensAdapter itensAdapter;
    List<buscaItens> lista;
    String login, codigo, nomeItem, qtdItem, dataReg,senha;
    Random ident = new Random( );
    int valor,somaValores[] = new int [5];
    ArrayList listaSpinner;


    //host do banco de dados
    private String HOST = "http://10.0.0.107:8080/appalmox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tela_pedido );



        listaItens = findViewById( R.id.listaItens );

        lista = new ArrayList<>();
        itensAdapter = new ItensAdapter( TelaPedido.this, lista );
        listaItens.setAdapter( itensAdapter );


        nome = findViewById( R.id.nome );
        btnEnviar = findViewById( R.id.btnEnviar );
        btnCancelar = findViewById( R.id.btnCancelar);

        //Traz o nome da TelaMenu
        final Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if (params != null) {
            mostraTexto = params.getString( "mensagem" );

        }

        //Traz a area da Telamenu
        Intent intent2 = getIntent();
        Bundle params2 = intent2.getExtras();
        if (params2 != null) {
            mostraTexto2 = params2.getString( "area" );

        }

        for (int j = 0; j < 3; j++) {
            valor = (65 + ident.nextInt(90 - 65));
            somaValores[j] = valor;
        }
        for (int j = 3; j < 5; j++) {
            valor = ident.nextInt(10);
            somaValores[j] = valor;
        }

        senha = String.valueOf(somaValores[0]) + String.valueOf(somaValores[1]) + String.valueOf(somaValores[2]) + String.valueOf(somaValores[3]) + String.valueOf(somaValores[4]);


        //Classe usada para trazer os dados do banco e montar a listview
        mostraPedidos();


        btnEnviar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enviaPedido();
            }

        } );

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        } );


    }

    private void mostraPedidos() {


        String url = "http://10.0.0.107:8080/appalmox/itens.php";

        Ion.with( getBaseContext() )
                .load( url )
                .asJsonArray()
                .setCallback( new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        try {
                            //Conta o tamanho da lista
                            for (int i = 0; i < result.size(); i++) {

                                //Converte cada array em um objeto
                                JsonObject obj = result.get( i ).getAsJsonObject();

                                //busca o get e set
                                buscaItens p = new buscaItens();


                                p.setItem( obj.get( "nome" ).getAsString() );
                                p.setCodigo( obj.get( "codigo" ).getAsString() );


                                lista.add( p );

                            }
                            //atualiza a lista de forma automatica
                            itensAdapter.notifyDataSetChanged();


                        } catch (Exception erro) {

                            Toast.makeText( TelaPedido.this, "Ocorreu um erro 1 " + erro, Toast.LENGTH_LONG ).show();
                        }
                    }
                } );
    }

    private void enviaPedido() {




        for (int j = 0; j < lista.size(); j++) {


             qtd = lista.get( j ).getGetQuantidade();

            if (qtd.equalsIgnoreCase("0") && (qtd.equalsIgnoreCase( null ))) {

                Toast.makeText( TelaPedido.this, "Ocorreu um erro", Toast.LENGTH_LONG ).show();


            }else{

                codigo = lista.get( j ).getCodigo();
                nomeItem = lista.get( j ).getItem();
                qtd = lista.get( j ).getGetQuantidade();


                SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");
                Date data = new Date();
                dataReg = formataData.format(data);


                String url = "http://10.0.0.107:8080/appalmox/create.php";

                Ion.with( TelaPedido.this )
                        .load( url )
                        .setBodyParameter( "nome", mostraTexto )
                        .setBodyParameter( "area", mostraTexto2 )
                        .setBodyParameter( "logdata", dataReg )
                        .setBodyParameter( "codigo", codigo )
                        .setBodyParameter( "item", nomeItem )
                        .setBodyParameter( "quantidade", qtd )
                        .setBodyParameter( "identificador", senha )
                        .asJsonObject()
                        .setCallback( new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

                                if (result.get( "CREATE" ).getAsString().equals( "OK" )) {

                                    int id = Integer.parseInt( result.get( "ID" ).getAsString() );


                                } else {
                                    Toast.makeText( TelaPedido.this, "Ocorreu um erro", Toast.LENGTH_LONG ).show();
                                }

                            }
                        } );

            }
        }
        Toast.makeText( TelaPedido.this, "Pedido enviado", Toast.LENGTH_LONG ).show();
        onBackPressed();

    }


}

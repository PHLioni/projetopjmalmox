package pjm.projetoalmox.com.projetopjmalmox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class TelaHistorico extends AppCompatActivity {

    ListView listaPedidos;
    PedidosAdapter pedidosAdapter;
    List<buscaHistorico> lista;
    String login, nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tela_historico );

        listaPedidos = (ListView) findViewById( R.id.listaPedidos );

        lista = new ArrayList<buscaHistorico>();
        pedidosAdapter = new PedidosAdapter( TelaHistorico.this, lista );
        listaPedidos.setAdapter( pedidosAdapter );

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if (params != null) {
            nome = params.getString( "mensagem" );

        }

        mostraPedidos();
    }

    private void mostraPedidos() {


        String url = "http://10.0.0.107:8080/appalmox/read.php";
        Ion.with( getBaseContext() )
                .load( url )
                .asJsonArray()
                .setCallback( new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        try {

                            for (int i = 0; i < result.size(); i++) {

                                JsonObject obj = result.get( i ).getAsJsonObject();

                                buscaHistorico p = new buscaHistorico();

                                if (obj.get( "nome" ).getAsString().equals( nome )) {
                                    p.setId( obj.get( "identificador" ).getAsString() );
                                    p.setNome( obj.get( "nome" ).getAsString() );
                                    p.setData( obj.get( "logdata" ).getAsString() );
                                    p.setStatus( obj.get( "status" ).getAsString() );
                                    p.setHora( obj.get( "loghora" ).getAsString() );

                                    lista.add( p );
                                }
                            }

                            pedidosAdapter.notifyDataSetChanged();


                        } catch (Exception erro) {

                            Toast.makeText( TelaHistorico.this, "Ocorreu um erro 1 " + erro, Toast.LENGTH_LONG ).show();
                        }
                    }
                } );

    }
}
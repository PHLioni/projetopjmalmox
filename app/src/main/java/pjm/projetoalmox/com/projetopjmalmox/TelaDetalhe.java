package pjm.projetoalmox.com.projetopjmalmox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class TelaDetalhe extends AppCompatActivity {

    String data, nome,ident;

    ListView listaDetalhe;
    DetalheAdapter detalheAdapter;
    List<buscaDetalhe> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tela_detalhe );

        listaDetalhe = (ListView) findViewById( R.id.listaDetalhe );


        lista = new ArrayList<buscaDetalhe>();
        detalheAdapter = new DetalheAdapter( TelaDetalhe.this, lista );
        listaDetalhe.setAdapter( detalheAdapter );

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if (params != null) {
            data = params.getString( "data" );
        }

        Intent intent2 = getIntent();
        Bundle params2 = intent2.getExtras();
        if (params2 != null) {
            nome = params2.getString( "nome" );
        }

        Intent intent3 = getIntent();
        Bundle params3 = intent3.getExtras();
        if (params3 != null) {
            ident = params3.getString( "ident" );
        }

        mostraDetalhe();


    }


    private void mostraDetalhe() {


        String url = "http://10.0.0.107:8080/appalmox/detalhe.php";
        Ion.with( getBaseContext() )
                .load( url )
                .asJsonArray()
                .setCallback( new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        try {

                            for (int i = 0; i < result.size(); i++) {

                                JsonObject obj = result.get( i ).getAsJsonObject();

                                buscaDetalhe p = new buscaDetalhe();

                                if (obj.get( "nome" ).getAsString().equals( nome ) && (obj.get( "data" ).getAsString().equals( data ) && (obj.get( "identificador" ).getAsString().equals( ident )))) {

                                    p.setNomeItem( obj.get( "item" ).getAsString() );
                                    p.setCodItem( obj.get( "codigo" ).getAsString() );
                                    p.setQtd( obj.get( "quantidade" ).getAsString() );


                                    lista.add( p );
                                } else {

                                }

                            }

                            detalheAdapter.notifyDataSetChanged();


                        } catch (Exception erro) {

                            Toast.makeText( TelaDetalhe.this, "Ocorreu um erro 1 " + erro, Toast.LENGTH_LONG ).show();
                        }
                    }
                } );

    }

}

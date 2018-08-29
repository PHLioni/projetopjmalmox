package pjm.projetoalmox.com.projetopjmalmox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class ListDatas extends AppCompatActivity {

    ListView listaDatas, listaItens;
    DataAdapter dataAdapter;
    List<buscaData> lista;
    String nome,dataItem;
    int itemCliacado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_datas );

        mostraDatas();

        listaDatas = findViewById( R.id.list );
        lista = new ArrayList<>();
        dataAdapter = new DataAdapter( ListDatas.this, lista );
        listaDatas.setAdapter( dataAdapter );

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if (params != null) {
            nome = params.getString( "mensagem" );
        }




        listaDatas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int codigoPosicao = i;
                buscaData c = (buscaData) adapterView.getAdapter().getItem( codigoPosicao );
                Intent it = new Intent(ListDatas.this, TelaDetalhe.class);
                Bundle params = new Bundle();
                String resposta = String.valueOf( c.getData());
                params.putString("data", resposta);
                it.putExtras(params);

                Bundle params2 = new Bundle();
                String resposta2 = nome;
                params2.putString("nome", resposta2);
                it.putExtras(params2);

                Bundle params3 = new Bundle();
                String resposta3 = String.valueOf( c.getIdent() );
                params3.putString("ident", resposta3);
                it.putExtras(params3);


                startActivity( it );

                itemCliacado = i;

            }
        });

    }

    private void mostraDatas() {


        String url = "http://10.0.0.107:8080/appalmox/datas.php";
        Ion.with( getBaseContext() )
                .load( url )
                .asJsonArray()
                .setCallback( new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        try {

                            for (int i = 0; i < result.size(); i++) {

                                JsonObject obj = result.get( i ).getAsJsonObject();

                                buscaData p = new buscaData();

                                if (obj.get( "nome" ).getAsString().equals( nome )) {

                                    p.setData( obj.get( "data" ).getAsString() );
                                    p.setStatus( obj.get( "status" ).getAsString() );
                                    p.setIdent( obj.get("identificador").getAsString() );
                                    p.setPagou( obj.get("pagou").getAsString() );

                                    lista.add( p );
                                }else{

                                }

                            }

                            dataAdapter.notifyDataSetChanged();


                        } catch (Exception erro) {

                            Toast.makeText( ListDatas.this, "Ocorreu um erro 1 " + erro, Toast.LENGTH_LONG ).show();
                        }
                    }
                } );

    }

}

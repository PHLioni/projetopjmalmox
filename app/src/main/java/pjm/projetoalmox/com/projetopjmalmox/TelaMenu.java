package pjm.projetoalmox.com.projetopjmalmox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.HostnameResolutionException;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class TelaMenu extends AppCompatActivity {

    Button btnPedido, btnHistorico, btnSair;
    TextView nome;
    TextView area;
    TextView coor;
    String nomeA,areaA,coorA,login;


    String HOST = "http://10.0.0.107:8080/appalmox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);

        btnPedido = findViewById(R.id.btnPedido);
        btnHistorico = findViewById(R.id.btnHistorico);
        btnSair = findViewById(R.id.btnSair);
        nome = findViewById(R.id.nome);
        area = findViewById(R.id.area);
        coor = findViewById(R.id.coor);

        String url = HOST + "/tecnico.php";

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if (params != null) {
            login = params.getString("mensagem");
        }


       Ion.with(TelaMenu.this)
               .load(url)
               .setBodyParameter("login", login)
               .asJsonObject()
               .setCallback(new FutureCallback<JsonObject>() {
                   @Override
                   public void onCompleted(Exception e, JsonObject result) {

                        nomeA = result.get("nome").getAsString();
                        areaA = result.get("area").getAsString();
                        coorA = result.get("coor").getAsString();

                       nome.setText(nomeA);
                       area.setText(areaA);
                       coor.setText(coorA);



                   }
               });


        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(areaA.equals( "MDU" )) {
                    Intent it = new Intent( TelaMenu.this, TelaPedidoMdu.class );
                    Bundle params = new Bundle();
                    String resposta = nome.getText().toString();
                    params.putString( "mensagem", resposta );
                    it.putExtras( params );

                    Bundle params2 = new Bundle();
                    String resposta2 = area.getText().toString();
                    params2.putString( "area", resposta2 );
                    it.putExtras( params2 );
                    startActivity( it );
                }else{
                    Intent it = new Intent( TelaMenu.this, TelaPedido.class );
                    Bundle params = new Bundle();
                    String resposta = nome.getText().toString();
                    params.putString( "mensagem", resposta );
                    it.putExtras( params );

                    Bundle params2 = new Bundle();
                    String resposta2 = area.getText().toString();
                    params2.putString( "area", resposta2 );
                    it.putExtras( params2 );
                    startActivity( it );

                }
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(TelaMenu.this, MainActivity.class);
                startActivity(it);
            }
        });

        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(TelaMenu.this, ListDatas.class);
                Bundle params = new Bundle();
                String resposta = nome.getText().toString();
                params.putString("mensagem", resposta);
                it.putExtras(params);
                startActivity(it);
            }
        });




     }

    }



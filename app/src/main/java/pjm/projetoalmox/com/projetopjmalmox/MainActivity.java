package pjm.projetoalmox.com.projetopjmalmox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnEntrar;
    EditText login_01;
    String login, nome, mostraTexto;
    Bundle params;

    ListView listaPedidos;
    PedidosAdapter pedidosAdapter;
    List<buscaHistorico> lista;

    private String HOST = "http://10.0.0.107:8080/appalmox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEntrar = findViewById(R.id.btnEntrar);
        login_01 = findViewById(R.id.login);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login = login_01.getText().toString();

                final String URL = HOST + "/logar.php";

                Ion.with(MainActivity.this)
                        .load(URL)
                        .setBodyParameter("login",login)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {

                                try{
                                    String retorno = result.get("LOGIN").getAsString();

                                    if(retorno.equals("ERRO")){
                                        Toast.makeText(MainActivity.this, "Tecnico não cadastrado!" + login, Toast.LENGTH_LONG).show();
                                    }else if (retorno.equals("SUCESSO")){

                                         Intent it = new Intent(MainActivity.this, TelaMenu.class);
                                         Bundle params = new Bundle();
                                         login = login_01.getText().toString();
                                         params.putString("mensagem", login);
                                         it.putExtras(params);
                                         startActivity(it);


                                    }

                                }catch (Exception erro){

                                    Toast.makeText(MainActivity.this, "Ocorreu um erro " + erro, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }
}

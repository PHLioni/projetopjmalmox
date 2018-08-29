package pjm.projetoalmox.com.projetopjmalmox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DataAdapter extends BaseAdapter{

    private Context ctx;
    private List<buscaData> lista;

    String dataReg;

    public DataAdapter(Context ctx2, List<buscaData> list2) {

        ctx = ctx2;
        lista = list2;


    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public buscaData getItem(int i) {
        return lista.get( i );
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = null;

        if (view == null) {
            LayoutInflater inflater = ((Activity) ctx).getLayoutInflater();
            v = inflater.inflate( R.layout.lista_data, null );
        } else {

            v = view;
        }
        buscaData p = getItem( i );

        TextView data = v.findViewById( R.id.listaMostra );
        TextView status = v.findViewById( R.id.listaStatus );
        TextView ident = v.findViewById( R.id.listaIdent );
        TextView pagou = v.findViewById( R.id.pagou );


        data.setText("Data: "+  p.getData());
        status.setText( "Status: " + p.getStatus() );
        ident.setText( "Pedido N°: " + p.getIdent());
        pagou.setText( "Pago por: " + p.getPagou());

        return v;


    }
}

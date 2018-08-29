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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class DetalheAdapter extends BaseAdapter {


    private Context ctx;
    private List<buscaDetalhe> lista;


    public DetalheAdapter(Context ctx2, List<buscaDetalhe> list2) {

        ctx = ctx2;
        lista = list2;


    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public buscaDetalhe getItem(int i) {
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
            v = inflater.inflate( R.layout.lista_detalhe, null );
        } else {

            v = view;
        }
        final buscaDetalhe p = getItem( i );


        TextView nomeItem = (TextView) v.findViewById( R.id.nomeItem );
        TextView codItem = (TextView) v.findViewById( R.id.codItem );
        TextView qtd = (TextView) v.findViewById( R.id.qtd );


        nomeItem.setText( p.getNomeItem() );
        codItem.setText( p.getCodItem() + ":" );
        qtd.setText( p.getQtd() );

        return v;


    }
}

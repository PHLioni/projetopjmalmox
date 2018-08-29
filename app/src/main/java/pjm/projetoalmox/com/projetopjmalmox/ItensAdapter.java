package pjm.projetoalmox.com.projetopjmalmox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.koushikdutta.async.parser.StringParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItensAdapter extends BaseAdapter {

    private Context ctx;
    private List<buscaItens> lista;
    Intent i;
    Spinner quantidade;
    int listaSpinner = 0;
    List<Integer> listaS;


    public ItensAdapter(Context ctx2, List<buscaItens> list2) {

        ctx = ctx2;
        lista = list2;
        this.listaS = new ArrayList();


    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public buscaItens getItem(int i) {
        return lista.get( i );
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View v = view;
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity) ctx).getLayoutInflater();
            v = inflater.inflate( R.layout.espelho, null );

            holder.item = (TextView) v.findViewById( R.id.item );
            holder.quantidade = (Spinner) v.findViewById( R.id.quantidade );
            holder.codigo = (TextView) v.findViewById( R.id.codigo );

            v.setTag( holder );
        } else {

            holder = (ViewHolder) v.getTag();
        }

        final buscaItens p = getItem( i );



        final ArrayAdapter qtd = ArrayAdapter.createFromResource( ctx, R.array.quantidade, android.R.layout.simple_spinner_dropdown_item );


        holder.item.setText( p.getItem() + ":" );
        holder.quantidade.setAdapter( qtd );
        int pos = listaS.get( i );
        holder.quantidade.setSelection( pos );
        holder.codigo.setText( p.getCodigo() );


        holder.quantidade.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int codigoPosicao = i;

                p.setGetQuantidade( adapterView.getAdapter().getItem( codigoPosicao ).toString() );
                listaSpinner = i;


                listaS.add(i , listaSpinner );


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );


        return v;
    }

    private class ViewHolder {
        TextView item, codigo;
        Spinner quantidade;
    }


}
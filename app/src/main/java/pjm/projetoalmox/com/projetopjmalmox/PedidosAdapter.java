package pjm.projetoalmox.com.projetopjmalmox;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

public class PedidosAdapter extends BaseAdapter {

    private Context ctx;
    private List <buscaHistorico> lista;


    public PedidosAdapter(Context ctx2, List<buscaHistorico> lista2){

        ctx = ctx2;
        lista = lista2;

    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public buscaHistorico getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = null;

        if(view == null){
            LayoutInflater inflater = ((Activity)ctx).getLayoutInflater();
            v = inflater.inflate(R.layout.item_lista, null);
        }else{

            v = view;
        }

        buscaHistorico p = getItem(i);

        TextView numeroPedido = (TextView) v.findViewById(R.id.numeroPedido);
        TextView data= (TextView) v.findViewById(R.id.Data);
        TextView nome = (TextView) v.findViewById(R.id.nome);
        TextView status = (TextView)v.findViewById( R.id.status );
        TextView hora = (TextView)v.findViewById( R.id.Hora );


        numeroPedido.setText("Pedido: "+ p.getId());
        data.setText("Data: " + p.getData());
        nome.setText("Tecnico: " + p.getNome());
        status.setText( "Status: "+p.getStatus() );
        hora.setText( "Hora: "+p.getHora() );





        return v;
    }
}

package br.com.infnet.tp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.infnet.tp3.Model.Pessoa;

/**
 * Created by kaike on 25/08/2017.
 */

public class ListAdapterContato extends ArrayAdapter {

    private Context context;
    private ArrayList<Pessoa> lista;

    public ListAdapterContato(Context context, List<Pessoa> lista){
        super(context, 0, lista);

        this.context = context;
        this.lista = (ArrayList<Pessoa>) lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Pessoa ContatoPosicao = this.lista.get (position);

        convertView = LayoutInflater.from(this.context).inflate(android.R.layout.simple_list_item_1, null);

        TextView txtName  = (TextView) convertView.findViewById(android.R.id.text1);
        txtName.setText(ContatoPosicao.getNome());

        return convertView;
    }
}

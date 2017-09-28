package br.com.infnet.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.infnet.tp3.Model.Pessoa;

/**
 * Created by kaike on 25/08/2017.
 */

public class ListActivity extends AppCompatActivity {


    ListView Contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Contacts = (ListView) findViewById(R.id.lvContacts);

        ArrayList<Pessoa> listContacts = new ArrayList<Pessoa>();

        Pessoa a = new Pessoa ();


        listContacts.add(a);


        ListAdapterContato adapterContact = new ListAdapterContato(this, listContacts);

        Contacts.setAdapter(adapterContact);

        final ArrayList<Pessoa> listContatoFinal = listContacts;

        Contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetalheContato.class);
                Pessoa ContactSelecionado = listContatoFinal.get(position);

                intent.putExtra("ContactSelecionado", (Parcelable) ContactSelecionado);

                startActivity(intent);
            }
        });
    }
}

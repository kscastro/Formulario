package br.com.infnet.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.infnet.tp3.DetalheContato;
import br.com.infnet.tp3.ListAdapterContato;
import br.com.infnet.tp3.Model.Pessoa;
import br.com.infnet.tp3.R;

/**
 * Created by kaike on 25/08/2017.
 */

public class ListActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;


    ListView Contacts;
    ArrayList<Pessoa> listContacts = new ArrayList<Pessoa>();
    DatabaseReference databaseReference;
    final ArrayList<Pessoa> listContatoFinal = listContacts;
    ArrayList<String> arrayNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Contacts = (ListView) findViewById(R.id.lvContacts);
        Pessoa a = new Pessoa ();
        arrayNome = new ArrayList<>();
        inicilizarFirebase();
        eventoDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrayNome);
        Contacts.setAdapter(adapter);

        Contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetalheContato.class);
                String ContactSelecionado = arrayNome.get(position);

                intent.putExtra("ContactSelecionado", (Serializable) ContactSelecionado);

                startActivity(intent);
            }
        });
    }
    private void eventoDatabase() {
        databaseReference.child("Pessoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Pessoa p = objSnapshot.getValue(Pessoa.class);
                    arrayNome.add(p.getNome());



                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void inicilizarFirebase() {
        FirebaseApp.initializeApp(ListActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

}

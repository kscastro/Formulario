package br.com.infnet.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.infnet.tp3.Model.Pessoa;

/**
 * Created by kaike on 25/08/2017.
 */

public class DetalheContato extends AppCompatActivity {

    TextView txtDetailName,txtDetailSenha,txtDetailTel,txtDetailCel,txtDetailEmail,txtDetailCpf,txtDetailAddress;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String ContactSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_contato);

        txtDetailName = (TextView) findViewById(R.id.txtName);
        txtDetailSenha =(TextView) findViewById(R.id.txtSenha);
        txtDetailEmail = (TextView) findViewById(R.id.txtEmail);
        txtDetailTel = (TextView) findViewById(R.id.txtTel);
         txtDetailCel = (TextView) findViewById(R.id.txtCel);
        txtDetailCpf = (TextView) findViewById(R.id.txtCpf);
        txtDetailAddress = (TextView) findViewById(R.id.txtAddress);

        Intent intent = getIntent();
         ContactSelecionado = (String) intent.getSerializableExtra("ContactSelecionado");
        inicilizarFirebase();
        eventoDatabase();







    }
    private void eventoDatabase() {
        databaseReference.child("Pessoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Pessoa p = objSnapshot.getValue(Pessoa.class);
                    if (ContactSelecionado.equals(p.getNome())){
                        txtDetailName.setText("Nome: "+p.getNome());
                        txtDetailSenha.setText("Senha: "+p.getSenha());
                        txtDetailEmail.setText("Email: "+p.getEmail());
                        txtDetailTel.setText("Tel: "+p.getTelefone());
                        txtDetailCel.setText("Cel: "+p.getCelular());
                        txtDetailCpf.setText("CPF: "+p.getCpf());
                        txtDetailAddress.setText("Cidade: "+p.getCidade());

                    }




                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void inicilizarFirebase() {
        FirebaseApp.initializeApp(DetalheContato.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

}

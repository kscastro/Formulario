package br.com.infnet.tp3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.infnet.tp3.Model.Pessoa;

public class MainActivity extends AppCompatActivity {

    EditText Name,Senha,Email,Telefone,Celular,CPF,Cidade;
    Button b1,b2;
    Button   btnList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.edtName);
        Senha = (EditText)findViewById(R.id.edtSenha);
        Email = (EditText)findViewById(R.id.edtEmail);
        Telefone = (EditText)findViewById(R.id.edtTelefone);
        Celular = (EditText)findViewById(R.id.edtCelular);
        CPF = (EditText)findViewById(R.id.edtCPF);
        Cidade = (EditText)findViewById(R.id.edtCidade);
        b1=(Button)findViewById(R.id.btnSave);
        btnList = (Button)findViewById(R.id.btnList);


    inicilizarFirebase();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pessoa p = new Pessoa();
                p.setUid(UUID.randomUUID().toString());
                p.setNome(Name.getText().toString());
                p.setEmail(Email.getText().toString());
                p.setSenha(Senha.getText().toString());
                p.setTelefone(Telefone.getText().toString());
                p.setCelular(Celular.getText().toString());
                p.setCpf(CPF.getText().toString());
                p.setCidade(Cidade.getText().toString());
                databaseReference.child("Pessoa").child(p.getUid()).setValue(p);
                limparCampos();


            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();

                Intent toListView = new Intent (context, ListActivity.class);
                startActivity(toListView);

            }
        });



    }




    private void limparCampos() {
        Name.setText("");
        Senha.setText("");
        Email.setText("");
        Telefone.setText("");
        Celular.setText("");
        CPF.setText("");
        Cidade.setText("");
    }

    private void inicilizarFirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}

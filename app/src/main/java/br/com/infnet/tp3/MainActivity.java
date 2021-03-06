package br.com.infnet.tp3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.infnet.tp3.ListActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
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
        b2=(Button)findViewById(R.id.btnClean);
        btnList = (Button)findViewById(R.id.btnList);
        CPF.addTextChangedListener(Mask.insert("###.###.###-##", CPF));


    inicilizarFirebase();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //Valida se os campos não estão vazios
                Validator.validateNotNull(Name,"Preencha o campo nome");
                Validator.validateNotNull(Senha,"Preencha o campo senha");
                Validator.validateNotNull(Email,"Preencha o campo E-mail");
                Validator.validateNotNull(Telefone,"Preencha o campo Telefone");
                Validator.validateNotNull(Celular,"Preencha o campo celular");
                Validator.validateNotNull(CPF,"Preencha o campo CPF");
                Validator.validateNotNull(Cidade,"Preencha o campo Cidade");

                int isTrue = 1;

                //Validação do CPF
                boolean cpf_valido = Validator.validateCPF(CPF.getText().toString());
                if(!cpf_valido){
                    CPF.setError("CPF inválido");
                    CPF.setFocusable(true);
                    CPF.requestFocus();

                    isTrue = 0;
                }
                //Validação do E-mail
                boolean email_valido = Validator.validateEmail(Email.getText().toString());
                if(!email_valido){
                    Email.setError("Email inválido");
                    Email.setFocusable(true);
                    Email.requestFocus();
                    isTrue = 0;
                }
                //Salva as Informações no TXT
                if(isTrue ==1){
                    try {
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
                    }
                    // a == b

                    catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

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
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
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
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
}

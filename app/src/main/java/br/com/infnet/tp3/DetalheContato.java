package br.com.infnet.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.infnet.tp3.Model.Pessoa;

/**
 * Created by kaike on 25/08/2017.
 */

public class DetalheContato extends AppCompatActivity {

    TextView txtDetailName;
    TextView txtDetailTel;
    TextView txtDetailEmail;
    TextView txtDetailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_contato);

        txtDetailName = (TextView) findViewById(R.id.txtName);
        txtDetailTel = (TextView) findViewById(R.id.txtTel);
        txtDetailEmail = (TextView) findViewById(R.id.txtEmail);
        txtDetailAddress = (TextView) findViewById(R.id.txtAddress);

        Intent intent = getIntent();
        Pessoa ContactSelecionado = (Pessoa) intent.getSerializableExtra("ContactSelecionado");
        String name = ContactSelecionado.getNome();
        String tel = ContactSelecionado.getTelefone();
        String email = ContactSelecionado.getEmail();
        String address = ContactSelecionado.getCidade();
    }
}

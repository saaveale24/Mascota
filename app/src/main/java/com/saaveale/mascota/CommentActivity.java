package com.saaveale.mascota;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CommentActivity extends AppCompatActivity {
    private EditText etNombre;
    private EditText etCorreo;
    private EditText etMensaje;
    private Button btnEnviarComentario;
    private Pattern pattern = Pattern
            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        etNombre=(EditText) findViewById(R.id.etNombre);
        etCorreo=(EditText) findViewById(R.id.etCorreo);
        etMensaje=(EditText) findViewById(R.id.etMensaje);
        btnEnviarComentario=(Button) findViewById(R.id.btnEnviarComentario);

        btnEnviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

    }
    private void sendEmail() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String mensaje = etMensaje.getText().toString().trim();

        if(correo.length()>0 && pattern.matcher(correo).find()==true){
            SendMail sm = new SendMail(this,findViewById(android.R.id.content), correo,
                    "Comentario de "+nombre, mensaje);
            sm.execute();
        }else{
            Snackbar.make(findViewById(android.R.id.content),"El correo ingresado es inválido",Snackbar.LENGTH_SHORT).show();
        }
    }

    public void LimpiarCampos(){
        etMensaje.setText("");
        etCorreo.setText("");
        etNombre.setText("");
    }
}
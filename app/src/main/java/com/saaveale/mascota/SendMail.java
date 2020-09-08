package com.saaveale.mascota;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail extends AsyncTask {
    private Context context;
    private View view;
    private Session session;
    private String email;
    private String subject;
    private String message;
    private ProgressDialog progressDialog;
    private boolean error=false;
    public SendMail(Context context, View view, String email, String subject, String message){
        this.context = context;
        this.view=view;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Properties props = new Properties();
        props.put("mail.smtp.host", Config.HOST);
        props.put("mail.smtp.socketFactory.port", Config.PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", Config.AUTH);
        props.put("mail.smtp.port", Config.PORT);
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(com.saaveale.mascota.Config.EMAIL, com.saaveale.mascota.Config.PASSWORD);
            }
        });
        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(com.saaveale.mascota.Config.EMAIL));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mm.setSubject(subject);
            mm.setText(message);
            Transport.send(mm);
        }
        catch (MessagingException e) {
            e.printStackTrace();
            error=true;
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,"Enviando correo","Por favor espere...",false,false);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
        if(error){
            Snackbar.make(view,"Error en el envio del correo",Snackbar.LENGTH_LONG)
                    .setAction("Con otra app", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                          sendEmailWithApp();
                        }
                    })
                    .show();
        }else {
            Snackbar.make(view, "Mensaje enviado", Snackbar.LENGTH_LONG).show();
            ((CommentActivity)context).LimpiarCampos();
        }
    }

    private void sendEmailWithApp() {
        String[] TO = {email};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Enviar correo..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Snackbar.make(view,"No tiene un cliente de correo instalado",Snackbar.LENGTH_SHORT).show();
        }
    }
}
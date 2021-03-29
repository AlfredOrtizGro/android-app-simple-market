package com.team9.tiendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.team9.tiendapp.Activitys.AcercaDe;
import com.team9.tiendapp.Activitys.RecuperarContrasenia;
import com.team9.tiendapp.Activitys.Registro;

import Firebase.Autenticacion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button btn_registro;
    Button btn_iniciar;
    TextView recuperar;
    TextView acercade;
    EditText correo,contrasenia;

    Autenticacion autenticacion = new Autenticacion();


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = autenticacion.getmAuth().getCurrentUser();
        //updateUI(currentUser);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autenticacion.setmAuth(FirebaseAuth.getInstance());

        correo = findViewById(R.id.login_correo);
        contrasenia= findViewById(R.id.login_contrasenia);

        btn_iniciar = findViewById(R.id.login_btn_iniciar);
        btn_iniciar.setOnClickListener(this);
        recuperar = findViewById(R.id.login_olvido_contrasenia);
        recuperar.setOnClickListener(this);
        btn_registro = findViewById(R.id.login_btn_registrarse);
        btn_registro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intento_registro = new Intent(MainActivity.this, Registro.class);
                startActivity(intento_registro);
            }
        });

        acercade = findViewById(R.id.login_acercade);
        acercade.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent_acerca_de = new Intent(MainActivity.this, AcercaDe.class);
                startActivity(intent_acerca_de);
            }
        });

    }

    public void login(){
        autenticacion.getmAuth().signInWithEmailAndPassword(correo.getText().toString(),contrasenia.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Logica luego de loguearse
                            Toast.makeText(MainActivity.this,"Usuario logueado",Toast.LENGTH_SHORT).show();
                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w("Firebase", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case R.id.login_btn_iniciar:
                login();
                break;
            case R.id.login_olvido_contrasenia:
                iniciarActividad(RecuperarContrasenia.class);
        }
    }

    private void iniciarActividad(Class clase){
        Intent intent = new Intent(getApplicationContext(),clase);
        startActivity(intent);

    }
}

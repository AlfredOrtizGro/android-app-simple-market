package com.team9.tiendapp.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.team9.tiendapp.R;

import Firebase.Autenticacion;
import Firebase.Usuario;

public class Registro extends AppCompatActivity
{

    EditText correo, contrasenia, usuario;
    Button registrar;
    ImageView foto;

    private FirebaseAuth mAuth;
    private StorageReference mStorage;
    private static final int CAMERA_INTENT = 1;
    private DatabaseReference mDatabase;// ...

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mStorage = FirebaseStorage.getInstance().getReference();

        foto = findViewById(R.id.registro_foto);
        foto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, CAMERA_INTENT);
            }
        });

        usuario = findViewById(R.id.registro_usuario);
        correo = findViewById(R.id.registro_correo);
        contrasenia = findViewById(R.id.registro_contrasenia);
        registrar = findViewById(R.id.registro_btn_registrar);
        registrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                crear_usuario();
            }
        });
    }

    public void crear_usuario()
    {

        final String email = correo.getText().toString();
        String password = contrasenia.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            //Logica luego de loguearse
                            Usuario u = new Usuario(mAuth.getUid(), usuario.getText().toString(), email, "Software", 0f, 0f);
                            crear_usuario_bd(u);

                            Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        } else
                        {

                            // If sign in fails, display a message to the user.
                            Log.w("Firebase", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Registro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    public void crear_usuario_bd(Usuario usuario)
    {
        mDatabase.child("users").child(usuario.getUid()).setValue(usuario);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_INTENT && resultCode == RESULT_OK)
        {
            final Uri uri = data.getData();
            foto.setImageURI(uri);
            StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    foto.setImageURI(uri);
                    Toast.makeText(getApplicationContext(),"Se subio la imagen a la base de datos",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

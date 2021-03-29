package com.team9.tiendapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.team9.tiendapp.R;

public class RecuperarContrasenia extends AppCompatActivity implements View.OnClickListener
{

    EditText correo;
    Button btn_recuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasenia);

        correo = findViewById(R.id.recuperar_correo);
        btn_recuperar = findViewById(R.id.recuperar_btn_recuperar);
        btn_recuperar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.recuperar_btn_recuperar:
                //Solicitar reemplazo
                break;
            default:
                break;
        }
    }
}

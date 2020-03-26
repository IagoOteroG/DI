package com.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class Config extends AppCompatActivity {
    EditText nombre;
    EditText edad;
    TextView nacionalidad;
    private Locale locale;
    private Configuration config = new Configuration();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        nombre= (EditText) this.findViewById(R.id.nombre);
        edad= (EditText) this.findViewById(R.id.edad);
        nacionalidad= (TextView) this.findViewById(R.id.nacionalidad);
        Button saveBack = (Button) this.findViewById(R.id.back);
        Button change = (Button) this.findViewById(R.id.cambioIdioma);


        SharedPreferences prefs = getSharedPreferences("mispref",Context.MODE_PRIVATE );

        nombre.setText( prefs.getString("nombre","Carlos"));
        edad.setText( prefs.getString("edad","50"));
        nacionalidad.setText( prefs.getString("lng","English"));
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(Config.this);
                b.setTitle(getResources().getString(R.string.changeNationality));
                String[] types = getResources().getStringArray(R.array.languages);
                b.setItems(types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPreferences.Editor edit = getSharedPreferences("mispref", Context.MODE_PRIVATE ).edit();



                        switch(which){
                            case 0:
                                locale = new Locale("en");
                                config.locale =locale;
                                edit.putString("lng", "English");
                                edit.commit();
                                break;
                            case 1:
                                locale = new Locale("es");
                                edit.putString("lng", "Español");
                                edit.commit();
                                config.locale =locale;
                                break;
                        }
                        getResources().updateConfiguration(config, null);
                        Intent refresh = new Intent(Config.this, MainActivity.class);
                        startActivity(refresh);
                        finish();
                    }
                });
                b.show();
            }
        });
        saveBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Config.this.finish();
            }
        });
    }
    private void save(){


        SharedPreferences.Editor edit = getSharedPreferences("mispref", Context.MODE_PRIVATE ).edit();

            edit.putString("nombre", nombre.getText().toString());
            edit.putString("edad", edad.getText().toString());
            edit.putString("nacionalidad", nacionalidad.getText().toString());
        edit.commit();

    }

}

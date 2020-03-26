package com.example;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class Estadisticas extends AppCompatActivity {
    private ArrayList<Entrenamiento> listaEntrenamientos = new ArrayList<>();
    private DBManager gestorDB;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_estats);
        this.gestorDB= new DBManager(this);
        Button bt = (Button) this.findViewById(R.id.back);
        TextView estats= (TextView) this.findViewById(R.id.entrenador);
        TextView entrenTotals= (TextView) this.findViewById(R.id.nEntrenamientos);
        TextView vMedia= (TextView) this.findViewById(R.id.velocidadMedia);
        TextView disTotal= (TextView) this.findViewById(R.id.distTotal);
        TextView tiempoTotal= (TextView) this.findViewById(R.id.tiempoTotal);
        SharedPreferences prefs = getSharedPreferences("mispref", Context.MODE_PRIVATE );
        String nombre=prefs.getString("nombre","Please Config first");
        String edad=prefs.getString("edad","error");
        String nacionalidad=prefs.getString("lng","error");
        recuperar();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estadisticas.this.finish();
            }
        });
        estats.setText(nombre+" "+edad+" "+nacionalidad);
        entrenTotals.setText(String.valueOf(listaEntrenamientos.size()));
        int metros=0;
        int kilometros=0;
        int seg=0;
        int min=0;
        int horas=0;
        for(int x=0;x<listaEntrenamientos.size();x++){
            metros= listaEntrenamientos.get(x).getMetros();
            kilometros= listaEntrenamientos.get(x).getMetros();
            seg=listaEntrenamientos.get(x).getSegundos();
            min=listaEntrenamientos.get(x).getMinutos();
            horas=listaEntrenamientos.get(x).getHoras();
        }
        float segg=seg/3600;
        float minn=min/60;
        float horss=horas+segg+minn;//todo en horas
        float metro=metros/1000;
        float km=metro+kilometros;//todo en kilometros
        float result=km/horas;
        String.format("%.2f", result);
        vMedia.setText(String.format("%.2f", result)+" Km/h");
        disTotal.setText(String.valueOf(kilometros)+" Km, "+String.valueOf(metros)+" m");
        tiempoTotal.setText(String.valueOf(horas)+" h, "+String.valueOf(min)+" min, "+String.valueOf(seg)+" s.");
    }

    private void recuperar(){
    Cursor cursor = this.gestorDB.getEntrenamientos();
        if(cursor!=null){
        while(cursor.moveToNext()){
            int id = cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_ID)));
            String dato1=cursor.getString((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_NOMBRE )));
            String dato2=cursor.getString(cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_FECHA));
            int dato3=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_HORAS )));
            int dato4=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_MINUTOS )));
            int dato5=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_SEGUNDOS )));
            int dato6=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_KILOMETROS )));
            int dato7=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_METROS )));
            String dato8=cursor.getString((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_TIPO )));
            Entrenamiento entrenamiento = new Entrenamiento(dato1, dato2, dato3, dato4, dato5, dato6, dato7, dato8);
            listaEntrenamientos.add(entrenamiento);
        }

    }
    }
}

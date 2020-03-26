package com.example;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialog;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialogCallback;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class AddActivity extends AppCompatActivity implements SlideDatePickerDialogCallback {


    private int color;

    private TextView initialTextView;
    Button btFecha;
    int id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        this.gestorDB = new DBManager(getApplicationContext());

        final Button btAceptar = (Button) this.findViewById( R.id.aceptar );
        final Button btCancelar = (Button) this.findViewById( R.id.cancelar );
        final EditText edNombre = (EditText) this.findViewById( R.id.nombre );
        btFecha = (Button) this.findViewById(R.id.fecha);
        final EditText edHoras = (EditText) this.findViewById( R.id.horas );
        final EditText edMinutos = (EditText) this.findViewById( R.id.minutos );
        final EditText edSegundos = (EditText) this.findViewById( R.id.segundos );
        final EditText edKilometros = (EditText) this.findViewById( R.id.kilometros );
        final EditText edMetros = (EditText) this.findViewById( R.id.metros );
        Calendar date= Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        btFecha.setText(dateFormat.format(date.getTime()));


        InputFilter[] limiteNombre = new InputFilter[1];
        limiteNombre[0] = new InputFilter.LengthFilter(30);
        InputFilter[] limiteTiempo = new InputFilter[1];
        limiteTiempo[0] = new InputFilter.LengthFilter(2);
        InputFilter[] limiteDistancia = new InputFilter[1];
        limiteDistancia[0] = new InputFilter.LengthFilter(3);


        edNombre.setFilters(limiteNombre);
        edHoras.setFilters(limiteTiempo);
        edMinutos.setFilters(limiteTiempo);
        edSegundos.setFilters(limiteTiempo);
        edKilometros.setFilters(limiteDistancia);
        edMetros.setFilters(limiteDistancia);


        final Intent transitionIntent = this.getIntent();
        final int pos = transitionIntent.getExtras().getInt("pos");
        System.out.println(pos);
        if(pos==-100) {
             id = transitionIntent.getExtras().getInt("id", 0);
             System.out.println("aqui mi id"+id);
            final String nombre = transitionIntent.getExtras().getString("nombre", "ERROR");
            final String horas = transitionIntent.getExtras().getString("horas", "ERROR");
            final String minutos = transitionIntent.getExtras().getString("minutos", "ERROR");
            final String segundos = transitionIntent.getExtras().getString("segundos", "ERROR");
            final String kilometros = transitionIntent.getExtras().getString("kilometros", "ERROR");
            final String metros = transitionIntent.getExtras().getString("metros", "ERROR");
            final String fecha = transitionIntent.getExtras().getString("fecha", "ERROR");
            final String rb = transitionIntent.getExtras().getString("rb", "ERROR");
            System.out.println("aqui rbbbbbbbb"+rb);
            if(rb.equals("1")){
                 RadioButton rad0 = (RadioButton) this.findViewById(R.id.radioButton);
                rad0.setChecked(true);
            }else if(rb.equals("2")){
                RadioButton rad0 = (RadioButton) this.findViewById(R.id.radioButton2);
                rad0.setChecked(true);
            }else if(rb.equals("3")){
                RadioButton rad0 = (RadioButton) this.findViewById(R.id.radioButton3);
                rad0.setChecked(true);
            }

            edNombre.setText(nombre);
            edHoras.setText(horas);
            edMinutos.setText(minutos);
            edSegundos.setText(segundos);
            edKilometros.setText(kilometros);
            edMetros.setText(metros);
            btFecha.setText(fecha);

        }

        btFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar date= Calendar.getInstance();
                date.set(Calendar.YEAR, 2030);

                SlideDatePickerDialog.Builder builder = new SlideDatePickerDialog.Builder();
                builder.setEndDate(date);
                SlideDatePickerDialog dialog = builder.build();
                dialog.show(getSupportFragmentManager(), "Dialog");

            }
        });



        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddActivity.this.setResult(Activity.RESULT_CANCELED);
                AddActivity.this.finish();
            }
        });




        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mystring = getResources().getString(R.string.piscinaGrande);
                String mystring2 = getResources().getString(R.string.piscinaMedia);
                String mystring3 = getResources().getString(R.string.abierto);
               if(edNombre.getText().toString().equals("")|| btFecha.getText().toString().equals(R.string.fecha)||edHoras.getText().toString().equals("")||edMinutos.getText().toString().equals("")||edSegundos.getText().toString().equals("")||edKilometros.getText().toString().equals("")||edMetros.getText().toString().equals("")){
                   MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(AddActivity.this);
                   builder.setTitle(R.string.incompleto);
                   builder.setMessage(R.string.eIncompleto);
                   builder.setPositiveButton(R.string.reintentar,null);
                   builder.create().show();
                } else if(id==-1){
                    final String nombre = edNombre.getText().toString();
                    final String fecha = btFecha.getText().toString();
                    int horas = Integer.parseInt(edHoras.getText().toString());
                    int minutos = Integer.parseInt(edMinutos.getText().toString());

                    int segundos = Integer.parseInt(edSegundos.getText().toString());
                   while(segundos>=60){
                       segundos=segundos-60;
                       minutos=minutos+1;
                   }
                    while(minutos>=60){
                       minutos=minutos-60;
                       horas=horas+1;
                   }
                    int kilometros = Integer.parseInt(edKilometros.getText().toString());
                    int metros = Integer.parseInt(edMetros.getText().toString());
                   while(metros>=60){
                       metros=metros-60;
                       kilometros=kilometros+1;
                   }
                    RadioGroup rgTipo = (RadioGroup) AddActivity.this.findViewById( R.id.tipo );
                    int selectedId= rgTipo.getCheckedRadioButtonId();
                    final RadioButton resultTipo = (RadioButton) AddActivity.this.findViewById(selectedId);
                    String tipo = String.valueOf(resultTipo.getText());
                    if(tipo.equals(mystring)){
                        tipo="1";
                    }else if(tipo.equals(mystring2)){
                        tipo="2";
                    }else if(tipo.equals(mystring3)){
                        tipo="3";
                    }
                    final Intent retData = new Intent();
                    retData.putExtra("name", nombre);
                    retData.putExtra("color", color);
                    gestorDB.insertaEntrenamiento(nombre,fecha,horas,minutos,segundos,kilometros,metros,tipo);
                    gestorDB.close();
                    AddActivity.this.setResult(Activity.RESULT_OK, retData);
                    AddActivity.this.finish();
                }else{
                    final String nombre = edNombre.getText().toString();
                    final String fecha = btFecha.getText().toString();
                    int horas = Integer.parseInt(edHoras.getText().toString());
                    int minutos = Integer.parseInt(edMinutos.getText().toString());
                    int segundos = Integer.parseInt(edSegundos.getText().toString());
                   while(segundos>=60){
                       segundos=segundos-60;
                       minutos=minutos+1;
                   }
                   while(minutos>=60){
                       minutos=minutos-60;
                       horas=horas+1;
                   }
                     int kilometros = Integer.parseInt(edKilometros.getText().toString());
                     int metros = Integer.parseInt(edMetros.getText().toString());
                   while(metros>=60){
                       metros=metros-60;
                       kilometros=kilometros+1;
                   }
                    RadioGroup rgTipo = (RadioGroup) AddActivity.this.findViewById( R.id.tipo );
                    int selectedId= rgTipo.getCheckedRadioButtonId();
                    final RadioButton resultTipo = (RadioButton) AddActivity.this.findViewById(selectedId);
                   String tipo = String.valueOf(resultTipo.getText());
                   if(tipo.equals(mystring)){
                       tipo="1";
                   }else if(tipo.equals(mystring2)){
                       tipo="2";
                   }else if(tipo.equals(mystring3)){
                       tipo="3";
                   }

                    gestorDB.modificaContacto(AddActivity.this.id,nombre, fecha,horas,minutos,segundos,kilometros,metros,tipo);
                    gestorDB.close();
                    final Intent retData = new Intent();
                    retData.putExtra("name", nombre);
                    retData.putExtra("color", color);
                    AddActivity.this.setResult(Activity.RESULT_OK, retData);
                    AddActivity.this.finish();

                }
            }
        });

    }
    private int year;
    private int month;
    private int day;
    private DBManager gestorDB;

    @Override
    public void onPositiveClick(int i, int i1, int i2, Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        btFecha.setText(dateFormat.format(calendar.getTime()));

    }
}

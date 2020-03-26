package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBManager gestorDB;
    private RecyclerView recyclerView;
    private int[] colors;
    private ArrayList<Entrenamiento> listaEntrenamientos = new ArrayList<>();
    public static final String TRANSITION_FAB = "fab_transition";
    public static final String TRANSITION_INITIAL = "initial_transition";
    public static final String TRANSITION_NAME = "name_transition";
    public static final String TRANSITION_DELETE_BUTTON = "delete_button_transition";
    private RecycleViewAdapter adapter;
    public static final String EXTRA_UPDATE = "update";
    public static final String EXTRA_DELETE = "delete";
    public static final String EXTRA_COLOR = "color";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadActivity();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        this.getMenuInflater().inflate(R.menu.actions_menu, menu);
        return true;
    }
    private void loadActivity() {
        listaEntrenamientos.clear();
        this.gestorDB= new DBManager(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        colors = getResources().getIntArray(R.array.initial_colors);
        initCards();
        if (adapter == null) {
            adapter = new RecycleViewAdapter(this, listaEntrenamientos);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pair<View, String> pair = Pair.create(v.findViewById(R.id.fab), TRANSITION_FAB);
                ActivityOptionsCompat options;
                Activity act = MainActivity.this;
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, pair);
                Intent transitionIntent = new Intent(act, AddActivity.class);
                transitionIntent.putExtra("pos",-1);
                act.startActivityForResult(transitionIntent, adapter.getItemCount(), options.toBundle());
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean toret = false;

        switch (menuItem.getItemId()) {
            case R.id.opAdd:
                Pair<View, String> pair = Pair.create(findViewById(R.id.fab), TRANSITION_FAB);
                ActivityOptionsCompat options;
                Activity act = MainActivity.this;
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, pair);
                Intent transitionIntent = new Intent(act, AddActivity.class);
                transitionIntent.putExtra("pos",-1);
                act.startActivityForResult(transitionIntent, adapter.getItemCount(), options.toBundle());
                toret = true;
                break;
            case R.id.opEstadisticas:
                Intent subActividad = new Intent(MainActivity.this, Estadisticas.class);
                MainActivity.this.startActivity(subActividad);
                break;
            case R.id.opConfiguracion:
                Intent subActivida= new Intent(MainActivity.this, Config.class);
                MainActivity.this.startActivity(subActivida);
                break;
            case R.id.opEliminar:
                String[] strings = new String[listaEntrenamientos.size()];
                final boolean[] selected = new boolean[listaEntrenamientos.size()];

                for (int i=0;i<listaEntrenamientos.size();i++){
                    strings[i] = listaEntrenamientos.get(i).toString();
                    selected[i]=false;
                }

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
                builder.setTitle(R.string.deseaEliminar);
                builder.setMultiChoiceItems(strings, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which] =  isChecked;
                    }
                });
                builder.setNegativeButton(R.string.cancelar, null);
                builder.setPositiveButton(R.string.eliminar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int aux = 0;
                        for(int i = 0; i<listaEntrenamientos.size();i++){
                            if (selected[i]){
                                adapter.animateCircularDelete(null, (i-aux));
                                aux++;
                            }
                        }
                    }
                });
                builder.create().show();
                break;
            case R.id.opModificar:

                String[] strings2 = new String[listaEntrenamientos.size()];
                final Integer[] selected2 = new Integer[1];
                selected2[0]=0;

                for (int i=0;i<listaEntrenamientos.size();i++){
                    strings2[i] = listaEntrenamientos.get(i).toString();
                }
                MaterialAlertDialogBuilder builder2 = new MaterialAlertDialogBuilder(this);
                builder2.setTitle("¿Qué entrenamiento desea modificar?");
                builder2.setSingleChoiceItems(strings2, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected2[0] = which;
                    }
                });
                builder2.setNegativeButton("Cancelar", null);
                builder2.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, AddActivity.class);
                        intent.putExtra("pos",-100);
                        Entrenamiento entre= new Entrenamiento();
                        entre = listaEntrenamientos.get(selected2[0]);
                        intent.putExtra("id",entre.getId());
                        intent.putExtra("nombre",entre.getNombre());
                        intent.putExtra("horas",String.valueOf(entre.getHoras()));
                        intent.putExtra("minutos",String.valueOf(entre.getMinutos()));
                        intent.putExtra("segundos",String.valueOf(entre.getSegundos()));
                        intent.putExtra("kilometros",String.valueOf(entre.getKilometros()));
                        intent.putExtra("metros",String.valueOf(entre.getMetros()));
                        intent.putExtra("fecha",String.valueOf(entre.getFecha()));
                        intent.putExtra("rb",String.valueOf(entre.getTipo()));
                        MainActivity.this.startActivity(intent);
                    }
                });
                builder2.create().show();
                break;

        }

        return toret;
    }
    public void doSmoothScroll(int position) {
        recyclerView.smoothScrollToPosition(position);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loadActivity();

    }

    private void initCards() {

        Cursor cursor = this.gestorDB.getEntrenamientos();

        if(cursor!=null){
            while(cursor.moveToNext()){
                int id = cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_ID)));
                String dato1=cursor.getString((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_NOMBRE )));
                String dato2=cursor.getString(cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_FECHA));
                System.out.println(dato2);
                int dato3=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_HORAS )));
                int dato4=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_MINUTOS )));
                int dato5=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_SEGUNDOS )));
                int dato6=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_KILOMETROS )));
                int dato7=cursor.getInt((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_METROS )));
                String dato8=cursor.getString((cursor.getColumnIndex(DBManager.ENTRENAMIENTO_COL_TIPO )));
                Entrenamiento entrenamiento = new Entrenamiento(dato1, dato2, dato3, dato4, dato5, dato6, dato7, dato8);
                entrenamiento.setId(id);
                entrenamiento.setColorResource(id);
                listaEntrenamientos.add(entrenamiento);
            }

        }

    }
}

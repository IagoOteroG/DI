package com.example;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;

class RecycleViewAdapter  extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    protected static final int CODIGO_EDICION_ITEM = 100;
    protected static final int CODIGO_ADICION_ITEM = 102;
    public Context context;
    private static final String DEBUG_TAG = "TrainingRecycleAdapter";
    private DBManager gestorDB;
    public  ArrayList<Entrenamiento> listaEntrenamientos;

    public RecycleViewAdapter(Context context, ArrayList<Entrenamiento> listaEntrenamientos) {
        this.context = context;
        this.listaEntrenamientos = listaEntrenamientos;
        gestorDB = new DBManager(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.card_view_holder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        String name = listaEntrenamientos.get(position).getNombre();
        int color = listaEntrenamientos.get(position).getColorResource();
        TextView initial = holder.initial;
        TextView nameTextView = holder.name;
        nameTextView.setText(name);
        initial.setBackgroundColor(color);
        initial.setText(Character.toString(name.charAt(0)));
    }
    @Override
    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }
    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }
    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }
    public void animateCircularDelete(final View view, final int list_position) {
        try{
            if(view!=null) {
                int centerX = view.getWidth();
                int centerY = view.getHeight();
                int startRadius = view.getWidth();
                int endRadius = 0;
                Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

                animation.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        Log.d(DEBUG_TAG, "SampleMaterialAdapter onAnimationEnd for Edit adapter position " + list_position);
                        Log.d(DEBUG_TAG, "SampleMaterialAdapter onAnimationEnd for Edit cardId " + getItemId(list_position));

                        view.setVisibility(View.INVISIBLE);
                        gestorDB.eliminarEntrenamiento(listaEntrenamientos.get(list_position).getId());
                        listaEntrenamientos.remove(list_position);
                        notifyItemRemoved(list_position);
                    }
                });
                animation.start();
            }else{
                gestorDB.eliminarEntrenamiento(listaEntrenamientos.get(list_position).getId());
                listaEntrenamientos.remove(list_position);
                notifyItemRemoved(list_position);
            }
    }catch (Exception e){}
    }
    public void updateCard(String name, int list_position) {
        listaEntrenamientos.get(list_position).setNombre(name);
        notifyItemChanged(list_position);
    }
    public void addCard(String nombre, int color) {
        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setNombre(nombre);
        entrenamiento.setColorResource(color);
        listaEntrenamientos.add(entrenamiento);
        ((MainActivity) context).doSmoothScroll(getItemCount());
        notifyItemInserted(getItemCount());
    }
    @Override
    public int getItemCount() {
        if (listaEntrenamientos.isEmpty()) {
            return 0;
        } else {
            return listaEntrenamientos.size();
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        private TextView initial;
        private TextView name;
        private Button deleteButton;

        public ViewHolder(View v) {
            super(v);
            v.setOnCreateContextMenuListener(this);
            initial = (TextView) v.findViewById(R.id.initial);
            name = (TextView) v.findViewById(R.id.name);
            deleteButton = (Button) v.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                    builder.setTitle(R.string.eliminar);
                    builder.setMessage(R.string.alertdialog);
                    builder.setPositiveButton(R.string.eliminar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            animateCircularDelete(itemView, getAdapterPosition());
                        }
                    });
                    builder.setNegativeButton(R.string.cancelar, null);
                    builder.create().show();
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pair<View, String> p1 = Pair.create((View) initial, MainActivity.TRANSITION_INITIAL);
                    Pair<View, String> p2 = Pair.create((View) name, MainActivity.TRANSITION_NAME);
                    Pair<View, String> p3 = Pair.create((View) deleteButton, MainActivity.TRANSITION_DELETE_BUTTON);

                    ActivityOptionsCompat options;
                    Activity act = (AppCompatActivity) context;
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, p1, p2, p3);

                    int requestCode = getAdapterPosition();
                    Entrenamiento entre= new Entrenamiento();
                    entre= listaEntrenamientos.get(getAdapterPosition());
                    String name = listaEntrenamientos.get(requestCode).getNombre();
                    int color = listaEntrenamientos.get(requestCode).getColorResource();
                    Intent transitionIntent = new Intent(context, AddActivity.class);
                    transitionIntent.putExtra("pos",-100);
                    transitionIntent.putExtra("id",entre.getId());
                    transitionIntent.putExtra("nombre",entre.getNombre());
                    transitionIntent.putExtra("horas",String.valueOf(entre.getHoras()));
                    transitionIntent.putExtra("minutos",String.valueOf(entre.getMinutos()));
                    transitionIntent.putExtra("segundos",String.valueOf(entre.getSegundos()));
                    transitionIntent.putExtra("kilometros",String.valueOf(entre.getKilometros()));
                    transitionIntent.putExtra("metros",String.valueOf(entre.getMetros()));
                    transitionIntent.putExtra("fecha",String.valueOf(entre.getFecha()));
                    transitionIntent.putExtra("rb",String.valueOf(entre.getTipo()));
                    transitionIntent.putExtra(MainActivity.EXTRA_COLOR, color);
                    transitionIntent.putExtra(MainActivity.EXTRA_UPDATE, false);
                    transitionIntent.putExtra(MainActivity.EXTRA_DELETE, false);
                    ((AppCompatActivity) context).startActivityForResult(transitionIntent, CODIGO_EDICION_ITEM, options.toBundle());
                }
            });

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()){
                case 1:

                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                    builder.setTitle(R.string.eliminar);
                    builder.setMessage(R.string.alertdialog);
                    builder.setPositiveButton(R.string.eliminar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            animateCircularDelete(itemView, getAdapterPosition());
                        }
                    });
                    builder.setNegativeButton(R.string.cancelar, null);
                    builder.create().show();
                    break;

            }
            return true;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem borrar = menu.add(Menu.NONE, 1, 1, R.string.eliminar);
            borrar.setOnMenuItemClickListener(this);
        }
    }
}

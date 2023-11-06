package com.example.dnd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonajeAdaptador extends RecyclerView.Adapter<PersonajeAdaptador.ViewHolder> {
    private final List<Personaje> mData;
    private final LayoutInflater mInflater;
    private final Context context;
    private final Map<String, Integer> razaImagenMap = new HashMap<>();
    public PersonajeAdaptador(List<Personaje> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

        razaImagenMap.put("Elfo", R.mipmap.elfo);
        razaImagenMap.put("Humano", R.mipmap.humano);
        razaImagenMap.put("Drow", R.mipmap.drow);
        razaImagenMap.put("Draconido", R.mipmap.draconico);
        razaImagenMap.put("Gnomo", R.mipmap.gnomo);
        razaImagenMap.put("Enano", R.mipmap.enano);
        razaImagenMap.put("Semiorco", R.mipmap.semiorco);
        razaImagenMap.put("Mediano", R.mipmap.mediano);
        razaImagenMap.put("Semielfo", R.mipmap.semielfo);
        razaImagenMap.put("Githyanki", R.mipmap.gith);
        razaImagenMap.put("Tiefling", R.mipmap.tiefling);
    }

    @NonNull
    @Override
    public PersonajeAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_personajes, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PersonajeAdaptador.ViewHolder holder,final int position) {
        holder.bindData(mData.get(position));

        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicionAEliminar = holder.getAdapterPosition();
                if (posicionAEliminar != RecyclerView.NO_POSITION) {
                    mostrarConfirmacionDeEliminar(posicionAEliminar);
                }
            }
        });


        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positionToEdit = holder.getAdapterPosition();
                if (positionToEdit != RecyclerView.NO_POSITION) {
                    Personaje personaje = mData.get(positionToEdit);

                    Intent intent = new Intent(context, CreacionPersonaje.class);
                    intent.putExtra("editarPersonaje", personaje);
                    context.startActivity(intent);
                }
            }
        });
    }
    private void mostrarConfirmacionDeEliminar(final int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmar Eliminación");
        builder.setMessage("¿Estás seguro de que deseas eliminar tu personaje?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int personajeId = mData.get(posicion).getId();
                if (eliminarPersonajeEnBD(personajeId)) {
                    mData.remove(posicion);
                    notifyItemRemoved(posicion);
            }
        }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private boolean eliminarPersonajeEnBD(int personajeId) {
        daoPersonaje dao = new daoPersonaje(context);
        return dao.eliminar(personajeId);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRaza;
        TextView txtNombrePersonaje, txtClasePersonaje, txtGeneroPersonaje, txtTrasfondoPersonaje;
        Button eliminar;
        Button editar;

        ViewHolder(View itemView) {
            super(itemView);
            imgRaza = itemView.findViewById(R.id.imgRaza);
            txtNombrePersonaje = itemView.findViewById(R.id.txtNombrePersonaje);
            txtClasePersonaje = itemView.findViewById(R.id.txtClasePersonaje);
            txtGeneroPersonaje = itemView.findViewById(R.id.txtGeneroPersonaje);
            txtTrasfondoPersonaje = itemView.findViewById(R.id.txtTrasfondoPersonaje);
            eliminar = itemView.findViewById(R.id.btnEliminar);
            editar = itemView.findViewById(R.id.btnEditar);

        }

        void bindData(final Personaje personaje) {

            String raza = personaje.getRaza();
            int imagenRaza = razaImagenMap.containsKey(raza) ? razaImagenMap.get(raza) : R.mipmap.mipersonaje;

            imgRaza.setImageResource(imagenRaza);
            txtNombrePersonaje.setText(personaje.getNombre());
            txtClasePersonaje.setText(personaje.getClase());
            txtGeneroPersonaje.setText(personaje.getGenero());
            txtTrasfondoPersonaje.setText(personaje.getTrasfondo());
        }


    }

}

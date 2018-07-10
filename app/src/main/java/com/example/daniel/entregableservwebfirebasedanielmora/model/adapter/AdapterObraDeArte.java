package com.example.daniel.entregableservwebfirebasedanielmora.model.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.entregableservwebfirebasedanielmora.R;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ObraDeArte;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterObraDeArte extends RecyclerView.Adapter {

    private List<ObraDeArte> obras;
    private Context context;
    private NotificadorCelda notificadorCelda;

    public AdapterObraDeArte(Context context, NotificadorCelda notificadorCelda) {
        this.context = context;
        this.obras = new ArrayList<>();
        this.notificadorCelda = notificadorCelda;
    }

    public void setObras(List<ObraDeArte> obras) {
        this.obras = obras;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_recycler_obra, parent, false);
        ViewHolderObra viewHolderObra = new ViewHolderObra(view);
        return viewHolderObra;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ObraDeArte obraDeArte = obras.get(position);
        ViewHolderObra viewHolderObra = (ViewHolderObra) holder;
        viewHolderObra.armarCeldaObras(obraDeArte);
    }

    @Override
    public int getItemCount() {
        if (obras != null) {
            return obras.size();
        } else {
            return 0;
        }
    }

    public void agregarObras(List<ObraDeArte> resultado) {
        for (ObraDeArte obraDeArteAAgregar : resultado) {
            if (!this.obras.contains(obraDeArteAAgregar)) {
                this.obras.add(obraDeArteAAgregar);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolderObra extends RecyclerView.ViewHolder {

        private ImageView imagenCelda;
        private TextView textViewTitulo;

        public ViewHolderObra(final View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.titulo_celda_recycler_obra);
            imagenCelda = itemView.findViewById(R.id.imagen_celda_recycler_obra);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int celdaClickeada = getAdapterPosition();
                    notificadorCelda.notificarCeldaCliqueada(obras, celdaClickeada);
                }
            });
        }

        public void armarCeldaObras(ObraDeArte obraDeArte) {
            textViewTitulo.setText(obraDeArte.getNombreObra());
            Picasso.get().load(obraDeArte.getImage()).placeholder(R.drawable.placeholder).into(imagenCelda);
        }
    }

    public interface NotificadorCelda {
        public void notificarCeldaCliqueada(List<ObraDeArte> obrasDeArte, int posicion);
    }

}

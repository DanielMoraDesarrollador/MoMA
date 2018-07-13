package com.example.daniel.entregableservwebfirebasedanielmora.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniel.entregableservwebfirebasedanielmora.R;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ObraDeArte;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleObra extends Fragment {

    private static final String OBRA_RECIBIDA = "obra_recibida";

    private ImageView imagenGrande;
    private TextView nombreObra;
    private TextView nombreArtista;
    private TextView nacionalidad;
    private TextView influenciadoPor;
    private ObraDeArte obraDeArte;
    private FirebaseStorage storage;
    private StorageReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_obra, container, false);

        imagenGrande = view.findViewById(R.id.imagen_obra_grande);
        nombreObra = view.findViewById(R.id.nombre_obra);
        nombreArtista = view.findViewById(R.id.nombre_artista);
        nacionalidad = view.findViewById(R.id.nacionalidad);
        influenciadoPor = view.findViewById(R.id.influenciado_por);

        nombreObra.setSelected(true);
        nombreArtista.setSelected(true);
        nacionalidad.setSelected(true);
        influenciadoPor.setSelected(true);

        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();

        Bundle bundle = getArguments();
        obraDeArte = (ObraDeArte) bundle.getSerializable(OBRA_RECIBIDA);

        nombreObra.setText(obraDeArte.getNombreObra());
        //Picasso.get().load(obraDeArte.getImage()).placeholder(R.drawable.placeholder).into(imagenGrande);
        cargarImagenesGrandeDescargadas(obraDeArte.getImage());
        return view;
    }

    private void cargarImagenesGrandeDescargadas(String imagenDescargada) {
        if (TextUtils.isEmpty(imagenDescargada)) {
            return;
        }
        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(reference.child(imagenDescargada))
                .placeholder(R.drawable.placeholder)
                .into(imagenGrande);
    }

    public static FragmentDetalleObra dameUnFragment(ObraDeArte obraDeArte) {
        FragmentDetalleObra fragmentDetalleObraCreado = new FragmentDetalleObra();
        Bundle bundle = new Bundle();
        bundle.putSerializable(OBRA_RECIBIDA, obraDeArte);
        fragmentDetalleObraCreado.setArguments(bundle);
        return fragmentDetalleObraCreado;
    }
}

package com.example.daniel.entregableservwebfirebasedanielmora.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daniel.entregableservwebfirebasedanielmora.R;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.Artista;
import com.example.daniel.entregableservwebfirebasedanielmora.model.pojo.ObraDeArte;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleObra
        extends Fragment {

    private static final String OBRA_RECIBIDA = "obra_recibida";
    private static final String ARTISTS = "artists";

    private ImageView imagenGrande;
    private TextView nombreObra;
    private TextView nombreArtista;
    private TextView nacionalidad;
    private TextView influenciadoPor;
    private ObraDeArte obraDeArte;
    private FirebaseStorage storage;
    private StorageReference reference;
    private FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
        database = FirebaseDatabase.getInstance();

        Bundle bundle = getArguments();
        obraDeArte = (ObraDeArte) bundle.getSerializable(OBRA_RECIBIDA);

        nombreObra.setText(obraDeArte.getNombreObra());

        cargarImagenesGrandeDescargadas(obraDeArte.getImage());
        cargarDetalleObra(obraDeArte);

        return view;
    }

    private void cargarImagenesGrandeDescargadas(String imagenDescargada) {
        if (TextUtils.isEmpty(imagenDescargada)) {
            return;
        }
        Glide.with(getContext())
                .using(new FirebaseImageLoader())
                .load(reference.child(imagenDescargada))
                .into(imagenGrande);
    }

    private void cargarDetalleObra(ObraDeArte obra) {
        DatabaseReference dataReference = database.getReference()
                .child(ARTISTS)
                .child(obra.getArtistId());

        dataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Artista artista = dataSnapshot.getValue(Artista.class);
                    nombreArtista.setText(artista.getNombreArtista());
                    nacionalidad.setText(artista.getNacionalidad());
                    influenciadoPor.setText(artista.getInfluenciadoPor());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error de carga detalle", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static FragmentDetalleObra dameUnFragment(ObraDeArte obraDeArte) {
        FragmentDetalleObra fragmentDetalleObraCreado = new FragmentDetalleObra();
        Bundle bundle = new Bundle();
        bundle.putSerializable(OBRA_RECIBIDA, obraDeArte);
        fragmentDetalleObraCreado.setArguments(bundle);
        return fragmentDetalleObraCreado;
    }
}

package com.example.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_b extends Fragment {

    private TextView textView;

    // Constructor vacío (obligatorio)
    public Fragment_b() {
    }

    // Método para instanciar el fragment (opcional)
    public static Fragment_b newInstance(String param1, String param2) {
        Fragment_b fragment = new Fragment_b();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("param1");
            String mParam2 = getArguments().getString("param2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    // Aquí añadimos el código para manejar el TextView
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textFragmentB);
    }

    // Método para recibir mensajes desde Fragment_a
    public void recibir(String msg) {
        textView.setText(msg);
    }
}

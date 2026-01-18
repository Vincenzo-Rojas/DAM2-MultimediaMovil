package com.example.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_a extends Fragment {

    private EditText editText;
    private Button button;
    private EnviarMensaje enviarMensaje;

    // Constructor vacío (obligatorio)
    public Fragment_a() {
    }

    // Método para instanciar el fragment (opcional si no necesitas parámetros)
    public static Fragment_a newInstance(String param1, String param2) {
        Fragment_a fragment = new Fragment_a();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recuperar argumentos si es necesario
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("param1");
            String mParam2 = getArguments().getString("param2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    // Aquí añadimos tu código para EditText, Button y comunicación
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = view.findViewById(R.id.editTextA);
        button = view.findViewById(R.id.buttonA);

        enviarMensaje = (EnviarMensaje) getActivity();

        button.setOnClickListener(v -> {
            String mensaje = editText.getText().toString();
            enviarMensaje.enviarDato(mensaje);
        });
    }
}

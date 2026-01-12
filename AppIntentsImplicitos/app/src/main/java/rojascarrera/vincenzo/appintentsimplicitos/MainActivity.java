package rojascarrera.vincenzo.appintentsimplicitos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnWeb, btnTelefono, btnEmail, btnGaleria;

    private static final int PERMISO_CALL_PHONE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los botones
        btnWeb = findViewById(R.id.btnWeb);
        btnTelefono = findViewById(R.id.btnTelefono);
        btnEmail = findViewById(R.id.btnEmail);
        btnGaleria = findViewById(R.id.btnGaleria);

        // Listener para abrir navegador
        btnWeb.setOnClickListener(v -> abrirWeb());

        // Listener para marcar teléfono
        btnTelefono.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Verifica permiso CALL_PHONE si usas ACTION_CALL
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISO_CALL_PHONE);
                } else {
                    llamarTelefono();
                }
            } else {
                llamarTelefono();
            }
        });

        // Listener para enviar correo electrónico
        btnEmail.setOnClickListener(v -> enviarEmail());

        // Listener para abrir galería
        btnGaleria.setOnClickListener(v -> abrirGaleria());
    }

    // Abrir navegador
    private void abrirWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No hay aplicación para abrir la web", Toast.LENGTH_SHORT).show();
        }
    }

    // Llamar teléfono (acción CALL)
    private void llamarTelefono() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:600123123"));
        try{
            startActivity(intent);
        } catch (SecurityException e) {
            Toast.makeText(this, "No tienes permiso para llamar", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "No hay aplicación de teléfono", Toast.LENGTH_SHORT).show();
        }
    }

    // Enviar correo
    private void enviarEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ejemplo@correo.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Saludo");
        intent.putExtra(Intent.EXTRA_TEXT, "Hola, este es un mensaje de prueba");

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No hay aplicación de correo", Toast.LENGTH_SHORT).show();
        }
    }

    // Abrir galería
    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No hay aplicación de galería", Toast.LENGTH_SHORT).show();
        }
    }

    // Manejo de la respuesta del permiso CALL_PHONE
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                llamarTelefono();
            } else {
                Toast.makeText(this, "Permiso denegado para realizar llamadas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

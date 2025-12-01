package Rojas.Vincenzo.examen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Elementos de la UI
    private RadioGroup rgTipoCombustible;
    private EditText etLitros, etDescuento;
    private CheckBox cbDescuento;
    private ImageView imgDescuento;
    private TextView tvMensaje, tvDescuento;
    private Button btnLlenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de elementos
        rgTipoCombustible = findViewById(R.id.rgTipoCombustible);
        etLitros = findViewById(R.id.etLitros);
        etDescuento = findViewById(R.id.etCodigoDescuento);
        cbDescuento = findViewById(R.id.chkDescuento);
        imgDescuento = findViewById(R.id.ivDescuento);
        tvMensaje = findViewById(R.id.tvMensaje);
        tvDescuento = findViewById(R.id.tvDescuentoAplicado);
        btnLlenar = findViewById(R.id.btnLlenar);

        // Ocultar inicialmente la imagen y el mensaje de descuento
        imgDescuento.setVisibility(View.GONE);
        tvDescuento.setVisibility(View.GONE);

        // Listener del botón
        btnLlenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facturar();
            }
        });
    }

    private boolean validar() {
        // Validar que se haya seleccionado un tipo de combustible
        if (rgTipoCombustible.getCheckedRadioButtonId() == -1) {
            tvMensaje.setText("Seleccione un tipo de combustible");
            return false;
        }

        // Validar que los litros no estén vacíos y sean números
        String litrosStr = etLitros.getText().toString().trim();
        if (litrosStr.isEmpty()) {
            tvMensaje.setText("Indique el número de litros");
            return false;
        }

        try {
            int litros = Integer.parseInt(litrosStr);
            if (litros <= 0) {
                tvMensaje.setText("El número de litros debe ser mayor que 0");
                return false;
            }
        } catch (NumberFormatException e) {
            tvMensaje.setText("Ingrese un número válido de litros");
            return false;
        }

        // Validar descuento si está marcado el checkbox
        if (cbDescuento.isChecked()) {
            String desc = etDescuento.getText().toString().trim();
            if (!desc.equalsIgnoreCase("AAA")) {
                tvMensaje.setText("El texto del descuento es incorrecto");
                return false;
            }
        }

        // Todo correcto
        return true;
    }

    private void facturar() {

        if(!validar())
            return;

        int checkedId = rgTipoCombustible.getCheckedRadioButtonId();

        // Encuentra el RadioButton seleccionado
        RadioButton rbSeleccionado = findViewById(checkedId);
        String nombreCombustible = rbSeleccionado.getText().toString();

        // Asigna el precio según el texto
        double precioPorLitro = 0.0;
        switch(nombreCombustible) {
            case "SP/98":
                precioPorLitro = 1.99;
                break;
            case "SP/95":
                precioPorLitro = 1.65;
                break;
            case "Diesel A":
                precioPorLitro = 1.59;
                break;
            case "Diesel B":
                precioPorLitro = 1.39;
                break;
        }

        // Obtener la cantidad de litros
        int litros = Integer.parseInt(etLitros.getText().toString().trim());
        double importe = litros * precioPorLitro;

        // Variables para los mensajes finales
        String mensajeCompra = getString(R.string.rCompra);
        String mensaje = String.format(mensajeCompra, litros, nombreCombustible, importe);;
        tvMensaje.setText(mensaje);

        // Aplicar descuento si corresponde
        if (cbDescuento.isChecked()) {
            importe = importe * 0.95; // Descuento del 5%
            mensajeCompra = getString(R.string.rDescuento);
            mensaje = String.format(mensajeCompra,importe);
            tvDescuento.setText(mensaje);

            imgDescuento.setVisibility(View.VISIBLE);
            tvDescuento.setVisibility(View.VISIBLE);

        } else {
            // Si no está marcado el checkbox, ocultar imagen y mensaje de descuento
            imgDescuento.setVisibility(View.GONE);
            tvDescuento.setVisibility(View.GONE);
        }


    }


}

package app.examen2;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements AccionesDialogo{

    EditText etPW, etUser;
    ImageButton bImagen;
    Button bAceptar;
    CrearBD bd;

    Boolean autorizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etPW = findViewById(R.id.etPW);
        etUser = findViewById(R.id.etUser);

        bImagen = findViewById(R.id.imageButton);
        bAceptar = findViewById(R.id.button1);
        autorizacion = false;

        bd = new CrearBD(this);

        bAceptar.setOnClickListener(v -> comprobarUsuario());


        bImagen.setOnClickListener(v -> verLibro());


    }

    @Override
    protected void onRestart(){
        super.onRestart();
        limpiarCajas();
        autorizacion = false;
    };


    private void verLibro() {
        if(autorizacion){
            startActivity(new Intent(this, verLibros.class));
        }
    }

    private void comprobarUsuario() {

        if (etUser.getText().toString().isEmpty()) {
            verMensajeToast(getString(R.string.campoUser));
            return;
        }

        if (etPW.getText().toString().isEmpty() ){
            verMensajeToast(getString(R.string.campoPassW));
            return;
        }

        //Mirar si ese usuario existe en la BD, si no existe, mandar aviso para crearlo
        SQLiteDatabase db = bd.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT password FROM " + CrearBD.TABLA_USUARIO +
                        " WHERE usuario=?",
                new String[]{etUser.getText().toString()}
        );

        if (c.moveToFirst()) {
            //Si las contraseñas son iguales, habilita acceder a la lista de libros
            if(c.getString(0).equals(etPW.getText().toString())){
                autorizacion = true;
                verMensajeToast(getString(R.string.ok));
            }else{
                autorizacion = false;
                verMensajeToast(getString(R.string.notOk));
                etPW.setText("");
            }

        } else {
            verMensajeToast(getString(R.string.notExists));
            autorizacion = false;
            //Se procede con la ventana de aviso, por si quiere crearlo
            //Si pulsa que si, se ayade
            mostrarDialogo();

        }

        c.close();
        db.close();
    }

    private void ayadirUser() {
        try {
            SQLiteDatabase db = bd.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("usuario", etUser.getText().toString());
            values.put("password", etPW.getText().toString());

            db.insertOrThrow(CrearBD.TABLA_USUARIO, null, values);
            db.close();

            verMensajeToast(getString(R.string.insertadoOK));

        } catch (Exception e) {
            verMensajeToast(getString(R.string.insertadoNotOK));
        }
    }

    public void verMensajeToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void limpiarCajas() {
        etPW.setText("");
        etUser.setText("");
    }

    public void mostrarDialogo() {
        Dialogo dialogo = new Dialogo();
        dialogo.show(getSupportFragmentManager(), "tagDialogo");
    }

    // Acción al pulsar OK
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        ayadirUser();
    }

    // Acción al pulsar Cancelar
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show();
        limpiarCajas();
    }

}
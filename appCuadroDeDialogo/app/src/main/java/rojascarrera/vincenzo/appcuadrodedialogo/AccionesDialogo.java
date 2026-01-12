package rojascarrera.vincenzo.appcuadrodedialogo;


import androidx.fragment.app.DialogFragment;

public interface AccionesDialogo {
    void onDialogPositiveClick(DialogFragment dialog);  // OK
    void onDialogNegativeClick(DialogFragment dialog);  // Cancelar
}
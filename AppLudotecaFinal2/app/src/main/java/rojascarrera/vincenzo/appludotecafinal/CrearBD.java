package rojascarrera.vincenzo.appludotecafinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Esta clase se encarga de crear y gestionar la base de datos
public class CrearBD extends SQLiteOpenHelper {

    // Nombre del archivo de la base de datos
    private static final String NOMBRE_BD = "LudotecaFinal.db";

    // Version de la base de datos
    private static final int VERSION = 1;

    // Constructor
    public CrearBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    // Se ejecuta la primera vez que se crea la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Creamos la tabla de videos
        String sql = "CREATE TABLE videosFinal (" +
                "referencia TEXT PRIMARY KEY," +
                "titulo TEXT," +
                "autor TEXT," +
                "tema TEXT," +
                "duracion INTEGER," +
                "ruta TEXT," +
                "visitas INTEGER)";

        db.execSQL(sql);
    }

    // Se ejecuta cuando cambia la version de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Se borra la tabla antigua
        db.execSQL("DROP TABLE IF EXISTS videosFinal");

        // Se vuelve a crear
        onCreate(db);
    }
}

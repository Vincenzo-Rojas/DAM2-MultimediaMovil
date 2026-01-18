package rojascarrera.vincenzo.appbasededatos6_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrearDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "articulos.db";
    public static final int DB_VERSION = 1;

    public CrearDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE articulos (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre TEXT)"
        );

        // Datos iniciales
        db.execSQL("INSERT INTO articulos (nombre) VALUES ('Teclado')");
        db.execSQL("INSERT INTO articulos (nombre) VALUES ('Raton')");
        db.execSQL("INSERT INTO articulos (nombre) VALUES ('Pantalla')");
        db.execSQL("INSERT INTO articulos (nombre) VALUES ('Portatil')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS articulos");
        onCreate(db);
    }
}

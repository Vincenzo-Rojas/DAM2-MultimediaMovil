package rojas.vincenzo.proyectoludoteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrearBD extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "LudotecaVincenzo.db";
    public static final int VERSION = 1;

    public static final String TABLA_VIDEOS = "videosVincenzo";

    public CrearBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLA_VIDEOS + " (" +
                "referencia TEXT PRIMARY KEY," +
                "titulo TEXT," +
                "autor TEXT" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_VIDEOS);
        onCreate(db);
    }
}

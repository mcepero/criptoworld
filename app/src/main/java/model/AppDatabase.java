package model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Criptomoneda.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String bbdd = "criptomonedas.db";

    public abstract CriptomonedaDao monedaDAO();

    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,
                    AppDatabase.class,
                    AppDatabase.bbdd).build();
        }
        return instance;
    }
}

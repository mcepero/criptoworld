package model;

import android.service.autofill.FieldClassification;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import model.Criptomoneda;

@Dao
public interface CriptomonedaDao {

    @Query("SELECT * FROM criptomonedas")
    public LiveData<List<Criptomoneda>> getCriptomonedas();

    @Insert
    public long addCripto(Criptomoneda criptomoneda);
}

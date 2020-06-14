package viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import model.AppDatabase;
import model.Criptomoneda;

public class CriptomonedaViewModel extends AndroidViewModel {

    private LiveData<List<Criptomoneda>> listaMonedas;
    private static AppDatabase db;

    public CriptomonedaViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        listaMonedas = db.monedaDAO().getCriptomonedas();
    }

    public LiveData<List<Criptomoneda>> getMonedas(){
        return listaMonedas;
    }

    public void addMoneda(Criptomoneda criptomoneda){
        //new AsyncAddMonedaDB().execute(criptomoneda);
    }

}

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

    /*private class AsyncAddMonedaDB extends AsyncTask<Criptomoneda, Void, Long>{
        Criptomoneda criptomoneda;

        @Override
        protected Long doInBackground(Criptomoneda... criptomonedas) {
            long id=1;

            if (criptomonedas.length != 0){
                String name = criptomonedas[0].getNombre();
                Log.d("Criptomoneda", name);
                criptomoneda = criptomonedas[0];
                id = db.monedaDAO().addCripto(criptomonedas[0]);
                criptomoneda.setNumeroMoneda(id);
            }
            return id;
        }

        @Override
        protected void onPostExecute(Long id){
            if (id == -1) {
                Toast.makeText(getApplication(), "Error añadiendo criptomoneda", Toast.LENGTH_SHORT)
                        .show();
            } else {

                Toast.makeText(getApplication(), "Criptomoneda añadida", Toast.LENGTH_SHORT)
                        .show();
                //adapter.notifyItemInserted(0);
            }
        }
    }*/

}

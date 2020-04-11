package activities.criptomonedas;

import adapters.CriptomonedaAdapter;
import androidx.lifecycle.LiveData;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import model.AppDatabase;
import model.Criptomoneda;
import model.CriptomonedaDao;
import viewmodel.CriptomonedaViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manuelcepero.cripto.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class CriptomonedasDetallesFragment extends Fragment implements Serializable, CriptomonedaDao {
    ArrayList<Criptomoneda> listaMonedas;
    Criptomoneda c;
    AppDatabase appDatabase;
    private View view;
    CriptomonedaViewModel model;
    CriptomonedaAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_detalles_moneda, container, false);
        super.onCreate(savedInstanceState);

        Button anadir = (Button) view.findViewById(R.id.agregarFavoritos);
        listaMonedas = new ArrayList<>();
        appDatabase = AppDatabase.getInstance(getContext());
        Bundle args = this.getArguments();
        if (args != null) {
            c= args.getParcelable("moneda");
        }

        model = ViewModelProviders.of(this).get(CriptomonedaViewModel.class);
        model.getMonedas().observe(getViewLifecycleOwner(), new Observer<List<Criptomoneda>>() {
            @Override
            public void onChanged(List<Criptomoneda> criptomonedas) {
                adapter.addMonedas((ArrayList<Criptomoneda>) criptomonedas);
            }
        });

        anadirDatos();

        adapter = new CriptomonedaAdapter(getContext());

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText cantidad = view.findViewById(R.id.introducir_cantidad);
                c.setPrecio_introducido(Double.parseDouble(cantidad.getText().toString()));
                new CriptomonedasDetallesFragment.InsertTask(CriptomonedasDetallesFragment.this, c).execute();
            }
        });
       return view;
    }

    public void anadirDatos() {

        final ImageView imagen = view.findViewById(R.id.imagenDetallesMoneda);
        final TextView nombre = view.findViewById(R.id.nombreDetallesMoneda);
        final TextView precio = view.findViewById(R.id.precioDetallesMoneda);
        final TextView volumen = view.findViewById(R.id.volumen);
        final TextView capital = view.findViewById(R.id.capital);
        final TextView cambio1d = view.findViewById(R.id.cambio24h);
        final TextView cambio7d = view.findViewById(R.id.cambio7d);
        final TextView cambio1y = view.findViewById(R.id.cambio1y);

        if (!c.getImagen_large().isEmpty())
            Picasso.get().load(c.getImagen_large()).into(imagen);
        nombre.setText(c.getNombre());
        precio.setText("Precio: " + c.getPrecioActual() + "€");
        volumen.setText("Volumen en 24h: " + c.getVolumen() + "€");
        capital.setText("Capital de mercado: " + c.getCapital() + "€");
        cambio1d.setText("Cambio 24h: " + c.getCambio24h() + "%");
        cambio7d.setText("Cambio 7 días: " + c.getCambio7d() + "%");
        cambio1y.setText("Cambio 1 año: " + c.getCambio1ano() + "%");

    }


    @Override
    public LiveData<List<Criptomoneda>> getCriptomonedas() {
        return null;
    }

    @Override
    public long addCripto(Criptomoneda criptomoneda) {

        return 0;
    }

    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {

        private WeakReference<CriptomonedasDetallesFragment> activityReference;
        private Criptomoneda criptomoneda;

        InsertTask(CriptomonedasDetallesFragment context, Criptomoneda criptomoneda) {
            activityReference = new WeakReference<CriptomonedasDetallesFragment>(context);
            this.criptomoneda = criptomoneda;
        }

        @Override
        protected Boolean doInBackground(Void... objs) {
            try {
                activityReference.get().appDatabase.monedaDAO().addCripto(criptomoneda);
            }catch (Exception e){
                e.getMessage();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool) {

            }
        }
    }
}

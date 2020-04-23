package activities.favoritas;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manuelcepero.cripto.R;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import model.AppDatabase;
import model.Criptomoneda;
import model.CriptomonedaDao;

public class FavoritasDetallesFragment extends Fragment implements CriptomonedaDao {
    private ArrayList<Criptomoneda> listaMonedas;
    private Criptomoneda c;
    private AppDatabase appDatabase;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_detalles_moneda_favorita, container, false);
        super.onCreate(savedInstanceState);

        listaMonedas = new ArrayList<>();

        Bundle args = this.getArguments();
        if (args != null) {
            c= args.getParcelable("moneda");
        }


        appDatabase = AppDatabase.getInstance(getContext());

        anadirDatos();

        final Button eliminar = view.findViewById(R.id.eliminar);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FavoritasDetallesFragment.DeleteTask(FavoritasDetallesFragment.this, c).execute();
                FavoritasFragment favoritasFragment = new FavoritasFragment();

                Toast toast = Toast.makeText(getActivity(), "Criptomoneda eliminada", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }

    public void anadirDatos() {

        final ImageView imagen = view.findViewById(R.id.imagenDetallesMoneda_favorita);
        final TextView nombre = view.findViewById(R.id.nombreDetallesMoneda_favorita);
        final TextView precio = view.findViewById(R.id.precioDetallesMoneda_favorita);
        final TextView volumen = view.findViewById(R.id.volumen_favorita);
        final TextView capital = view.findViewById(R.id.capital_favorita);
        final TextView cambio1d = view.findViewById(R.id.cambio24h_favorita);
        final TextView cambio7d = view.findViewById(R.id.cambio7d_favorita);
        final TextView cambio1y = view.findViewById(R.id.cambio1y_favorita);
        final TextView cantidad = view.findViewById(R.id.cantidad_favorita);
        final TextView valor_cantidad = view.findViewById(R.id.valor_cantidad_favorita);

        if (!c.getImagen_large().isEmpty())
            Picasso.get().load(c.getImagen_large()).into(imagen);
        nombre.setText(c.getNombre());
        precio.setText("Precio: " + c.getPrecioActual() + "€");
        volumen.setText("Volumen en 24h: " + c.getVolumen() + "€");
        capital.setText("Capital de mercado: " + c.getCapital() + "€");
        cambio1d.setText("Cambio 24h: " + c.getCambio24h() + "%");
        cambio7d.setText("Cambio 7 días: " + c.getCambio7d() + "%");
        cambio1y.setText("Cambio 1 año: " + c.getCambio1ano() + "%");
        cantidad.setText("Cantidad introducida: " + c.getPrecio_introducido());
        valor_cantidad.setText("Valor de la cantidad introducida: " + c.getPrecio_introducido()*c.getPrecioActual() + "€");

    }


    @Override
    public LiveData<List<Criptomoneda>> getCriptomonedas() {
        return null;
    }

    @Override
    public long addCripto(Criptomoneda criptomoneda) {
        final EditText cantidad = view.findViewById(R.id.introducir_cantidad);
        criptomoneda.setPrecio_introducido(Double.parseDouble(cantidad.getText().toString()));
        return 0;
    }

    @Override
    public int deleteCripto(Criptomoneda criptomoneda) {
        return 0;
    }


    private static class DeleteTask extends AsyncTask<Void, Void, Boolean> {

        private WeakReference<FavoritasDetallesFragment> activityReference;
        private Criptomoneda criptomoneda;

        DeleteTask(FavoritasDetallesFragment context, Criptomoneda criptomoneda) {
            activityReference = new WeakReference<>(context);
            this.criptomoneda = criptomoneda;
        }

        @Override
        protected Boolean doInBackground(Void... objs) {
            try {
                activityReference.get().appDatabase.monedaDAO().deleteCripto(criptomoneda);
            }catch (Exception e){
                e.getMessage();
            }
            return true;
        }
    }
}

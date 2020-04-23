package activities.exchanges;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manuelcepero.cripto.R;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import model.Exchange;

public class ExchangeDetallesFragment extends Fragment {
    private ArrayList<Exchange> listaExchanges;
    private Exchange e;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_detalles_exchange, container, false);
        super.onCreate(savedInstanceState);

        listaExchanges = new ArrayList<>();

        Bundle args = this.getArguments();
        if (args != null) {
            e= args.getParcelable("exchange");
        }

        anadirDatos();

        return view;
    }

    public void anadirDatos() {

        final ImageView imagen = view.findViewById(R.id.imagenDetallesExchange);
        final TextView nombre = view.findViewById(R.id.nombreDetallesExchange);
        final TextView pais = view.findViewById(R.id.paisDetallesExchange);
        final TextView volumen = view.findViewById(R.id.volumenDetallesExchange);
        final TextView url = view.findViewById(R.id.urlDetallesExchange);
        if (!url.getText().toString().equals("null"))
            url.setMovementMethod(LinkMovementMethod.getInstance());

        //parseJSON();
        if (!e.getImagen().isEmpty())
            Picasso.get().load(e.getImagen()).into(imagen);
        nombre.setText(e.getNombre());
        volumen.setText(volumen.getText() + " " + e.getVolumen() + "€");
        pais.setText(pais.getText() + " " + e.getPais());
        url.setText("URL: " + e.getUrl());

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlWeb=e.getUrl();

                if (!urlWeb.startsWith("https://") && !urlWeb.startsWith("http://")){
                    urlWeb = "http://" + urlWeb;
                }

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(urlWeb));
                startActivity(i);
            }
        });

    }


    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {

        private WeakReference<ExchangeDetallesFragment> activityReference;
        private Exchange exchange;

        InsertTask(ExchangeDetallesFragment context, Exchange exchange) {
            activityReference = new WeakReference<>(context);
            this.exchange = exchange;
        }

        @Override
        protected Boolean doInBackground(Void... objs) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool) {

            }
        }
    }
}

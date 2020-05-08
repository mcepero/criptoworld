package activities.criptomonedas;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.manuelcepero.cripto.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import activities.SplashActivity;
import adapters.CriptomonedaAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.Criptomoneda;
import viewmodel.CriptomonedaViewModel;

public class CriptomonedasFragment extends Fragment {

    private RecyclerView recyclerView;
    private CriptomonedaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Criptomoneda> listaMonedas;
    private RequestQueue mRequestQueue;
    private View view;
    SplashActivity splashActivity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_monedas, container, false);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_monedas);
        listaMonedas = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        parseJSONn();

        adapter = new CriptomonedaAdapter(getContext(), listaMonedas);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void parseJSONn() {
        //String url = "https://api.coingecko.com/api/v3/coins";
        String url = "https://api.coingecko.com/api/v3/coins";//?vs_currency=usd&order=market_cap_desc&per_page=200&page=1";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = (JSONObject) response.get(i);
                        String id = object.getString("id");
                        String nombreCompleto = object.getString("name");
                        String nombreAbreviado = object.getString("symbol");

                        JSONObject marketData = object.getJSONObject("market_data");
                        JSONObject currentPrice = marketData.getJSONObject("current_price");
                        double euro = currentPrice.getDouble("eur");


                        double cambio1d = marketData.getDouble("price_change_percentage_24h");

                        double cambio7d = marketData.getDouble("price_change_percentage_7d");

                        double cambio1y = marketData.getDouble("price_change_percentage_1y");

                        JSONObject volume = marketData.getJSONObject("total_volume");
                        long volumeEuro = volume.getLong("eur");

                        JSONObject capital = marketData.getJSONObject("market_cap");
                        long capitalEuro = capital.getLong("eur");

                        JSONObject image = object.getJSONObject("image");
                        String imagen_small = image.getString("small");
                        String imagen_large = image.getString("large");

                        Criptomoneda c = new Criptomoneda(id, i, nombreCompleto, nombreAbreviado, euro, cambio1d, cambio7d, cambio1y, volumeEuro, capitalEuro, imagen_small, imagen_large,0);
                        listaMonedas.add(c);
                    }

                    adapter = new CriptomonedaAdapter(getContext(), listaMonedas);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

}

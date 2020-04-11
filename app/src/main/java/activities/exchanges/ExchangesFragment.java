package activities.exchanges;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import adapters.ExchangeAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.Exchange;

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

public class ExchangesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ExchangeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Exchange> listaExchanges;
    private RequestQueue mRequestQueue;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_exchanges, container, false);
        super.onCreate(savedInstanceState);

        listaExchanges = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewExchanges);
        layoutManager =  new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        parseJSONn();

        return view;
    }

    private void parseJSONn() {
        String url = "https://api.coingecko.com/api/v3/exchanges";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = (JSONObject) response.get(i);
                        String id = object.getString("id");
                        String nombreCompleto = object.getString("name");
                        String pais = object.getString("country");
                        long volumen = object.getLong("trade_volume_24h_btc");
                        String imagen = object.getString("image");
                        String url = object.getString("url");
                        //int anoFundacion = object.getInt("year_established");

                        if (pais.equals("null")){
                            pais="Desconocido";
                        }
                        Exchange e = new Exchange(id, nombreCompleto, pais, 0, volumen, url, imagen);
                        listaExchanges.add(e);

                    }

                    adapter = new ExchangeAdapter(getContext(), listaExchanges);
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
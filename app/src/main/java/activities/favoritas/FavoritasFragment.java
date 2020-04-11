package activities.favoritas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.example.manuelcepero.cripto.R;

import java.util.ArrayList;
import java.util.List;

import adapters.CriptomonedaFavoritaAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.AppDatabase;
import model.Criptomoneda;
import viewmodel.CriptomonedaViewModel;

public class FavoritasFragment extends Fragment implements CriptomonedaFavoritaAdapter.OnButtonClickedListener {
    private CriptomonedaFavoritaAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase appDatabase;
    private LiveData<List<Criptomoneda>> listaMonedas;
    private CriptomonedaViewModel criptomonedaViewModel;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_monedas_guardadas, container, false);
        super.onCreate(savedInstanceState);

        appDatabase = AppDatabase.getInstance(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        listaMonedas = appDatabase.monedaDAO().getCriptomonedas();

        adapter = new CriptomonedaFavoritaAdapter(getContext());
        criptomonedaViewModel = ViewModelProviders.of(this).get(CriptomonedaViewModel.class);
        criptomonedaViewModel.getMonedas().observe(getViewLifecycleOwner(), new Observer<List<Criptomoneda>>() {
            @Override
            public void onChanged(List<Criptomoneda> criptomonedas) {
                adapter.addMonedas((ArrayList<Criptomoneda>) criptomonedas);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onButtonClicked(View v, Criptomoneda criptomoneda) {

    }
}


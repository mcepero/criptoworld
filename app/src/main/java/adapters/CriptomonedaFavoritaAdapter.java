package adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manuelcepero.cripto.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import activities.favoritas.FavoritasDetallesFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import model.Criptomoneda;

public class CriptomonedaFavoritaAdapter extends RecyclerView.Adapter<CriptomonedaFavoritaAdapter.ViewHolder>{

    private List<Criptomoneda> listaMonedas;
    private int layout;
    private Activity activity;
    private CriptomonedaFavoritaAdapter.OnButtonClickedListener listener;
    private Context context;

    public CriptomonedaFavoritaAdapter(Context context, List<Criptomoneda> listaMonedas, CriptomonedaFavoritaAdapter.OnButtonClickedListener listener) {
        this.context = context;
        this.listaMonedas =listaMonedas;
        this.listener=listener;
    }

    public CriptomonedaFavoritaAdapter(Context context){
        this.context=context;
        this.listaMonedas = new ArrayList<>();

    }

    @NonNull
    @Override
    public CriptomonedaFavoritaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_monedas, parent, false);
        return new CriptomonedaFavoritaAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull CriptomonedaFavoritaAdapter.ViewHolder holder, int position) {
        Criptomoneda moneda = listaMonedas.get(position);
        holder.nombre.setText(moneda.getNombre() + " (" + moneda.getAbreviatura()+ ")");
        holder.precio.setText(holder.precio.getText() + " " + moneda.getPrecioActual() + "€");
        if(!moneda.getImagenSmall().isEmpty())
            Picasso.get().load(moneda.getImagen_large()).into(holder.imagen);
    }

    public void addMonedas(List<Criptomoneda> monedas){
        this.listaMonedas=monedas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaMonedas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Serializable {

        public TextView nombre;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View itemView){
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombreMoneda);
            precio = (TextView) itemView.findViewById(R.id.precioMoneda);
            imagen = (ImageView) itemView.findViewById(R.id.imagenMoneda);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    //listener.onButtonClicked(v, listaMonedas.get(position));
                    Criptomoneda c = listaMonedas.get(position);

                    FavoritasDetallesFragment favoritasDetallesFragment = new FavoritasDetallesFragment();

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("moneda", c);
                    favoritasDetallesFragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentos, favoritasDetallesFragment).addToBackStack(null).commit();
                }
            });
        }

        @Override
        public void onClick(View v) {
            listener.onButtonClicked(v, listaMonedas.get(getAdapterPosition()));
        }
    }
    public interface OnButtonClickedListener{
        void onButtonClicked(View v, Criptomoneda criptomoneda);
    }
}
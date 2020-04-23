package adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.manuelcepero.cripto.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import activities.criptomonedas.CriptomonedasDetallesFragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import model.Criptomoneda;

public class CriptomonedaAdapter extends RecyclerView.Adapter<CriptomonedaAdapter.ViewHolder>{

    private List<Criptomoneda> listaMonedas;
    private int layout;
    private Activity activity;
    private View.OnClickListener listener;
    private Context context;


    public CriptomonedaAdapter(Context context, ArrayList<Criptomoneda> listaMonedas, View.OnClickListener listener) {
        this.context = context;
        this.listaMonedas =listaMonedas;
        this.listener=listener;
    }

    public CriptomonedaAdapter(Context context, View.OnClickListener listener){
        this.context=context;
        this.listener=listener;
        this.listaMonedas = new ArrayList<>();
    }

    public CriptomonedaAdapter(Context context,List<Criptomoneda> listaMonedas) {
        this.listaMonedas = listaMonedas;
        this.context = context;
    }

    public CriptomonedaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CriptomonedaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_monedas, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull CriptomonedaAdapter.ViewHolder holder, int position) {
        Criptomoneda moneda = listaMonedas.get(position);
        holder.nombre.setText(moneda.getNombre() + " (" + moneda.getAbreviatura()+ ")");
        holder.precio.setText(moneda.getPrecioActual() + "€");
        if(!moneda.getImagenSmall().isEmpty())
            Picasso.get().load(moneda.getImagen_large()).into(holder.imagen);
    }

    public void addMonedas(ArrayList<Criptomoneda> monedas){
        this.listaMonedas=monedas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaMonedas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Serializable{
        public LinearLayout linearLayout;
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

                    Criptomoneda c = listaMonedas.get(position);


                    CriptomonedasDetallesFragment criptomonedasDetallesFragment = new CriptomonedasDetallesFragment();

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("moneda", c);
                    criptomonedasDetallesFragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, criptomonedasDetallesFragment).addToBackStack(null).commit();
                }
            });
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v);
        }
    }
    public interface OnButtonClickedListener{
        void onButtonClicked(View v, Criptomoneda criptomoneda);
    }
}

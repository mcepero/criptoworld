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

import activities.exchanges.ExchangeDetallesFragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import model.Exchange;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHolder> {

    private List<Exchange> listaExchanges;
    private int layout;
    private Activity activity;
    private ExchangeAdapter.OnButtonClickedListener listener;
    private Context context;

    public ExchangeAdapter(Context context, ArrayList<Exchange> listaExchanges) {
        this.context = context;
        this.listaExchanges =listaExchanges;
    }

    @NonNull
    @Override
    public ExchangeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_exchanges, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ExchangeAdapter.ViewHolder holder, int position) {
        Exchange exchange = listaExchanges.get(position);
        holder.nombre.setText(exchange.getNombre());
        holder.pais.setText("País: " + exchange.getPais());
        if(!exchange.getImagen().isEmpty())
            Picasso.get().load(exchange.getImagen()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return listaExchanges.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Serializable{

        public TextView nombre;
        public TextView pais;
        public ImageView imagen;

        public ViewHolder(View itemView){
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombreExchange);
            pais = (TextView) itemView.findViewById(R.id.paisExchange);
            imagen = (ImageView) itemView.findViewById(R.id.imagenExchange);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    Exchange e = listaExchanges.get(position);

                    ExchangeDetallesFragment exchangeDetallesFragment = new ExchangeDetallesFragment();

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("exchange", e);
                    exchangeDetallesFragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, exchangeDetallesFragment).addToBackStack(null).commit();
                }
            });
        }

        @Override
        public void onClick(View v) {
            listener.onButtonClicked(v, listaExchanges.get(getAdapterPosition()));
        }
    }
    public interface OnButtonClickedListener{
        void onButtonClicked(View v, Exchange exchange);
    }
}

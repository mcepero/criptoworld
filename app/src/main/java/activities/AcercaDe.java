package activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.manuelcepero.cripto.R;
import com.squareup.picasso.Picasso;

import activities.favoritas.FavoritasFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class AcercaDe extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.acerca_de);
        final ImageView imagen = findViewById(R.id.imagenAcercaDe);
        Picasso.get().load(R.drawable.logo).into(imagen);

    }
}

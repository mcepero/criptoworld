package activities.favoritas;

import android.os.Bundle;
import android.view.View;

import com.example.manuelcepero.cripto.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class FavoritasActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.criptmomonedas_fragment);

        FavoritasFragment favoritasFragment = new FavoritasFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, favoritasFragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, favoritasFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {

    }
}
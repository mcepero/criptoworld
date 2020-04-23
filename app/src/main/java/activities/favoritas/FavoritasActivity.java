package activities.favoritas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.manuelcepero.cripto.R;

import activities.SplashActivity;
import activities.criptomonedas.CriptomonedasActivity;
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

        Intent intent = new Intent(FavoritasActivity.this, SplashActivity.class);
        intent.putExtra("milisegundos", 2500);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}
package activities.exchanges;

import android.content.Intent;
import android.os.Bundle;

import com.example.manuelcepero.cripto.R;

import activities.SplashActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ExchangesActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.criptmomonedas_fragment);

        ExchangesFragment exchangesFragment = new ExchangesFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentos, exchangesFragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragmentos, exchangesFragment);
        transaction.commit();

        Intent intent = new Intent(ExchangesActivity.this, SplashActivity.class);
        intent.putExtra("milisegundos", 3000);
        startActivity(intent);
    }
}

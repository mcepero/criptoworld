package activities;

import activities.criptomonedas.CriptomonedasFragment;
import activities.exchanges.ExchangesFragment;
import activities.favoritas.FavoritasFragment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manuelcepero.cripto.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonCriptomonedas = (Button)findViewById(R.id.criptomonedas);
        Button botonGuardadas = (Button)findViewById(R.id.guardadas);
        Button botonExchanges = findViewById(R.id.exchanges);
        Button botonAcerca = findViewById(R.id.acercade);

        botonCriptomonedas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CriptomonedasFragment criptomonedasFragment = new CriptomonedasFragment();
                SplashFragment splashFragment = new SplashFragment();

                /*Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                intent.putExtra("milisegundos", 5500);
                startActivity(intent);*/

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.contenedorFragmentos, criptomonedasFragment).addToBackStack(MainActivity.class.getName())
                        .commit();


                /*getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragmentos, criptomonedasFragment);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorFragmentos, criptomonedasFragment);
                transaction.commit();*/

            }
        });

        botonGuardadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoritasFragment favoritasFragment = new FavoritasFragment();

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.contenedorFragmentos, favoritasFragment).addToBackStack(MainActivity.class.getName())
                        .commit();
            }
        });

        botonExchanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExchangesFragment exchangesFragment = new ExchangesFragment();

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.contenedorFragmentos, exchangesFragment).addToBackStack(MainActivity.class.getName())
                        .commit();
            }
        });

        botonAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AcercaDe.class));
            }
        });
    }
}

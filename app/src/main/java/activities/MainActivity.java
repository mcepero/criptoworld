package activities;

import activities.criptomonedas.CriptomonedasActivity;
import activities.criptomonedas.CriptomonedasFragment;
import activities.exchanges.ExchangesActivity;
import activities.favoritas.FavoritasActivity;
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
                startActivity(new Intent(MainActivity.this, CriptomonedasActivity.class));
            }
        });

        botonGuardadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavoritasActivity.class));
            }
        });

        botonExchanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExchangesActivity.class));
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

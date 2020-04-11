package activities.exchanges;

import android.os.Bundle;
import android.view.View;

import com.example.manuelcepero.cripto.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ExchangesActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.criptmomonedas_fragment);

        ExchangesFragment exchangesFragment = new ExchangesFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, exchangesFragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, exchangesFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {

    }
}

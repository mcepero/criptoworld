package activities.criptomonedas;

import android.os.Bundle;
import android.view.View;

import com.example.manuelcepero.cripto.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class CriptomonedasActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.criptmomonedas_fragment);

        CriptomonedasFragment criptomonedasFragment = new CriptomonedasFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, criptomonedasFragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, criptomonedasFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {

    }
}

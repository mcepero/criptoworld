package activities.criptomonedas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.manuelcepero.cripto.R;

import java.lang.ref.WeakReference;

import activities.MainActivity;
import activities.SplashActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import model.Criptomoneda;

public class CriptomonedasActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.criptmomonedas_fragment);

        CriptomonedasFragment criptomonedasFragment = new CriptomonedasFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, criptomonedasFragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, criptomonedasFragment);
        transaction.commit();

        Intent intent = new Intent(CriptomonedasActivity.this, SplashActivity.class);
        intent.putExtra("milisegundos", 5500);
        startActivity(intent);
    }
}

package activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.manuelcepero.cripto.R;

import activities.criptomonedas.CriptomonedasActivity;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splash);

        int milisegundos = getIntent().getExtras().getInt("milisegundos");

        esperarYCerrar(milisegundos);
    }

    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finalizarApp();
            }
        }, milisegundos);
    }

    public void finalizarApp() {
        finish();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}

package activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manuelcepero.cripto.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SplashFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.splash, container, false);
        super.onCreate(savedInstanceState);
        int milisegundos=0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            milisegundos = bundle.getInt("milisegundos", 0);
        }

        esperarYCerrar(milisegundos);
        return view;
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
        getActivity().onBackPressed();
    }
}

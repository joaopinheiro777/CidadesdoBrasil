package br.com.unipar.cidades_brasil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.unipar.cidades_brasil.adapter.CidadesAdapter;
import br.com.unipar.cidades_brasil.entities.Cidades;
import br.com.unipar.cidades_brasil.rest.CidadeService;
import br.com.unipar.cidades_brasil.service.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCidade;
    private ListView listViewCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cidades = findViewById(R.id.lstItens);

        CidadeService photoService = RetrofitService.getInstance().create(CidadeService.class);

        final WeakReference<Context> weakReference = new WeakReference(this);

        photoService.findAll().enqueue(new Callback<List<Cidades>>() {
            @Override
            public void onResponse(Call<List<Cidades>> call, Response<List<Cidades>> response) {
                CidadesAdapter photoAdapter = new CidadesAdapter(response.body(), weakReference);
                cidades.setAdapter(CidadesAdapter);
            }

            @Override
            public void onFailure(Call<List<Cidades>> call, Throwable t) {
                Toast.makeText(weakReference.get(), "Não foi possível carregar as Cidades.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
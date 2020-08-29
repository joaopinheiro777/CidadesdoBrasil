package br.com.unipar.cidades_brasil.rest;

import java.util.List;

import br.com.unipar.cidades_brasil.entities.Cidades;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CidadeService {

    @GET("/cidade")
    Call<List<Cidades>> findAll();
}

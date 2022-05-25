package br.senac.lojaretrofit.views

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.senac.lojaretrofit.databinding.ActivityListaProdutosBinding
import br.senac.lojaretrofit.databinding.CardItemBinding
import br.senac.lojaretrofit.model.Produto
import br.senac.lojaretrofit.services.API
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaProdutosActivity : AppCompatActivity() {
    lateinit var binding: ActivityListaProdutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        listarProdutos()
    }

    fun listarProdutos() {

        val callback = object : Callback<List<Produto>> {
            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                if (response.isSuccessful) {
                    val listaProdutos = response.body()
                    atualizarTela(listaProdutos)
                }
                else {
                    Snackbar.make(binding.container,
                        "Servidor indisponível",
                        Snackbar.LENGTH_LONG)
                        .show()
                }
                binding.progressBar.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                Snackbar.make(binding.container,
                    "Não foi possível se conectar ao servidor",
                    Snackbar.LENGTH_LONG)
                    .show()
                binding.progressBar.visibility = View.INVISIBLE
            }

        }

        API.produto.listar().enqueue(callback)
        binding.progressBar.visibility = View.VISIBLE

    }

    fun atualizarTela(listaProdutos: List<Produto>?) {
        binding.container.removeAllViews()

        listaProdutos?.forEach {

            val cardBinding = CardItemBinding.inflate(layoutInflater)

            cardBinding.textNome.text = it.nomeProduto
            cardBinding.textPreco.text = it.precProduto.toString()


            Picasso
                .get()
                .load("https://oficinacordova.azurewebsites.net/android/rest/produto/image/${it.idProduto}")
                .into(cardBinding.imagem)

            binding.container.addView(cardBinding.root)

        }
    }
}
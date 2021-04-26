package br.senac.lojaretrofit.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.senac.lojaretrofit.databinding.ActivityListaProdutosBinding

class ListaProdutosActivity : AppCompatActivity() {
    lateinit var binding: ActivityListaProdutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
package com.example.aula2604.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.aula2604.roomDB.Pessoa
import kotlinx.coroutines.launch

class PessoaViewModel(private val repository: Repository): ViewModel() {
    fun getPessoa() = repository.getAllPessoa().asLiveData(viewModelScope.coroutineContext) /* pega o arquivo 'Pessoa' em outra pasta, e identificar os dados e suas váriaveis para que nesse arquivo, seja executado com sucesso o inserir, atualizar e deletar */

    fun upsertPessoa(pessoa: Pessoa){
        viewModelScope.launch {
            repository.upsertPessoa(pessoa)
        }
    }

    fun deletePessoa(pessoa: Pessoa) {
        viewModelScope.launch {
            repository.deletePessoa(pessoa)
        }
    }
}

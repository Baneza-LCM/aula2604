package com.example.aula2604.viewModel

import com.example.aula2604.roomDB.Pessoa
import com.example.aula2604.roomDB.PessoaDataBase

class Repository(private val db: PessoaDataBase) {
    suspend fun upsertPessoa(pessoa: Pessoa){
        db.pessoaDao().upsertPessoa(pessoa)
    }

    suspend fun deletePessoa(pessoa: Pessoa){
        db.pessoaDao().deletePessoa(pessoa)
    }

    fun getAllPessoa() = db.pessoaDao().getAllPessoa() /* pega todos os registro dentro da tabela 'Pessoa', com o arquivo 'PessoaDao' */
}

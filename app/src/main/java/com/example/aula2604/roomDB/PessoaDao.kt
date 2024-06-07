package com.example.aula2604.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PessoaDao {

    @Upsert
    suspend fun upsertPessoa(pessoa: Pessoa) 
/* duas funções em um único código, representando o inserir e atulizar */

    @Delete
    suspend fun deletePessoa(pessoa: Pessoa)

    @Query("SELECT * FROM pessoa") 
    /* por não ter um código próprio em kotlin, é preciso digitar os códigos manualmente em sql */
    fun getAllPessoa(): Flow<List<Pessoa>> 
    /* pega todos os registros inseridos e lista eles em uma tabela */
}

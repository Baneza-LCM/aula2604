package com.example.aula2604.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pessoa(
    val nome: String,
    val telefone: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

/* Nesse arquivo, é implementado o código para uma breve construção de uma tabela no banco de dados, sendo a tabela 'Pessoa', além de definir os dados com variáveis diferente, sendo carácter ou númerico. */

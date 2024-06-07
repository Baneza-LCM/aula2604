package com.example.aula2604.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities =[Pessoa::class],
    version = 1 /* versão do banco de dados */
)

abstract class PessoaDataBase: RoomDatabase() {
    abstract fun pessoaDao(): PessoaDao 
    /* fazendo a conexão com um arquivo na mesma pasta, o 'PessoaDao' */
}

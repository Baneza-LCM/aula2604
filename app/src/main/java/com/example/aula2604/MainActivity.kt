package com.example.aula2604

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.aula2604.roomDB.Pessoa
import com.example.aula2604.roomDB.PessoaDataBase
import com.example.aula2604.ui.theme.Aula2604Theme
import com.example.aula2604.viewModel.PessoaViewModel
import com.example.aula2604.viewModel.Repository

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PessoaDataBase::class.java,
            "pessoa.db"
        ).build()
    }
    /* o código acima, representa a váriavel privada do banco de dados, da tabela pessoa, fazendo a conexão com esse banco para esse arquivo em questão, possibilitando o uso para fornecer dados por meio do formulário, codificado nesse arquivo*/

    private val viewModel by viewModels<PessoaViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T: ViewModel> create(modelClass: Class<T>): T{
                    return PessoaViewModel(Repository(db)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aula2604Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(viewModel, this)
                }
            }
        }
    }
}

@Composable
fun App(viewModel: PessoaViewModel, mainActivity: MainActivity){
    var nome by remember {
        mutableStateOf("")
    }

    var telefone by remember {
        mutableStateOf("")
    }

    val pessoa = Pessoa(
        nome,
        telefone
    )
    /* as váriaves acima, são os dados da tabela Pessoa, para puxar os dados colocados no formulário feito e enviar para o banco, sem ter o erro dos dados dos registros estarem com outro nome e o banco não reconhecer. */

    var pessoaList by remember {
        mutableStateOf(listOf<Pessoa>())
    }

    viewModel.getPessoa().observe(mainActivity){
        pessoaList = it
    }

    Column(
        Modifier
            .background(Color.Black)
    ) {
        Row(
            Modifier
                .padding(20.dp)
        ){

        }
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            Text(
                text = "App DataBase",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        Row(
            Modifier
                .padding(20.dp)
        ){

        }
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text(text = "Nome:") }
            )
        }
        Row(
            Modifier
                .padding(20.dp)
        ){

        }
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            TextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = { Text(text = "Telefone:") }
            )
        }
        Row(
            Modifier
                .padding(20.dp)
        ){

        }
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            Button(
                onClick = {
                    viewModel.upsertPessoa(pessoa)
                    nome = ""
                    telefone = ""
                    /* envia o novo registro e atualiza ao mesmo tempo, tendo duas funções em uma palavra de código */
                }
            ) {
                Text(text = "Cadastrar")
            }
        }
        Row(
            Modifier
                .padding(20.dp)
        ){

        }
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
            ) {
                Text(text = "Nome")
            }
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
            ) {
                Text(text = "Telefone")
            }
        }
        Divider()
        LazyColumn {
            items(pessoaList){ pessoa ->
                Row(
                    Modifier
                        .clickable {
                            viewModel.deletePessoa(pessoa)
                        } /* o deletePessoa exclui o registro na qual o usuário clicar na opção de deletar tal registro, fazendo a ligação com o banco para que exclua tal registro de tal coluna. */
                        .fillMaxWidth(),
                    Arrangement.Center
                ){
                    Column(
                        Modifier
                            .fillMaxWidth(0.5f),
                        Arrangement.Center
                    ) {
                        Text(text = "${pessoa.nome}")
                    }
                    Column(
                        Modifier
                            .fillMaxWidth(0.5f),
                        Arrangement.Center
                    ) {
                        Text(text = "${pessoa.telefone}")
                    }
                }
                Divider()
            }
        }
    }
}

/*
@Preview
@Composable
fun AppPreview(){
    Aula2604Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            App()
        }
    }
}*/

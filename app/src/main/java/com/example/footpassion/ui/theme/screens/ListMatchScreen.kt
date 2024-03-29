package com.example.footpassion.ui.theme.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.footpassion.R
import com.example.footpassion.ui.theme.FootPassionTheme
import com.example.footpassion.ui.theme.Routes
import com.example.footpassion.viewmodel.MainViewModel
import com.example.footpassion.viewmodel.dateFormat

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ListMatchPreview() {
    FootPassionTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val mainViewModel: MainViewModel = viewModel()
            ListMatchScreen(mainViewModel = mainViewModel)
        }
    }
}

@Composable
fun ListMatchScreen(navHostController: NavHostController? = null, mainViewModel: MainViewModel) {

    LaunchedEffect(key1 = "") {
        mainViewModel.loadData()
    }


    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(id = R.drawable.pelouse),
            contentDescription = null, // Modifier en conséquence
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "FOOT PASSION",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null, // Modifier en conséquence
                    contentScale = ContentScale.Fit,
                    //   modifier = Modifier.size(50.dp),
                    modifier = Modifier
                        .weight(1f)
                        .size(40.dp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { navHostController?.navigate(Routes.AddGameScreen.route) },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Icon(
                        Icons.Filled.Send,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Ajouter un Match")
                }
            }
            Row {
                Text(
                    text = "LISTE DES MATCHS",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )


            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp)
            ) {
                items(mainViewModel.myList) { game ->
                    var scoreEquipe1 = remember { mutableIntStateOf(0) }
                    scoreEquipe1.intValue = game.scoreEquipe1
                    var scoreEquipe2 = remember { mutableIntStateOf(0) }
                    scoreEquipe2.intValue = game.scoreEquipe2
                    var fini = remember { mutableStateOf(false) }
                    fini.value = game.fini
                    Spacer(Modifier.height(50.dp))
                    Row(modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(14.dp))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if (game.date!= null) dateFormat.format(game.date) else "-",
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = game.equipe1,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    fontSize = 25.sp
                                )
                                Text(
                                    text = " ",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = game.equipe2,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    fontSize = 25.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if (fini.value) "Terminé" else "En Cours",
                                    fontSize = 15.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = scoreEquipe1.value.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    fontSize = 25.sp
                                )
                                Text(
                                    text = "-",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    fontSize = 25.sp
                                )
                                Text(
                                    text = scoreEquipe2.value.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    fontSize = 25.sp
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp, 0.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = {
                                        scoreEquipe1.value +=1
                                        mainViewModel.updateGame(game.id, "equipe1")},
                                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                                ) {
                                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                    Text("+", fontSize = 18.sp)
                                }
                                Text(
                                    text = " ",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center,
                                    fontSize = 25.sp
                                )
                                Button(
                                    onClick = {
                                        scoreEquipe2.value +=1
                                        mainViewModel.updateGame(game.id, "equipe2")},
                                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                                ) {
                                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                    Text("+", fontSize = 18.sp)
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = {
                                        fini.value = true
                                        mainViewModel.updateGame(game.id, "end")},
                                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                                ) {
                                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                    Text("Fin du Match")
                                }
                            }
                        }
                    }
                }
            }

        }

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row (){
            Button(
                onClick = { mainViewModel.loadData() },
                colors = ButtonDefaults.buttonColors(Color.Blue, Color.White),
                modifier = Modifier
                    .padding(16.dp) // Ajoute un espace autour du bouton
            ) {
                Text("Rafraîchir")
            }

            Button(
                onClick = { navHostController?.navigate(Routes.ResultListScreen.route) },
                colors = ButtonDefaults.buttonColors(Color.Blue, Color.White),
                modifier = Modifier
                    .padding(16.dp) // Ajoute un espace autour du bouton
            ) {
                Text("Historique")
            }
        }

    }

}

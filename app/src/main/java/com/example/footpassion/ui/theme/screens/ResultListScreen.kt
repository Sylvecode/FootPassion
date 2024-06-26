package com.example.footpassion.ui.theme.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.footpassion.R
import com.example.footpassion.ui.theme.FootPassionTheme
import com.example.footpassion.ui.theme.Routes
import com.example.footpassion.viewmodel.MainViewModel


@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ResultPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    FootPassionTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//            //Jeu de donnée pour la Preview
            val mainViewModel: MainViewModel = viewModel()
            ResultListScreen(mainViewModel = mainViewModel)
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ResultListScreen(navHostController: NavHostController? = null, mainViewModel: MainViewModel) {

    LaunchedEffect(key1 = "") {
        mainViewModel.loadRecentGames()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Ajouter l'image en arrière-plan
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
            )
            {
                Text(
                    text = "FOOT PASSION",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )


                Spacer(modifier = Modifier.size(10.dp))

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
            Spacer(modifier = Modifier.size(60.dp))

            Row {
                Text(
                    text = "HISTORIQUE DES RÉSULTATS",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color.White,
                modifier = Modifier.padding(15.dp)

            ) {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .height(390.dp)
                ) {

                    Row {
                        Spacer(
                            Modifier
                                .width(30.dp)
                                .height(40.dp)
                        )
                        Text(
                            text = "Date",
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(15.dp)
                        )
                        Spacer(Modifier.width(100.dp))
                        Text(
                            text = "Match",
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    )
                    {
                        items(mainViewModel.myList.size) { index ->
                            val game = mainViewModel.myList[index]
                            Row {
                                Spacer(Modifier.width(20.dp))
                                mainViewModel.convertDateToString(game.date)?.let {
                                    Text(
                                        text = it,
                                        textAlign = TextAlign.Start,
                                    )
                                }
                                Spacer(Modifier.width(60.dp))
                                Text(text = game.equipe1+" " + game.scoreEquipe1.toString() + " - "+ game.scoreEquipe2.toString()+ " " + game.equipe2)
                            }
                        }

                    }

                    Spacer(Modifier.size(50.dp))

                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center){
                        Button(
                            onClick = { mainViewModel.loadRecentGames() },
                            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                        ) {
                            Icon(
                                Icons.Filled.Refresh,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text("Actualiser la liste")
                        }

                        Spacer(Modifier.size(50.dp))


                        Button(
                            onClick = { navHostController?.navigate(Routes.ListMatchScreen.route)},
                            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text("RETOUR")
                        }
                    }

                }

            }
        }
    }
}






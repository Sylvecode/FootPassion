package com.example.footpassion.ui.theme.screens

import android.content.res.Configuration
import android.widget.DatePicker
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.footpassion.R
import com.example.footpassion.ui.theme.FootPassionTheme
import com.example.footpassion.viewmodel.MainViewModel

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddGamePreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    FootPassionTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val mainViewModel: MainViewModel = viewModel()
            mainViewModel.team1Text.value = ""
            mainViewModel.team2Text.value = ""
            mainViewModel.dateText.value = ""
            AddGameScreen(mainViewModel = mainViewModel)
        }
    }
}



@Composable
fun AddGameScreen(navHostController: NavHostController? = null, mainViewModel: MainViewModel) {
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


                // Ajouter un padding entre "FOOT PASSION" et le logo
                Spacer(modifier = Modifier.size(10.dp))

                // Ajouter du padding autour du logo
                /* Box(
                modifier = Modifier.size(48.dp).padding(8.dp),

            )  */

                // Logo
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

            Spacer(Modifier.size(40.dp))

            Text(
                text = "Rentrez 2 équipes et la date du match",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                //color = MaterialTheme.colorScheme.primary
                color = Color.White
            )

            Spacer(Modifier.height(16.dp))


            // Encart avec fond blanc et bord arrondi
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.padding(35.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Label "Equipe 1"
                    Text(
                        text = "Equipe 1",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(8.dp))

                    // Champ de texte pour l'équipe 1
                    TextField(
                        value = mainViewModel.team1Text.value,
                        onValueChange = { mainViewModel.team1Text.value = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))

// Label "Equipe 2"
                    Text(
                        text = "Equipe 2",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(8.dp))

// Champ de texte pour l'équipe 2
                    TextField(
                        value = mainViewModel.team2Text.value,
                        onValueChange = { mainViewModel.team2Text.value = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))

// Label "Date"
                    Text(
                        text = "Date",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(8.dp))


// Champ de texte pour la date
                    TextField(
                        value = mainViewModel.dateText.value,
                        onValueChange = { mainViewModel.dateText.value = it},
                        modifier = Modifier.fillMaxWidth()
                    )


                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = {
                            mainViewModel.createGame(
                                /*
                                equipe1 = mainViewModel.team1Text.value,
                                equipe2 = mainViewModel.team2Text.value,
                                date = mainViewModel.dateParsed

                                 */

                            )
                        },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                    ) {
                        Icon(
                            Icons.Filled.Send,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Valider le match")
                    }
                    Spacer(Modifier.height(1.dp))

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null, // Modifier en conséquence
                        contentScale = ContentScale.Fit,
                        //   modifier = Modifier.size(50.dp),
                        modifier = Modifier
                            .weight(1f)
                            .size(120.dp)
                    )

                }
            }
        }
    }
}

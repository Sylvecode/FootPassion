package com.example.footpassion.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footpassion.model.FootPassionAPI
import com.example.footpassion.model.GameBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel : ViewModel() {


    val team1Text = mutableStateOf("")
    val team2Text = mutableStateOf("")
    val dateText = mutableStateOf("")
    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val dateParsed: Date = runCatching { dateFormat.parse(dateText.value) }.getOrElse {
        Date() // Valeur par défaut en cas d'échec du parsing
    }


    var myList = mutableStateListOf<GameBean>()

    private var errorMessage = mutableStateOf("")




    fun loadData(){

        errorMessage.value = ""

        //list.addAll(newData)
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val newData = FootPassionAPI.getAll()
                launch(Dispatchers.Main) {
                    myList.addAll(newData)
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    errorMessage.value = "Erreur : ${e.message}"
                }
            }
        }
    }



    fun createGame() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                println("Création d'un match")
                FootPassionAPI.addGame(equipe1 = team1Text.value, equipe2 = team2Text.value, date = dateText.value)
                val newGame = GameBean(equipe1 = team1Text.value, equipe2 = team2Text.value, date = convertStringToDate(dateText.value))
                launch(Dispatchers.Main) {
                    myList.add(newGame)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


    private fun convertStringToDate(dateString: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE) // Format de la date à parser
        return formatter.parse(dateString)!!
    }


    fun updateGame(id: Long?, action: String) {
        var game = myList.find { it.id == id }
        if (action == "equipe1") {
            game?.scoreEquipe1 = game?.scoreEquipe1!! + 1
        } else if (action == "equipe2") {
            game?.scoreEquipe2 = game?.scoreEquipe2!! + 1

        } else if (action == "end") {
            game?.fini = true
        }


        viewModelScope.launch(Dispatchers.Default) {
            try {
                println("Mise à jour du score ou de l'issue d'un match")
                FootPassionAPI.updateGame(id = id, action = action)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }




    /*fun loadDetail() {

        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val selectedValue = selectMexicanFood
                if(selectedValue != null) {
                    val newData = FootPassionAPI.foodDetail(selectedValue.id)
                    launch(Dispatchers.Main) {
                        //Je viens cher'cher la position de celui qui est séléctionné
                        val position = list.indexOfFirst { it.id == selectedValue.id }
                        //Je le remplace dans la liste
                        list[position] = newData
                    }
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
                launch(Dispatchers.Main) {
                    errorMessage.value = "Erreur : ${e.message}"
                }
            }
        }
    }*/

    /*
    fun loadFakeData() {
        list.add(
            GameBean(difficulty = "Easy", id = "1", image = "https://apipics.s3.amazonaws.com/mexican_api/1.jpg", title = "Pressure cooker refried beans")
        )

        list.add(
            GameBean(difficulty = "Medium", id = "2", image = "https://apipics.s3.amazonaws.com/mexican_api/2.jpg", title = "Pressure")
        )

        list.add(
            GameBean(difficulty = "Hard", id = "3", image = "https://apipics.s3.amazonaws.com/mexican_api/3.jpg", title = "cooker")
        )

    }
    
     */
}
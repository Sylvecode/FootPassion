package com.example.footpassion.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footpassion.model.FootPassionAPI
import com.example.footpassion.model.GameBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val myList = mutableStateListOf<GameBean>()
    private var errorMessage = mutableStateOf("")


    fun loadData(): List<GameBean>? {

        errorMessage.value = ""
        var newData: List<GameBean>? = null
        try {
             newData = FootPassionAPI.getAll()
        }
        catch(e: Exception) {
            e.printStackTrace()
        }
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
        return newData
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
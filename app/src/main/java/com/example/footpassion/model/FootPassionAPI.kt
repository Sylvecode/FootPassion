
package com.example.footpassion.model

import com.example.footpassion.model.FootPassionAPI.getAll
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Date


fun main() {

}



object FootPassionAPI {

     private val gson = Gson()
     private val client = OkHttpClient()

    fun addGame(equipe1: String, equipe2: String, date: Date){

    }

    fun getAll(): List<GameBean> {
        var json = sendGet("http://localhost:8080/getAll")
        return gson.fromJson(json, Array<GameBean>::class.java).toList()
    }



    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    private fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()

        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

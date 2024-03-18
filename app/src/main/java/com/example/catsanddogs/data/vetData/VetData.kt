package com.example.catsanddogs.data.vetData

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await

class VetData: ViewModel(){
    var stateList= mutableStateOf(mutableListOf(VetUIState()))
    init{
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            stateList.value= getDataFromFirestore()
        }
    }
}

suspend fun getDataFromFirestore(): MutableList<VetUIState> {
    val db= FirebaseFirestore.getInstance()

    var aboutList= mutableListOf<VetUIState>()
    try{
        db.collection("vetInfo")
            .get()
            .await().map{
                Log.d("hello","$it")
                val result= it.toObject(VetUIState::class.java)
                aboutList.add(result)
            }
    }catch (e: FirebaseFirestoreException){
        Log.d("error","getdata")
    }
    return aboutList
}
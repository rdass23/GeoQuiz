package com.example.geoquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepo: MainRepo): ViewModel() {

    private val _usersViewState : MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val usersViewState: StateFlow<List<User>> = _usersViewState

    private val _userViewState : MutableStateFlow<User?> = MutableStateFlow(null)
    val userViewState: StateFlow<User?> = _userViewState

    fun getAllUsers(){
        viewModelScope.launch {
            mainRepo.getAllUsers()
        }
    }

    fun getUser(userName : String){
        viewModelScope.launch {
            mainRepo.getUser(userName)
        }
    }

}
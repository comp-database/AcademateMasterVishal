package com.personal.admissionModule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val repositoryOne: AcademateRepository,private val repositoryTwo: EaseBuzzRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(repositoryOne,repositoryTwo) as T
    }
}
package com.openclassrooms.realestatemanager.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.openclassrooms.realestatemanager.repository.*
import com.openclassrooms.realestatemanager.utils.AddressConverter
import com.openclassrooms.realestatemanager.view.details.DetailsViewModel
import com.openclassrooms.realestatemanager.view.edit.EditViewModel
import com.openclassrooms.realestatemanager.view.list.ListViewModel
import com.openclassrooms.realestatemanager.view.loan.LoanViewModel
import com.openclassrooms.realestatemanager.view.map.MapsViewModel
import com.openclassrooms.realestatemanager.view.search.SearchViewModel


class ViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> {
                DetailsViewModel(application, InMemoryRepository.getInstance(),
                        NetworkRepository.getInstance(application),
                        PropertyRepository.getInstance(application))
            }
            modelClass.isAssignableFrom(ListViewModel::class.java) -> {
                ListViewModel(application,
                        InMemoryRepository.getInstance(),
                        PropertyRepository.getInstance(application))
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(application, InMemoryRepository.getInstance(),
                        PropertyRepository.getInstance(application), AddressConverter())
            }
            modelClass.isAssignableFrom(EditViewModel::class.java) -> {
                EditViewModel(application,
                        InMemoryRepository.getInstance(),
                        PropertyRepository.getInstance(application),
                        AddressConverter(),
                        PoiRepository.getInstance(application),
                        NotificationRepository())
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(application, PoiRepository.getInstance(application))
            }
            modelClass.isAssignableFrom(LoanViewModel::class.java) -> {
                LoanViewModel()
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }

}
package com.openclassrooms.realestatemanager.view.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.openclassrooms.realestatemanager.repository.PoiRepository
import com.openclassrooms.realestatemanager.view.model_ui.PoiUi

class SearchViewModel(application: Application, poiRepo: PoiRepository) : AndroidViewModel(application) {

    private val allPoiMediator = MediatorLiveData<List<PoiUi>>()
    val allPoi: LiveData<List<PoiUi>> = allPoiMediator

    init {
        allPoiMediator.addSource(poiRepo.allPoi()) { allPoi ->
            allPoiMediator.value = allPoi.map { PoiUi(it.name, it.iconResourceId, false) }
        }
    }

    fun handlePoiSelection(poi: PoiUi) {
        val poiList = allPoiMediator.value!!
        poiList[poiList.indexOf(poi)].isSelected = !poiList[poiList.indexOf(poi)].isSelected
        allPoiMediator.value = poiList
    }

    fun getSelectedPoi(): List<String> = allPoi.value?.filter { it.isSelected }?.map { it.name } ?: listOf()

/*
    val allPoi = Transformations.map(poiRepo.allPoi()) { allPoi ->
        allPoi.map { PoiUi(it.name, it.iconResourceId, false) }
    }

    val selectedPoi = arrayListOf<String>()

    fun handlePoiSelection(poi: PoiUi) {
        allPoi.value?.let { list ->
            list[list.indexOf(poi)].isSelected = !list[list.indexOf(poi)].isSelected
        }
    }

 */

/*
    fun handlePoiSelection(poi: PoiUi) {
        if (poi.isSelected)
            selectedPoi.add(poi.name)
        else
            selectedPoi.remove(poi.name)
    }

 */

}
package com.misfinanzaspersonales.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.misfinanzaspersonales.models.entities.ConceptoEntity
import com.misfinanzaspersonales.repositories.ConceptoRepository
import kotlinx.coroutines.launch

class ConceptoViewModel(private val mConceptoRepository: ConceptoRepository) : ViewModel() {

    fun insertarComcepto(mConceptoEntity: ConceptoEntity) = viewModelScope.launch {
        mConceptoRepository.insertarComcepto(mConceptoEntity)
    }

    val obtenerTodosLosConceptosASC: LiveData<List<ConceptoEntity>> = mConceptoRepository.obtenerTodosLosConceptosASC.asLiveData()

    fun obtenerConcepto(concepto_tx_pk: String): LiveData<ConceptoEntity> = mConceptoRepository.obtenerConcepto(concepto_tx_pk).asLiveData()

    fun actualizarConcepto(mConceptoEntity: ConceptoEntity) = viewModelScope.launch {
        mConceptoRepository.actualizarConcepto(mConceptoEntity)
    }

    fun borrarConcepto(mConceptoEntity: ConceptoEntity) = viewModelScope.launch {
        mConceptoRepository.borrarConcepto(mConceptoEntity)
    }
}

class ConceptoViewModelFactory(private val mConceptoRepository: ConceptoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConceptoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ConceptoViewModel(mConceptoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
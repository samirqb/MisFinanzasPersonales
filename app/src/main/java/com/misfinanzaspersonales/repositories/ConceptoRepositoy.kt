package com.misfinanzaspersonales.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import com.misfinanzaspersonales.models.daos.ConceptoDao
import com.misfinanzaspersonales.models.entities.ConceptoEntity
import kotlinx.coroutines.flow.Flow

class ConceptoRepository(private val mConceptoDao: ConceptoDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertarComcepto(mConceptoEntity: ConceptoEntity){
        Log.d("TAG-3", mConceptoEntity.concepto_tx_pk)
        mConceptoDao.insertarComcepto(mConceptoEntity)
    }

    val obtenerTodosLosConceptosASC: Flow<List<ConceptoEntity>> = mConceptoDao.obtenerTodosLosConceptosASC()

    fun obtenerConcepto(concepto_tx_pk: String): Flow<ConceptoEntity> = mConceptoDao.obtenerConcepto(concepto_tx_pk)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun actualizarConcepto(mConceptoEntity: ConceptoEntity) = mConceptoDao.actualizarConcepto(mConceptoEntity)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrarConcepto(mConceptoEntity: ConceptoEntity) = mConceptoDao.borrarConcepto(mConceptoEntity)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun borrarTodosLosConceptos() = mConceptoDao.borrarTodosLosConceptos()
}
package com.misfinanzaspersonales.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.misfinanzaspersonales.models.entities.ConceptoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConceptoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarComcepto(mConceptoEntity: ConceptoEntity)

    @Query("SELECT * from conceptos_tab WHERE concepto_tx_pk = :concepto_tx_pk")
    fun obtenerConcepto(concepto_tx_pk: String): Flow<ConceptoEntity>

    @Query("SELECT * FROM conceptos_tab ORDER BY concepto_tx_pk ASC")
    fun obtenerTodosLosConceptosASC(): Flow<List<ConceptoEntity>>

    @Update
    suspend fun actualizarConcepto(mConceptoEntity: ConceptoEntity)

    @Delete
    suspend fun borrarConcepto(mConceptoEntity: ConceptoEntity)

    @Query("DELETE FROM conceptos_tab")
    suspend fun borrarTodosLosConceptos()
}
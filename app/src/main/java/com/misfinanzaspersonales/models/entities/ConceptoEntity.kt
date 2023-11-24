package com.misfinanzaspersonales.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "conceptos_tab"
    //,indices = [Index(value=["concepto_tx_pk"])]
)
data class ConceptoEntity(
    @PrimaryKey
    @ColumnInfo(name = "concepto_tx_pk")
    val concepto_tx_pk: String,

    @ColumnInfo(name = "fech_creacion_concepto")
    val fech_creacion_concepto: String
)
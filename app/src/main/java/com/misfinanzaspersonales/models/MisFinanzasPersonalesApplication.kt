package com.misfinanzaspersonales.models

import android.app.Application
import com.misfinanzaspersonales.repositories.ConceptoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MisFinanzasPersonalesApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { MisFinanzasPersonalesRoomDatabase.obtenerDB(this, applicationScope ) }
    val mConceptoRepository by lazy { ConceptoRepository(database.mConceptoDao()) }
}
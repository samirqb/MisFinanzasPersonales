package com.misfinanzaspersonales.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.misfinanzaspersonales.models.daos.*
import com.misfinanzaspersonales.models.entities.ConceptoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.Instant


//import com.misfinanzaspersonales.models.entities.MedioDePagoEntity
//import com.misfinanzaspersonales.models.entities.TipoEntity
//import com.misfinanzaspersonales.models.entities.TransaccionEntity

@Database(version = 1
    ,exportSchema = false
    ,entities = arrayOf(ConceptoEntity::class
    //    ,MedioDePagoEntity::class
    //    ,TipoEntity::class
    //    ,TransaccionEntity::class
    )
)
public abstract class MisFinanzasPersonalesRoomDatabase : RoomDatabase() {

    abstract fun mConceptoDao(): ConceptoDao
    //abstract fun mMedioDePagoDao(): MedioDePagoDao
    //abstract fun mTipoDao(): TipoDao
    //abstract fun mTransaccionDao(): TransaccionDao

    private class populateDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        //@RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    ParametriaPredeterminadaTablas(database.mConceptoDao())
                }
            }
        }

        suspend fun ParametriaPredeterminadaTablas(mConceptoDao: ConceptoDao) {

            //obtenermos la fecha y la hora actual del sistema
            //var fecha_y_hora = Instant.now().toString()
            var fecha_y_hora = "20231121T21:55:00.0000"

            //obtenemos instancia del DAO
            //var mConceptoDao = database.mConceptoDao()

            // Delete all content here.
            mConceptoDao.borrarTodosLosConceptos()

            /* Agragamos conceptos de ejemplo. */
            var mConceptoEntity = ConceptoEntity("Viveres",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Gasolina",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Medicamentos",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Ocio",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Educacion",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Servicio internet",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Servicio agua",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Servicio electricidad",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Servicio telefonia movil",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Servicio transporte",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)

            mConceptoEntity = ConceptoEntity("Caridad",fecha_y_hora)
            mConceptoDao.insertarComcepto(mConceptoEntity)
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MisFinanzasPersonalesRoomDatabase? = null

        fun obtenerDB(context: Context
                      ,scope: CoroutineScope
        ): MisFinanzasPersonalesRoomDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MisFinanzasPersonalesRoomDatabase::class.java,
                    "mis_finanzas_personales_room_db"
                )
                    .addCallback(populateDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
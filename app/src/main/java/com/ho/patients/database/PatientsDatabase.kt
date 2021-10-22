package com.ho.patients.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PatientEntity::class], version = 2, exportSchema = false)
abstract class PatientsDatabase : RoomDatabase() {

    abstract fun patientDao(): PatientsDao

    companion object {
        @Volatile
        private var INSTANCE: PatientsDatabase? = null

        val migration1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE patients ADD COLUMN gender TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN address TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN habits TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN presentHistory TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN pastHistory TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN complain TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN bp TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN temperature TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN spo2 TEXT DEFAULT ''")
                database.execSQL("ALTER TABLE patients ADD COLUMN treatment TEXT DEFAULT ''")
            }
        }

        fun getDatabase(context: Context): PatientsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatientsDatabase::class.java,
                    "patient_database"
                ).addMigrations(migration1_2).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
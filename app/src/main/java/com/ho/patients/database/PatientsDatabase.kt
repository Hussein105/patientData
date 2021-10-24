package com.ho.patients.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PatientEntity::class], version = 12, exportSchema = true)
abstract class PatientsDatabase : RoomDatabase() {

    abstract fun patientDao(): PatientsDao

    companion object {
        @Volatile
        private var INSTANCE: PatientsDatabase? = null

        private val migration1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'gender' TEXT")
            }
        }

        private val migration2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'address' TEXT")
            }
        }

        private val migration3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'habits' TEXT")
            }
        }

        private val migration4_5: Migration = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'presentHistory' TEXT")
            }
        }

        private val migration5_6: Migration = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'pastHistory' TEXT")
            }
        }

        private val migration6_7: Migration = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'complain' TEXT")
            }
        }

        private val migration7_8: Migration = object : Migration(7, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'bloodPressure' TEXT")
            }
        }

        private val migration8_9: Migration = object : Migration(8, 9) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'temperature' TEXT")
            }
        }

        private val migration9_10: Migration = object : Migration(9, 10) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'oxygenSaturation' TEXT")
            }
        }

        private val migration10_11: Migration = object : Migration(10, 11) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'treatment' TEXT")
            }
        }

        private val migration11_12: Migration = object : Migration(11, 12) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'patients' ADD COLUMN 'age' TEXT")
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
                ).addMigrations(
                    migration1_2,
                    migration2_3,
                    migration3_4,
                    migration4_5,
                    migration5_6,
                    migration6_7,
                    migration7_8,
                    migration8_9,
                    migration9_10,
                    migration10_11,
                    migration11_12
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
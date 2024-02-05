package id.mzennis.sqldelight.sample

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import id.mzennis.sqldelight.sample.data.AnimalQueries
import id.mzennis.sqldelight.sample.data.AnimalRepository
import id.mzennis.sqldelight.sample.data.BreedQueries
import id.mzennis.sqldelight.sample.data.SpeciesQueries

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injector {

    /**
     * Creates an instance of [Database]
     */
    fun provideDatabase(appContext: Context): Database {
        return Database(
            driver = AndroidSqliteDriver(
                Database.Schema,
                appContext,
                "Sample.db",
                callback = object : AndroidSqliteDriver.Callback(Database.Schema) {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        db.setForeignKeyConstraintsEnabled(true)
                    }
                }
            ),
        )
    }

    fun provideAnimalQueries(database: Database): AnimalQueries {
        return database.animalQueries
    }

    fun provideSpeciesQueries(database: Database): SpeciesQueries {
        return database.speciesQueries
    }

    fun provideBreedQueries(database: Database): BreedQueries {
        return database.breedQueries
    }

    fun provideAnimalRepository(
        animalQueries: AnimalQueries,
        speciesQueries: SpeciesQueries,
        breedQueries: BreedQueries
    ): AnimalRepository {
        return AnimalRepository(animalQueries, speciesQueries, breedQueries)
    }
}
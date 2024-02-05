package id.mzennis.sqldelight.sample.data

import id.mzennis.sqldelight.sample.ui.model.AnimalUiModel

class AnimalRepository(
    private val animalQueries: AnimalQueries,
    private val speciesQueries: SpeciesQueries,
    private val breedQueries: BreedQueries
) {

    fun getAll(): List<AnimalUiModel> {
        return animalQueries.getAll().executeAsList().map {
            AnimalUiModel(
                it.animalName,
                it.species,
                it.breed,
                it.imageUrl.orEmpty(),
                it.shortDesc.orEmpty()
            )
        }
    }

    fun insert(
        name: String,
        speciesId: Long,
        breedId: Long?,
        shortDesc: String?,
        imageUrl: String?
    ) {
        animalQueries.insert(
            name = name,
            shortDesc = shortDesc,
            imageUrl = imageUrl,
            speciesId = speciesId,
            breedId = breedId
        )
    }

    fun updateImageUrl(imageUrl: String, id: Long) {
        animalQueries.updateImageUrlById(imageUrl, id)
    }

    fun insertBreed(name: String, speciesId: Long) {
        breedQueries.insert(name, speciesId)
    }

    fun insertSpecies(name: String) {
        speciesQueries.insert(name)
    }
}
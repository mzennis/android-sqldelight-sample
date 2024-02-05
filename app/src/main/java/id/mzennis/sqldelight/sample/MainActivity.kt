package id.mzennis.sqldelight.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import id.mzennis.sqldelight.sample.ui.theme.SQLDelightSampleTheme

class MainActivity : ComponentActivity() {

    private val database by lazy { Injector.provideDatabase(this.applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animalRepository = Injector.provideAnimalRepository(
            animalQueries = Injector.provideAnimalQueries(database),
            speciesQueries = Injector.provideSpeciesQueries(database),
            breedQueries = Injector.provideBreedQueries(database)
        )

        animalRepository.insert("Milo", 1, 1, " Affectionate, Loyal, and Fluffy", null)

        setContent {
            SQLDelightSampleTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val animals = animalRepository.getAll()

                    Column {
                        for (animal in animals) {
                            Greeting(animal.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    SQLDelightSampleTheme {
        Greeting("Android")
    }
}
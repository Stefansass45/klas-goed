package vcmsa.projects.jetpackbackpoki.Poki

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import vcmsa.projects.jetpackbackpoki.data.PokemonListItem

@Composable
fun HomeScreen(viewModel: PokemonViewModel, onPokemonClick: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        uiState.error != null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: ${uiState.error}")
        }

        else -> LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(uiState.pokemonList) { pokemon ->
                PokemonListItemRow(pokemon = pokemon, onClick = { onPokemonClick(pokemon.name) })
            }
        }
    }
}

@Composable
fun PokemonListItemRow(pokemon: PokemonListItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = pokemon.name.replaceFirstChar { it.uppercase() }, modifier =
                Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: PokemonViewModel, pokemonName: String, onNavigateBack: () -> Unit) {
    LaunchedEffect(key1 = pokemonName) {
        viewModel.fetchPokemonDetail(pokemonName)
    }
    val uiState by viewModel.uiState.collectAsState()
    val pokemon = uiState.selectedPokemon

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pokemon?.name?.replaceFirstChar { it.uppercase() } ?: "Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator()
                uiState.error != null -> Text("Error: ${uiState.error}")
                pokemon != null -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = pokemon.sprites.frontDefault,
                            contentDescription = pokemon.name,
                            modifier = Modifier.padding(200.dp)
                        )
                        Text(
                            "Name: ${pokemon.name.replaceFirstChar { it.uppercase() }}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            "Height: ${pokemon.height * 10} cm",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            "Weight: ${pokemon.weight / 10.0} kg", style =
                                MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AboutScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
        Text("This is a Pokedex App built with jetpack compose to demostrate navigation",
        textAlign = TextAlign.Center)
    }
}
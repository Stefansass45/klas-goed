package vcmsa.projects.jetpackbackpoki.Poki

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import retrofit2.Retrofit
import vcmsa.projects.jetpackbackpoki.data.PokemonDetailResponse
import vcmsa.projects.jetpackbackpoki.data.PokemonListItem
import vcmsa.projects.jetpackbackpoki.data.RetrofitClient

data class PokemonUiState(
    val pokemonList: List<PokemonListItem> = emptyList(),
    val selectedPokemon: PokemonDetailResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class PokemonViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonUiState())
    val uiState: StateFlow<PokemonUiState> = _uiState

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList(){
        _uiState.value = PokemonUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getPokemonList()
                _uiState.value = PokemonUiState(pokemonList = response.results)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }
    fun fetchPokemonDetail(name: String){
        _uiState.value = _uiState.value.copy(isLoading = true,
            selectedPokemon = null)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getPokemonDetail(name)
                _uiState.value = _uiState.value.copy(isLoading = false,
                    selectedPokemon = response)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false,
                    error = e.message)
            }
        }
    }
}
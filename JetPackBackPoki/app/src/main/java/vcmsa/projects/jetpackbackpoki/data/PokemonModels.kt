package vcmsa.projects.jetpackbackpoki.data
import com.google.gson.annotations.SerializedName

data class PokemonListResponse(val results: List<PokemonListItem>)
data class PokemonListItem(val name: String, val url: String)
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSprites
)
data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String,
)
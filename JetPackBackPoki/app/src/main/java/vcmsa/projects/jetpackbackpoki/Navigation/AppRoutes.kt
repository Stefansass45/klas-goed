package vcmsa.projects.jetpackbackpoki.Navigation

object AppRoutes {
    const val HOME="home"
    const val DETAILS="details/{pokemonName}"
    const val ABOUT="about"

    fun createDetailsRoute(pokemonName:String)="details/$pokemonName"
}
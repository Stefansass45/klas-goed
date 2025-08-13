package vcmsa.projects.jetpackapibasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import vcmsa.projects.jetpackapibasics.ui.LoanApp
import vcmsa.projects.jetpackapibasics.ui.theme.JetPackAPIBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackAPIBasicsTheme {
                LoanScreen()
                LoanApp()
            }
        }
    }
}
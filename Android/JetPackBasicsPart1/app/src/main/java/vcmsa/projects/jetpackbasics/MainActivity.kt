package vcmsa.projects.jetpackbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vcmsa.projects.jetpackbasics.ui.theme.JetPackBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(Color.Black)
                            .fillMaxSize()

                    ) {
                        Greeting("Gorlack the Destroyer")
                        Greeting("Greta the Healer")
                        Counter()
                        StatefulCounter()
                        Quote()
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
        modifier = modifier.padding(16.dp)

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackBasicsTheme {
        Greeting("Android")
    }
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }
    Button(onClick = { count++ }) {
        Text(text = "I've been clicked $count times")
    }
}

@Composable
fun CounterDisplay(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onIncrement, modifier = modifier) {
        Text(text = "I've been clicked $count times")
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    CounterDisplay(
        count = count,
        onIncrement = { count++ },
        modifier = modifier
    )
}

@Composable
fun QuoteCard(quote: Quote, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = quote.text)
        Text(text = "- ${quote.author}")
    }
}

@Composable
fun Quote() {
    val quoteList = listOf(
        Quote("quote 1", "ben1"),
        Quote("quote 2", "ben2"),
        Quote("quote 3", "ben3")
    )
    var quoteIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        QuoteCard(quote = quoteList[quoteIndex])
        Button(onClick = { quoteIndex = (quoteIndex + 1) % quoteList.size }) {
            Spacer(modifier = Modifier.padding(24.dp))

            Button(onClick = {
                quoteIndex = (quoteIndex + 1) % quoteList.size
            }) {
                Text("New Quote")
            }
        }
    }
}
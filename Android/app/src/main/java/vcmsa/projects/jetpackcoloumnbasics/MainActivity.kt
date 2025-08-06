package vcmsa.projects.jetpackcoloumnbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import vcmsa.projects.jetpackcoloumnbasics.ui.theme.JetpackColoumnBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackColoumnBasicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //LazyListName()
                    //InteractiveTodoList()
                    InteractiveTodoListv2()
                }
            }
        }
    }
}

@Composable
fun LazyListName(modifier: Modifier = Modifier) {
    //we still have our list of 100 names
    val names = List(100) { "User number ${it + 1}" }

    //lazy column to load a few items at a time
    LazyColumn(modifier = modifier) {
        items(names) { name ->
            Text(
                text = name,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Composable
fun InteractiveTodoList(modifier: Modifier = Modifier) {
    val todoItems = remember {
        mutableStateListOf(
            "Learn Jetpack Compose", "Do laundry",
            "Call mom/other/IT", "Buy milk", "Walk the dog"
        )
    }


    var selectedItem by remember { mutableStateOf<String?>("") }

    LazyColumn(modifier = modifier) {
        items(todoItems) { item ->
            val isSelected = item == selectedItem
            val backgroundColor = if (isSelected) {
                Color.Red
            } else {
                Color.Transparent
            }
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedItem = if (isSelected) null else item
                    }
                    .background(backgroundColor)
                    .padding(16.dp)
            )
        }
    }
}


@Composable
fun TodoRow(
    item: TodoItem, onItemClicked: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.text,
            style = LocalTextStyle.current.copy(
                textDecoration = if (item.isCompleted)
                    TextDecoration.LineThrough else null
            ),
            color = if (item.isCompleted) Color.Gray else Color.Black,
            modifier = Modifier
                .weight(1f)
        )
        IconButton(onClick = { onItemClicked(item) }) {
            Icon(
                imageVector = if (item.isCompleted) Icons.Default.CheckCircle
                else Icons.Default.Face,
                contentDescription = "Toggle completion",
                tint = if (item.isCompleted) Color.Green else Color.Gray
            )
        }

    }
}

data class TodoItem(val text: String, var isCompleted: Boolean = false)

@Composable
fun InteractiveTodoListv2(modifier: Modifier = Modifier) {
    var todoItems by remember {
        mutableStateOf(
            listOf(
                TodoItem("Learn Jetpack Compose"),
                TodoItem("Do laundry"),
                TodoItem("Call mom/other/IT"),
                TodoItem("Buy milk"),
                TodoItem("Walk the dog")
            )
        )
    }
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(todoItems) { item ->
            TodoRow(
                item = item,
                onItemClicked = { clickedItem ->
                    todoItems = todoItems.map {
                        if (it.text == clickedItem.text) {
                            it.copy(isCompleted = !it.isCompleted)
                        } else {
                            it
                        }
                    }
                }
            )
        }
    }
}




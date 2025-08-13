package vcmsa.projects.jetpackapibasics.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vcmsa.projects.jetpackapibasics.LoanResponse
import vcmsa.projects.jetpackapibasics.ui.api.LoanViewModel

class LoanScreen {
}

@Composable
fun LoanListScreen(loanViewModel: LoanViewModel, onNavigateToCreate: () -> Unit) {
    val uiState by loanViewModel.UiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                //buttons to navigate to the add loan screen
                FloatingActionButton(onClick = onNavigateToCreate) {
                    Icon(Icons.Default.Add, contentDescription = "Create Loan")
                }
                //new button to refresh the list
                FloatingActionButton(onClick = { loanViewModel.fetchLoans() }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Refresh Loans")
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator()
                uiState.error != null -> Text(
                    text = uiState.error!!,
                    color = MaterialTheme.colorScheme.error
                )

                else -> LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.loans) { loan ->
                        LoanItem(
                            loan = loan,
                            onDelete = { loanViewModel.deleteLoan(loan.loanId) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoanItem(loan: LoanResponse, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp)
            ) {
                Text("Loan ID: ${loan.loanId}", style = MaterialTheme.typography.titleMedium)
                Text("Member: ${loan.memberID}", style = MaterialTheme.typography.bodySmall)
                Text("Amount: ${loan.amount}", style = MaterialTheme.typography.bodyLarge)
                Text("Message: ${loan.message}", style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Loan",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun CreateLoanScreen(loanViewModel: LoanViewModel, onLoanCreated: () -> Unit) {
    var amount by remember { mutableStateOf("") }
    var memberID by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Added by Android App") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Create New Loan",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Member ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = memberID,
            onValueChange = { memberID = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val amountDouble = amount.toDoubleOrNull()
                if (amountDouble != null && memberID.isNotBlank()) {
                    loanViewModel.createLoan(amountDouble, memberID, message)
                    onLoanCreated() //navigate back
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Loan")
        }
    }
}

package vcmsa.projects.jetpackapibasics.ui.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import vcmsa.projects.jetpackapibasics.LoanRequest
import vcmsa.projects.jetpackapibasics.LoanResponse
import vcmsa.projects.jetpackapibasics.RetrofitClient

// State for the whole screen, including the list of loans
data class LoanScreenState(
    val loans: List<LoanResponse> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

class LoanViewModel : ViewModel() {
    private val _loanUIState = MutableStateFlow(LoanScreenState())
    val UiState: StateFlow<LoanScreenState> = _loanUIState

    init {
        fetchLoans()
    }

    fun fetchLoans() {
        _loanUIState.value = _loanUIState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val loans = RetrofitClient.instance.getLoans()
                _loanUIState.value = _loanUIState.value.copy(isLoading = false)
            } catch (e: Exception) {
                _loanUIState.value = _loanUIState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun createLoan(amount: Double, memberID: String, message: String) {
        viewModelScope.launch {
            try {
                val newLoan = LoanRequest(amount, memberID, message)
                RetrofitClient.instance.createLoan(newLoan)
                fetchLoans()
            } catch (e: Exception) {
                _loanUIState.value = _loanUIState.value.copy(error = e.message)
            }
        }
    }

    fun deleteLoan(loanId: Int){
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.deleteLoan(loanId)
                if (response.isSuccessful){
                    // the deletion was successful (e.g., status 204), so refresh the list.
                    fetchLoans()
                }
                else{
                    // the server returned an error code (e.g., 404 Not Found, 500 Server Error)
                    _loanUIState.value = _loanUIState.value.copy(error = "Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                // this catches network errors, like no internet connection.
                _loanUIState.value = _loanUIState.value.copy(error = e.message)
            }

        }
    }
}
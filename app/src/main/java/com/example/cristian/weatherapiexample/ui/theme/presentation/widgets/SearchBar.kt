import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.cristian.weatherapiexample.R
import com.example.cristian.weatherapiexample.domain.models.CityUI
import com.example.cristian.weatherapiexample.ui.theme.viewmodels.CityViewModel
import com.example.cristian.weatherapiexample.ui.theme.viewmodels.ForecastViewModel
import com.example.cristian.weatherapiexample.ui.theme.viewmodels.ViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    cityViewModel: CityViewModel,
    forecastViewModel: ForecastViewModel
) {
    var query by remember { mutableStateOf("") }
    val status by cityViewModel.status.observeAsState()
    var suggestions by remember { mutableStateOf(listOf<String>()) }
    var citySelected by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    if (status is ViewState.Success<*>) {
        suggestions =
            (status as ViewState.Success<List<CityUI>>).data.map { "${it.name}, ${it.country}" }
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = query,
            onValueChange = { newValue ->
                query = newValue
                cityViewModel.searchCity(query)
                citySelected = false
            },
            label = { Text("Buscar") },
            singleLine = true,
            modifier = modifier,
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = {
                        query = ""
                        cityViewModel.resetApiResponseStatus()
                        suggestions = listOf()
                        focusManager.clearFocus()
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Borrar texto")
                    }
                }
            }
        )

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.purple_200))
        ) {
            items(suggestions) { suggestion ->
                Text(
                    text = suggestion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            forecastViewModel.searchForecast(suggestion)
                            suggestions = listOf()
                            citySelected =
                                true
                            focusManager.clearFocus()
                        }
                        .padding(8.dp)
                )
            }
            if (suggestions.isEmpty() && query.isNotEmpty() && !citySelected) {
                item {
                    Text(
                        text = "No se encontraron resultados",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
package com.example.mvprxjava.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.test.services.storage.file.PropertyFile
import com.example.mvprxjava.model.Element
import java.lang.reflect.Modifier

@Composable
fun ListUI(
    elements: List<Element>,
    isLoading: Boolean,
    errorOccurred: Boolean,
    view: ListContract.View
) {
    PropertyFile.Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Show loading spinner if data is loading
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        // Show error message if there's an error
        if (errorOccurred) {
            Text("Error loading data", style = MaterialTheme.typography.bodyMedium)
        }

        // Display the list of elements if they are available
        if (elements.isNotEmpty() && !isLoading && !errorOccurred) {
            LazyColumn {
                items(elements) { element ->
                    ListItem(element)
                }
            }
        }
    }
}

@Composable
fun ListItem(element: Element) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Text(
            text = "${element.id}: ${element.title}",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

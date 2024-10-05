package com.example.screenchangekotlin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FirstScreen(navigateToSecondScreen: (String, Int) -> Unit) {

    val name = remember { mutableStateOf("") }
    val age = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the first Screen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name") })
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = age.value.toString(), onValueChange = { newValue ->
            // Try to parse the new value to an integer
            val parsedValue = newValue.toIntOrNull()
            // Update age only if parsing is successful
            if (parsedValue != null) {
                age.intValue = parsedValue
            }
        },
            label = { Text("Age") })


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navigateToSecondScreen(name.value, age.intValue)
        }) {
            Text(text = "Go to Second Page")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun FirstPreview() {
    FirstScreen { name, age ->
        // Here you can handle the navigation or just log the values
        println("Navigating to second screen with Name: $name, Age: $age")
    }
}
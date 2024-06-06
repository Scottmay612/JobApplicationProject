package com.example.jobapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jobapplication.ui.theme.JobApplicationTheme
import com.example.jobapplication.ui.theme.JobApplicationTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // Calls the main layout function to run the app.
                    JobApplicationLayout()
                }
            }
        }
    }
}

// Allows the user to type in a text field.
@Composable
fun EditTextField(
    // This is a string from the strings.xml file.
    @StringRes label: Int,
    value: String,

    // Returns nothing when the button is clicked.
    onValueChange: (String) -> Unit,

    // You can use different keyboard types for different types of inputs.
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = {Text(stringResource(label))},
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

// Contains the layout of the program. Each page is split into its
// own function to keep things clean and simple.
@Composable
fun JobApplicationLayout() {

    // Declare and remember the page number.
    var pageCount by remember { mutableIntStateOf(0) }

    Column(
        // Set default modifier.
        modifier = Modifier,

        // Center the context horizontally.
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        when (pageCount) {
            // Change pages when the button is clicked.
            0 -> PageOne()
            1 -> PageTwo()
            else -> PageOne()
        }

        // Button to go to the next page.
        Button(onClick = { when (pageCount) {
            // Increases the page count when the button is clicked.
            1 -> pageCount = 0
            else -> pageCount ++
        } }) {
            Text(text = "Next Page")
        }
    }
}
@Composable
fun PageOne() {
    // Declare and remember the user's information as they type it.
    var name by remember {mutableStateOf("")}
    var address by remember { mutableStateOf("")}
    var phoneNumber by remember { mutableStateOf("")}
    var emailAddress by remember { mutableStateOf("")}

    // Add spacer between elements on page.
    Spacer(modifier = Modifier.height(40.dp))
    Text(stringResource(id = R.string.enter_name))

    // Add spacer between elements on page.
    Spacer(modifier = Modifier.height(40.dp))

    // Name text field.
    EditTextField(
        label = R.string.name,
        value = name,

        // Changes name to be whatever they typed in.
        onValueChange = {name = it},

        keyboardOptions = KeyboardOptions.Default.copy(
            // Add text keyboard with "Next" action button.
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
    )
    // Add spacer between elements on page.
    Spacer(modifier = Modifier.height(40.dp))

    // Address text field.
    EditTextField(
        label = R.string.address,
        value = address,

        // Change address to whatever the user typed in.
        onValueChange = {address = it},

        keyboardOptions = KeyboardOptions.Default.copy(
            // Add text keyboard with "Next" action button.
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
    )
    // Add spacer between elements on page.
    Spacer(modifier = Modifier.height(40.dp))

    // Phone number text field.
    EditTextField(
        label = R.string.phone_number,
        value = phoneNumber,

        // Change address to be whatever the user typed in.
        onValueChange = {phoneNumber = it},

        keyboardOptions = KeyboardOptions.Default.copy(
            // Add number keyboard with "Next" action button.
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
    )
    // Add spacer between elements on page.
    Spacer(modifier = Modifier.height(40.dp))

    // Email address text field.
    EditTextField(
        label = R.string.email_address,
        value = emailAddress,

        // Change email address to whatever the user typed in.
        onValueChange = {emailAddress = it},
        keyboardOptions = KeyboardOptions.Default.copy(

            // Add email keyboard with "Done" action button.
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        )
    )
    // Add spacer between elements on page.
    Spacer(modifier = Modifier.height(40.dp))
}

// Code for the second page of info input.
@Composable
fun PageTwo(modifier: Modifier = Modifier) {
    // Declare and remember work ethic and strength variables.
    var workEthic by remember { mutableStateOf("") }
    var strength by remember { mutableStateOf("")}

    // Add double spaced spacer because there is no text at the top of this page.
    Spacer(modifier = Modifier.height(80.dp))

    // Work ethic text field.
    EditTextField(
        label = R.string.work_ethic,
        value = workEthic,

        // Change workEthic to whatever the user typed in.
        onValueChange = { workEthic = it },

        keyboardOptions = KeyboardOptions.Default.copy(
            // Add text keyboard with "Next" action button.
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
    // Add spacer between elements on page.
    Spacer(modifier = Modifier.height(40.dp))
    EditTextField(
        label = R.string.strength,
        value = strength,
        onValueChange = { strength = it},
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Default
        )
    )
    // Add spacer between elements on page.
    Spacer(modifier = Modifier.height(40.dp))
}

// This function needs to be fixed.
@Composable
fun AllInfoPage(name: String,
                address: String,
                phoneNumber: String,
                email: String,
                workEthic: String,
                strength: String,) {
    Text(text = "Is this information correct?")
    val infoList: MutableList<String> = mutableListOf()
    infoList.add(name)
    infoList.add(address)
    infoList.add(phoneNumber)
    infoList.add(email)
    infoList.add(workEthic)
    infoList.add(strength)
    for (item in infoList) {
        Text(text = item)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JobApplicationTheme {
        // Show a preview of what the pages look like.
        JobApplicationLayout()
    }
}
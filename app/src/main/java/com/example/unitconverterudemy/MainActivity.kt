package com.example.unitconverterudemy

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterudemy.ui.theme.UnitConverterUdemyTheme
import kotlin.math.roundToInt
import kotlin.time.times

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterUdemyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                    //Greeting("My name is Arpan")
                    // Greeting("My name is Arpan")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun unitConverter() {
    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outputUnit by remember {
        mutableStateOf("Centimeters")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    val conversionFactor = remember { mutableStateOf(1.00) }
    var conversionFactor2 by remember {
        mutableStateOf(0.01)
    }

    fun product() {
        val inputvalueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputvalueDouble * conversionFactor.value * (100 / conversionFactor2))/ 100

        outputValue = String.format("%.2f", result)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Here all the UI elements will be stacked with each other
        Text(text = "Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it
                            product()},
            label = { Text(text = "Enter the Value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
//            val context = LocalContext.current
//            Button(onClick = {
//                Toast.makeText(context, "Thanks for Clicking", Toast.LENGTH_LONG).show()
//            }) {
//                Text(text = "My Button")
//            }

            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        inputUnit = "Centimeters"
                        iExpanded = false
                        conversionFactor.value=0.01
                        product()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        inputUnit = "Meters"
                        iExpanded = false
                        conversionFactor.value=1.0
                        product()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        inputUnit = "Milimeters"
                        iExpanded = false
                        conversionFactor.value=0.001
                        product()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        inputUnit = "Feet"
                        iExpanded = false
                        conversionFactor.value=0.3048
                        product()
                    })
                }
            }
            Spacer(modifier = Modifier.width(30.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        outputUnit = "Centimeters"
                        oExpanded = false
                        conversionFactor2 = 0.01
                        product()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        outputUnit = "Meters"
                        oExpanded = false
                        conversionFactor2 = 1.0
                        product()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        outputUnit = "Milimeters"
                        oExpanded = false
                        conversionFactor2 = 0.001
                        product()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        outputUnit = "Feet"
                        oExpanded = false
                        conversionFactor2 = 0.3048
                        product()
                    })
                }
            }


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: ${outputValue} ${outputUnit}")
    }
}

@Preview(showBackground = true)
@Composable
fun unitConverterPreview() {
    unitConverter()
}








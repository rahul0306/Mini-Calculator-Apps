package com.example.bmicaluclator.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.bmicaluclator.R

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val buttonCircle = 0.13f
    val scrollState = rememberScrollState()
    var weight by remember { mutableStateOf(50f) }
    var age by remember { mutableStateOf(30) }
    var height by remember { mutableStateOf(150f) }
    var showPopUp by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val imageHeight = screenHeight * 0.15f
    var isMale by remember { mutableStateOf(true) }
    val selectedColor = Color(0x8800FF00)
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(20.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "BMI CALCULATOR",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier
                        .height(imageHeight)
                        .aspectRatio(1f).border(1.dp,Color.Black)
                        .clickable { isMale = true }) {
                        Image(
                            painter = painterResource(id = R.drawable.m),
                            contentDescription = "Female",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                        if (isMale) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(selectedColor)
                                    .zIndex(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(17.dp))
                    Text(text = "MALE", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier
                        .height(imageHeight)
                        .aspectRatio(1f).border(1.dp,Color.Black)
                        .clickable { isMale = false }) {
                        Image(
                            painter = painterResource(id = R.drawable.f),
                            contentDescription = "Female",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                        if (!isMale) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(selectedColor)
                                    .zIndex(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(17.dp))
                    Text(text = "FEMALE", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.onBackground)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "HEIGHT (cm)",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.background
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = "${height.toInt()}",
                    fontSize = 55.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.background

                )
                Slider(
                    value = height,
                    onValueChange = { height = it },
                    valueRange = 100f..300f,
                    steps = 200
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(color = MaterialTheme.colorScheme.onBackground)
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "WEIGHT",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.background

                        )
                        Text(
                            text = "${weight.toInt()}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.background

                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                onClick = { if (weight > 1) weight-- },
                                modifier = Modifier
                                    .fillMaxWidth(buttonCircle)
                                    .aspectRatio(1f)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.background,
                                        shape = CircleShape
                                    ),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Icon(Icons.Default.Remove, contentDescription = null)
                            }
                            Button(
                                onClick = { weight++ },
                                modifier = Modifier
                                    .fillMaxWidth(buttonCircle)
                                    .aspectRatio(1f)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.background,
                                        shape = CircleShape
                                    ),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues(0.dp)

                            ) {
                                Icon(Icons.Default.Add, contentDescription = null)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(color = MaterialTheme.colorScheme.onBackground)
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "AGE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.background

                        )
                        Text(
                            text = "$age",
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.background

                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                onClick = { if (age > 1) age-- },
                                modifier = Modifier
                                    .fillMaxWidth(buttonCircle)
                                    .aspectRatio(1f)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.background,
                                        shape = CircleShape
                                    ),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues(0.dp)

                            ) {
                                Icon(Icons.Default.Remove, contentDescription = null)
                            }
                            Button(
                                onClick = { age++ },
                                modifier = Modifier
                                    .fillMaxWidth(buttonCircle)
                                    .aspectRatio(1f)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.background,
                                        shape = CircleShape
                                    ),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                contentPadding = PaddingValues(0.dp)

                            ) {
                                Icon(Icons.Default.Add, contentDescription = null)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        val bmi = calculateBmi(height = height, weight = weight)
                        message = "Your bmi is %.2f.".format(bmi) + when {
                            age < 18 -> {
                                if (isMale){
                                    when{
                                        bmi < 18 -> "You are Underweight"
                                        bmi < 23 -> "You have a healthy weight"
                                        bmi < 27 -> "You are Overweight"
                                        else -> "You are Obese"
                                    }
                                }else{
                                    when{
                                        bmi < 17.5 -> "You are Underweight"
                                        bmi < 22.5 -> "You have a healthy weight"
                                        bmi < 27 -> "You are Overweight"
                                        else -> "You are Obese"
                                    }
                                }
                            }
                            else->{
                                if (isMale){
                                    when{
                                        bmi < 20 -> "You are Underweight"
                                        bmi < 24.9 -> "You have a healthy weight"
                                        bmi < 29.9 -> "You are Overweight"
                                        else -> "You are obese"
                                    }
                                }else{
                                    when{
                                        bmi < 19 -> "You are Underweight"
                                        bmi < 23.9 -> "You have a healthy weight"
                                        bmi < 28.9 -> "You are Overweight"
                                        else -> "You are obese"
                                    }
                                }
                            }
                        }
                        showPopUp = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = "CALCULATE YOUR BMI",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
        if (showPopUp) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x99000000))
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(24.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = message, fontSize = 24.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { showPopUp = false }) {
                            Text("OK")
                        }
                    }
                }
            }
        }


    }
}

fun calculateBmi(height: Float, weight: Float): Float {
    val heightInMetres = height / 100
    return if (heightInMetres > 0) weight / (heightInMetres * heightInMetres) else 0f
}
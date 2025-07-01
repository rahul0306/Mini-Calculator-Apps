package com.example.tipcalculator.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.R

@SuppressLint("ResourceAsColor")
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    var amount by remember {
        mutableStateOf("")
    }
    var tip by remember {
        mutableStateOf("")
    }
    var split by remember {
        mutableStateOf(1)
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val buttonSize = screenWidth * 0.15f
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 140.dp, horizontal = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Bill Amount",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            color = Color(R.color.customGray)
        )
        Spacer(modifier = Modifier.height(7.dp))
        TextField(
            value = amount, onValueChange = { amount = it }, textStyle = TextStyle(
                fontSize = 25.sp
            ), modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(R.color.customGray),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)), colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Tip (%)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            color = Color(R.color.customGray)
        )
        Spacer(modifier = Modifier.height(7.dp))
        TextField(
            value = tip,
            onValueChange = { tip = it },
            textStyle = TextStyle(
                fontSize = 25.sp
            ),
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(R.color.customGray),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Split",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            color = Color(R.color.customGray)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(buttonSize)
                    .aspectRatio(1f)
            ) {
                Button(
                    onClick = { if (split > 1) split-- }, modifier = Modifier
                        .aspectRatio(1f)
                        .clip(CircleShape)
                ) {
                    Text(text = "-", fontSize = 25.sp)
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "$split", fontSize = 35.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .width(buttonSize)
                    .aspectRatio(1f)
            ) {
                Button(
                    onClick = { split++ },
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(CircleShape)
                ) {
                    Text(text = "+", fontSize = 25.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Per Person",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            color = Color(R.color.customGray)
        )
        Spacer(modifier = Modifier.height(10.dp))
        val result = if (amount.isNotBlank() && tip.isNotBlank()) {
            calculateTip(amount = amount, tip = tip, split = split).toString()
        } else {
            "Enter bill and tip"
        }
        Text(
            text = result,
            fontSize = if (result == "Enter bill and tip") 17.sp else 30.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            color = Color.Black
        )
    }
}

fun calculateTip(amount: String, tip: String, split: Int): Int {
    //val splitInt = split.toIntOrNull() ?: 1
    val amountInt = amount.toIntOrNull() ?: 0
    val tipInt = tip.toIntOrNull() ?: 0
    val tipAmount = (amountInt * tipInt) / 100
    return (amountInt + tipAmount) / split
}
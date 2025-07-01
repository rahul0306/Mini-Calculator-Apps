package com.example.calculator.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.objecthunter.exp4j.ExpressionBuilder

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var enteredNumber by remember {
        mutableStateOf("")
    }
    val buttonList = listOf(
        "AC",
        "CL",
        "%",
        "/",
        "7",
        "8",
        "9",
        "x",
        "4",
        "5",
        "6",
        "-",
        "1",
        "2",
        "3",
        "+",
        "0",
        ".",
        "=",
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = enteredNumber,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            items(buttonList, span = { item ->
                if (item == "0") {
                    GridItemSpan(2)
                } else {
                    GridItemSpan(1)
                }
            }) { button ->
                Button(
                    onClick = {
                        enteredNumber = when (button) {
                            "AC" -> ""
                            "CL" -> enteredNumber.dropLast(1)
                            "=" -> calculateNumber(enteredNumber)
                            else -> enteredNumber + button
                        }
                    },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.aspectRatio(if (button == "0") 2f else 1f)
                ) {
                    Text(
                        text = button,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

fun calculateNumber(expression: String): String {
    return try {
        val exp = expression.replace("x", "*")
        val result = ExpressionBuilder(exp).build().evaluate()
        if (result % 1 == 0.0) result.toInt().toString() else result.toString()
    } catch (e: Exception) {
        "Error"
    }
}

package com.example.catsanddogs.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.catsanddogs.ui.theme.textFieldContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(modifier: Modifier = Modifier,
                      name: String,
                      onTextSelected: (String)->Unit,
                      errorStatus: Boolean) {
    var text by remember { mutableStateOf("") }
    var plength by remember { mutableStateOf("") }

    Text(
        text = name ,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left
    )
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange ={text = it
            plength = it.length.toString()
            onTextSelected(it)
        },
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.textFieldContainer

        ),
        isError = !errorStatus
    )
}
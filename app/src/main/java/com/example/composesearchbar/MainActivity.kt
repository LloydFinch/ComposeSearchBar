package com.example.composesearchbar

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(MainViewModel::class.java)
        setContent { HomeScreen() }

        viewModel.getDisplayData().observe(this, {
            Log.d(TAG, "size: ${it.size}")
        })
    }

    @Composable
    fun HomeScreen() {
        Column {
            SearchBar { keyword ->
                Toast.makeText(this@MainActivity, "keyword is $keyword", Toast.LENGTH_SHORT).show()
                viewModel.filter(keyword = keyword)
            }

            Spacer(modifier = Modifier.size(8.dp))

            ContentList(contentData = viewModel)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {

    }

    @Composable
    fun SearchBar(onSearch: (keyword: String) -> Unit) {
        var value by remember { mutableStateOf("") }
        OutlinedTextField(
            label = { Text(text = "Please input keyword") },
            value = value,
            onValueChange = { value = it },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.DarkGray, fontWeight = FontWeight.W700),
            trailingIcon = @Composable { Image(imageVector = Icons.Filled.Clear, contentDescription = null, Modifier.clickable { value = "" }) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch.invoke(value) }),
        )
    }

    @Composable
    fun ContentList(contentData: ContentsListData) {
        val contents by contentData.getDisplayData().observeAsState(listOf())
        LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
            items(contents) { content ->
                Text(text = "content is $content", modifier = Modifier.padding(4.dp))
            }
        }
    }
}



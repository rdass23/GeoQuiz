package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geoquiz.persistence.CurrentUser
import com.example.geoquiz.ui.theme.GeoQuizTheme

class HighScoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeoQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HighScoreScreen()
                }
            }
        }
    }
}

@Composable
fun HighScoreScreen() {
    Scaffold(topBar = {
        TopAppBarContent()
    }) {
        Column(Modifier.fillMaxSize()) {
            Text(
                text = "MY HIGH-SCORE",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4
            )
            val user = CurrentUser.getUser()
            Row(Modifier.fillMaxWidth()) {
                HighScore(
                    "Fact",
                    user.factHighScore.toString() + "/10",
                    modifier = Modifier.weight(.5f)
                )
                HighScore(
                    "Flag",
                    user.flagHighScore.toString() + "/10",
                    modifier = Modifier.weight(.5f)
                )
            }
        }
    }
}

@Composable
fun HighScore(type: String, score: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Your $type Quiz High-Score")
        Spacer(modifier = Modifier.height(32.dp))
        CircleScore(score)
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun CircleScore(score: String) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Yellow, shape = CircleShape)
            .layout() { measurable, constraints ->
                // Measure the composable
                val placeable = measurable.measure(constraints)

                //get the current max dimension to assign width=height
                val currentHeight = placeable.height
                var heightCircle = currentHeight
                if (placeable.width > heightCircle)
                    heightCircle = placeable.width

                //assign the dimension and the center position
                layout(heightCircle, heightCircle) {
                    // Where the composable gets placed
                    placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                }
            }) {

        Text(
            text = score,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .padding(32.dp)
                .defaultMinSize(32.dp) //Use a min size for short text.
        )
    }
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun HighScoreScreenPreview() {
    GeoQuizTheme {
        HighScoreScreen()
    }
}
package com.example.geoquiz

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarContent() {
    val expanded = remember { mutableStateOf(false)}
    val context = LocalContext.current
    TopAppBar(
        elevation= 0.dp,
        title = { },
        backgroundColor = Color.Transparent,

        navigationIcon = {},

        actions = {
            Box(
                Modifier
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = {
                    expanded.value = true
                }) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "Localized description"
                    )
                }

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                ) {
                    DropdownMenuItem(onClick = {
                        val intent = Intent(context, LearningModeActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Text("Learning Mode")
                    }
                    DropdownMenuItem(onClick = {
                        val intent = Intent(context, QuizSelectionActivity::class.java)
                        context.startActivity(intent) }) {
                        Text("Quiz Mode")
                    }
                    DropdownMenuItem(onClick = { val intent = Intent(context, WorldLeaderboardActivity::class.java)
                        context.startActivity(intent) }) {
                        Text("Leaderboard")
                    }
                    DropdownMenuItem(onClick = { val intent = Intent(context, HighScoreActivity::class.java)
                        context.startActivity(intent) }) {
                        Text("High-Score")
                    }
                }
            }
        }
    )
}
package com.example.mentoriaproj1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mentoriaproj1.ui.characters.list.CharactersListActivity
import com.example.mentoriaproj1.ui.theme.MentoriaProj1Theme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    MentoriaProj1Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.rickmortylogo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .height(110.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "O universo de Rick and Morty é composto por uma série de criaturas  bizarras e ambientes inóspitos.",
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .height(IntrinsicSize.Min),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Este app foi feito para que você possa conhecer tudo isso de uma maneira simples e divertida.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .height(IntrinsicSize.Min),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { goToCharactersList(context) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text(text = "Personagens", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { goToLocationsListView(context) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text(text = "Lugares", color = Color.Black)
            }
        }
    }

}

fun goToCharactersList(context: Context) {
    val intent = Intent(context, CharactersListActivity::class.java)
    context.startActivity(intent)
}

fun goToLocationsListView(context: Context) {
    val intent = Intent(context, PlacesActivity::class.java)
    context.startActivity(intent)
}
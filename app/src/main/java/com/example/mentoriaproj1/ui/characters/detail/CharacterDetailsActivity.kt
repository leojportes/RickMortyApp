package com.example.mentoriaproj1.ui.characters.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentoriaproj1.R
import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.ui.characters.CharacterViewState
import com.example.mentoriaproj1.ui.characters.NavigationKeys
import com.example.mentoriaproj1.ui.theme.MentoriaProj1Theme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

@SuppressLint("StateFlowValueCalledInComposition")
class CharacterDetailsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            val character: CharacterResponse? = this.intent.extras?.getSerializable(NavigationKeys.character) as? CharacterResponse

            MentoriaProj1Theme() {
                SetupView(
                    character = character?.name?: "",
                    race = character?.species?: "",
                    status = character?.status?: "",
                    origem = character?.origin?.name?: "",
                    urlImage = character?.image?: ""
                )
            }
        }
    }

}

@Composable
private fun SetupView(
    character: String,
    race: String,
    status: String,
    origem: String,
    urlImage: String
) {
    Column {
        val imageUrl: String = urlImage
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(urlImage)
                .crossfade(1000)
                .build(),
            placeholder = painterResource(id = R.drawable.rickmortylogo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(Modifier.padding(start = 20.dp)) {
            Text(
                text = character,
                fontWeight = FontWeight.Bold,
                fontSize = 27.sp,
            )
            Text(
                text = "Race:",
                fontSize = 14.sp,
            )
            Text(
                text = race,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            Text(
                text = "Status:",
                fontSize = 14.sp,
            )
            Text(
                text = status,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            Text(
                text = "Origem:",
                fontSize = 14.sp,
            )
            Text(
                text = origem,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
        }
    }
}
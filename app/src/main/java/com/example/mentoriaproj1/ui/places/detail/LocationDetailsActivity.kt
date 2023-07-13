package com.example.mentoriaproj1.ui.places.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mentoriaproj1.R
import com.example.mentoriaproj1.domain.models.LocationResponse
import com.example.mentoriaproj1.ui.characters.NavigationKeys

import com.example.mentoriaproj1.ui.theme.MentoriaProj1Theme

@SuppressLint("StateFlowValueCalledInComposition")
class LocationDetailsActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val location: LocationResponse? = this.intent.extras?.getSerializable(NavigationKeys.location) as? LocationResponse

            MentoriaProj1Theme() {
                SetupView(
                    name = location?.name?: "",
                    type = location?.type?: "",
                    dimension = location?.dimension?: ""
                )
            }
        }
    }

}

@Composable
private fun SetupView(
    name: String,
    type: String,
    dimension: String
) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Column(Modifier.padding(start = 20.dp)) {
            Text(
                text = "Name:",
                fontSize = 14.sp,
            )
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 27.sp,
            )
            Text(
                text = "Type:",
                fontSize = 14.sp,
            )
            Text(
                text = type,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            Text(
                text = "Dimension:",
                fontSize = 14.sp,
            )
            Text(
                text = dimension,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
        }
    }
}
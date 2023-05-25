package com.example.mentoriaproj1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.models.LocationResponse
import com.example.mentoriaproj1.ui.characters.CharactersViewModel
import com.example.mentoriaproj1.ui.places.PlacesViewModel
import com.example.mentoriaproj1.ui.theme.MentoriaProj1Theme

@SuppressLint("StateFlowValueCalledInComposition")
class PlacesActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)

    val viewModel: PlacesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val locationComposable = viewModel.locations.collectAsState().value
            LaunchedEffect(Unit) {
                viewModel.retrieveCharacters()
            }
            SetupView(locationComposable)
        }
    }

}

@Composable
private fun SetupView(characterComposable: List<LocationResponse>) {
    MentoriaProj1Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                characterComposable.forEach { location ->
                    PlacesModel(location = location)
                }
            }
        }
    }
}

@Composable fun PlacesModel(location: LocationResponse) {
    RowWithIconAndTwoTexts(
        title = location.name,
        subtitle = location.type,
        icon = Icons.Default.ArrowForward,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun RowWithIconAndTwoTexts(
    title: String,
    subtitle: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically),
        )
    }
    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        startIndent = 20.dp
    )
}
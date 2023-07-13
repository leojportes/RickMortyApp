package com.example.mentoriaproj1

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.models.LocationResponse
import com.example.mentoriaproj1.ui.places.LocationViewState
import com.example.mentoriaproj1.ui.places.PlacesViewModel
import com.example.mentoriaproj1.ui.places.detail.LocationDetailsActivity
import com.example.mentoriaproj1.ui.characters.NavigationKeys
import com.example.mentoriaproj1.ui.theme.MentoriaProj1Theme

@SuppressLint("StateFlowValueCalledInComposition")
internal class PlacesActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)

    val viewModel: PlacesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val locationComposable = viewModel.locationsViewState.collectAsState().value
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                viewModel.retrieveCharacters()
            }
            SetupView(
                locationComposable,
                onBackPressed = { this.finish() }
            ) { location: LocationResponse ->
                navigateToDetails(location = location, context)
            }
        }
    }

    private fun navigateToDetails(location: LocationResponse, context: Context) {
        val intent = Intent(context, LocationDetailsActivity::class.java)
        intent.putExtra(NavigationKeys.location, location)
        context.startActivity(intent)
    }

}

@Composable private fun LoadingView() {
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            Modifier.size(24.dp)
        )
    }
}

@Composable
private fun SetupView(
    state: LocationViewState,
    onBackPressed: () -> Unit,
    navigateToDetails: (LocationResponse) -> Unit
) {
    MentoriaProj1Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (state.isLoading) {
                LoadingView()
            } else {
                Content(onBackPressed, state.locations, navigateToDetails)
            }

        }
    }
}

@Composable
private fun Content(
    onBackPressed: () -> Unit,
    locationComposable: List<LocationResponse>,
    navigateToDetails: (LocationResponse) -> Unit
) {
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
            SmallTopAppBar(
                title = { Text("Lugares") },
                navigationIcon = { NavigationIcon(action = { onBackPressed() }) },
            )
            locationComposable.forEach { location ->
                PlacesModel(location = location, navigateToDetails)
            }
        }
    }
}

@Composable
private fun NavigationIcon(action: () -> Unit) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null,
        tint = Color.Black,
        modifier = Modifier
            .size(24.dp)
            .clickable { action() }
    )
}
@Composable private fun PlacesLoadingView() {
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            Modifier.size(24.dp)
        )
    }
}

@Composable fun PlacesModel(
    location: LocationResponse,
    navigateToDetails: (LocationResponse) -> Unit
) {
    RowWithIconAndTwoTexts(
        title = location.name,
        subtitle = location.type,
        icon = Icons.Default.ArrowForward,
        modifier = Modifier
            .padding(16.dp)
            .clickable { navigateToDetails(location) }
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
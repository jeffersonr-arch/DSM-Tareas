package com.example.thirtydays

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydays.model.Workout
import com.example.thirtydays.model.WorkoutRepository
import com.example.thirtydays.ui.theme.ThirtyDaysTheme

@Composable
fun WorkoutList(
    workouts: List<Workout>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(workouts) { workout ->
            WorkoutCard(
                workout = workout,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun WorkoutCard(
    workout: Workout,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.day_label, workout.day),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(workout.titleRes),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Image(
                painter = painterResource(workout.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            if (expanded) {
                Text(
                    text = stringResource(workout.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
                )
            }
            TextButton(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = if (expanded) stringResource(R.string.see_less)
                    else stringResource(R.string.see_more)
                )
            }
        }
    }
}

@Preview("Claro")
@Preview("Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WorkoutCardPreview() {
    ThirtyDaysTheme {
        WorkoutCard(workout = WorkoutRepository.workouts[0])
    }
}
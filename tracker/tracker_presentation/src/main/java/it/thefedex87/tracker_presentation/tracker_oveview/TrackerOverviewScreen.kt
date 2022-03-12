package it.thefedex87.tracker_presentation.tracker_oveview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import it.thefedex87.core.util.UiEvent
import it.thefedex87.core_ui.LocalSpacing
import it.thefedex87.tracker_presentation.tracker_oveview.components.DaySelector
import it.thefedex87.tracker_presentation.tracker_oveview.components.ExpandableMeal
import it.thefedex87.tracker_presentation.tracker_oveview.components.NutrientsHeader

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                date = state.date,
                onPreviousDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClicked)
                },
                onNextDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextDayClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                },
                content = {

                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
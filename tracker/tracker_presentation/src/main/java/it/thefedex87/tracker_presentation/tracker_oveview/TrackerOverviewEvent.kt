package it.thefedex87.tracker_presentation.tracker_oveview

import it.thefedex87.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    object OnNextDayClicked: TrackerOverviewEvent()
    object OnPreviousDayClicked: TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): TrackerOverviewEvent()
    data class OnAddFoodClick(val meal: Meal): TrackerOverviewEvent()
}
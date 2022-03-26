package it.thefedex87.tracker_presentation.search

import it.thefedex87.tracker_domain.model.MealType
import it.thefedex87.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
    data class OnQueryChanged(val query: String) : SearchEvent()
    object OnSearch : SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
    data class OnAmountForFoodChanged(val food: TrackableFood, val amount: String) : SearchEvent()
    data class OnTrackFoodClick(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean) : SearchEvent()
}
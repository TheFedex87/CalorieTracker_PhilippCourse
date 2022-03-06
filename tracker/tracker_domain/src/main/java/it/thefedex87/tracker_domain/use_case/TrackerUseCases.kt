package it.thefedex87.tracker_domain.use_case

data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getFoodsForDate: GetFoodsForDate,
    val deleteFood: DeleteFood,
    val calculateMealNutrients: CalculateMealNutrients
)
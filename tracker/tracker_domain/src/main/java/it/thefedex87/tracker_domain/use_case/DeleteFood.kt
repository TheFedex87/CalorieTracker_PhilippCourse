package it.thefedex87.tracker_domain.use_case

import it.thefedex87.tracker_domain.model.TrackableFood
import it.thefedex87.tracker_domain.model.TrackedFood
import it.thefedex87.tracker_domain.repository.TrackerRepository

class DeleteFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        trackedFood: TrackedFood
    ) {
        repository.deleteTrackedFood(trackedFood)
    }
}
package it.thefedex87.tracker_domain.use_case

import it.thefedex87.tracker_domain.model.TrackableFood
import it.thefedex87.tracker_domain.model.TrackedFood
import it.thefedex87.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {
    operator fun invoke(
        date: LocalDate
    ): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}
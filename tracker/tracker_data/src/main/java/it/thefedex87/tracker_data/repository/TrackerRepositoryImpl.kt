package it.thefedex87.tracker_data.repository

import it.thefedex87.tracker_data.local.TrackerDao
import it.thefedex87.tracker_data.mapper.toTrackableFood
import it.thefedex87.tracker_data.mapper.toTrackedFood
import it.thefedex87.tracker_data.mapper.toTrackedFoodEntity
import it.thefedex87.tracker_data.remote.OpenFoodApi
import it.thefedex87.tracker_domain.model.TrackableFood
import it.thefedex87.tracker_domain.model.TrackedFood
import it.thefedex87.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products.mapNotNull { it.toTrackableFood() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map {
                it.toTrackedFood()
            }
        }
    }
}
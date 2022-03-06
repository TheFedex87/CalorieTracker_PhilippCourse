package it.thefedex87.tracker_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import it.thefedex87.core.domain.preferences.Preferences
import it.thefedex87.tracker_domain.repository.TrackerRepository
import it.thefedex87.tracker_domain.use_case.*

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            deleteFood = DeleteFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences),
        )
    }
}
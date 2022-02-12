package it.thefedex87.onboarding_domain.use_case

import it.thefedex87.core.util.UiText
import it.thefedex87.core.R

class ValidateNutrients {

    operator fun invoke(
        carbsRatioText: String,
        proteinsRatioText: String,
        fatsRatioText: String,
    ): Result {
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinsRatio = proteinsRatioText.toIntOrNull()
        val fatsRatio = fatsRatioText.toIntOrNull()
        if (carbsRatio == null || proteinsRatio == null || fatsRatio == null) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_invalid_values)
            )
        }
        if (carbsRatio + proteinsRatio + fatsRatio != 100) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }

        return Result.Success(
            carbsRatio / 100f,
            proteinsRatio / 100f,
            fatsRatio / 100f
        )
    }

    sealed class Result {
        data class Success(
            val carbsRatio: Float,
            val proteinsRatio: Float,
            val fatsRatio: Float,
        ) : Result()

        data class Error(val message: UiText) : Result()
    }
}
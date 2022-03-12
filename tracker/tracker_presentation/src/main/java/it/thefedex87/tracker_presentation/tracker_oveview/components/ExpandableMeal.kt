package it.thefedex87.tracker_presentation.tracker_oveview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import it.thefedex87.core_ui.LocalSpacing
import it.thefedex87.core.R
import it.thefedex87.tracker_presentation.components.NutrientInfo
import it.thefedex87.tracker_presentation.components.UnitDisplay
import it.thefedex87.tracker_presentation.tracker_oveview.Meal

@Composable
fun ExpandableMeal(
    meal: Meal,
    onToggleClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleClick() }
                .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )
            Spacer(
                modifier = Modifier.width(spacing.spaceMedium)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.h2
                    )
                    Icon(
                        imageVector = if (meal.isExpanded) {
                            Icons.Default.KeyboardArrowUp
                        } else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (meal.isExpanded) {
                            stringResource(id = R.string.collapse)
                        } else stringResource(id = R.string.extend)
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    UnitDisplay(
                        amount = meal.calories,
                        unit = stringResource(id = R.string.kcal),
                        amountTextSize = 30.sp
                    )
                    Row {
                        NutrientInfo(
                            name = stringResource(R.string.carbs),
                            amount = meal.carbs,
                            unit = stringResource(R.string.grams)
                        )
                        Spacer(
                            Modifier.width(spacing.spaceSmall)
                        )
                        NutrientInfo(
                            name = stringResource(R.string.protein),
                            amount = meal.protein,
                            unit = stringResource(R.string.grams)
                        )
                        Spacer(
                            Modifier.width(spacing.spaceSmall)
                        )
                        NutrientInfo(
                            name = stringResource(R.string.fat),
                            amount = meal.fat,
                            unit = stringResource(R.string.grams)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        AnimatedVisibility(visible = meal.isExpanded) {
            content()
        }
    }
}
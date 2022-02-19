package com.gaproductivity.doitall.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object DefaultNavAnimation: DestinationStyle.Animated {

    private const val positiveOffset = 1000
    private const val negativeOffset = -1000

    private const val duration = 400

    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { positiveOffset },
            animationSpec = tween(duration)
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { negativeOffset },
            animationSpec = tween(duration)
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return slideInHorizontally(
            initialOffsetX = { negativeOffset },
            animationSpec = tween(duration)
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return slideOutHorizontally(
            targetOffsetX = { positiveOffset },
            animationSpec = tween(duration)
        )
    }
}

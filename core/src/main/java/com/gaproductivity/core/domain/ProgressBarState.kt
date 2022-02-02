package com.gaproductivity.core.domain

sealed class ProgressBarState{
    
    object Loading: ProgressBarState()
    
    object Idle: ProgressBarState()
}
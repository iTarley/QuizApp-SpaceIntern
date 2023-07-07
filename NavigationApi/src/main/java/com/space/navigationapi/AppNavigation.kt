package com.space.navigationapi

import android.os.Bundle

interface AppNavigation {
    fun navigateFirstFeatureToSecondFeature(arg: Bundle)
    fun navigateUp()
}
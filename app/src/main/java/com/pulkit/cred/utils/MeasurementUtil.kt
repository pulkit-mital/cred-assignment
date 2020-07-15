package com.pulkit.cred.utils

import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.FragmentActivity

object MeasurementUtil {

    fun getHeightOfBottomSheetDialog( activity: FragmentActivity,  view: View): Int{
        val displayMetrics = DisplayMetrics()
        // fill dm with data from current display
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        // loc will hold the coordinates of your view
        val location = IntArray(2)
        // fill loc with the coordinates of your view (loc[0] = x, looc[1] = y)
        view.getLocationOnScreen(location)
        // calculate the distance from the TOP(its y-coordinate) of your view to the bottom of the screen
        return displayMetrics.heightPixels - location[1] - view.height
    }
}
package com.tnovoselec.android.pokemon.ui.util

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


interface ActivityUtils {

    fun addFragmentToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int)

    fun addFragmentWithTagToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int, tag: String)

    fun addFragmentWithTagToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, tag: String, frameId: Int, backStackName: String)

    fun setFragmentWithTagToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, tag: String, frameId: Int, backStackName: String)

    fun setFragmentWithTagToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, tag: String, frameId: Int, backStackName: String, animate: Boolean)

    fun removeFragmentFromActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment)

    fun propagateBackToTopFragment(@NonNull fragmentManager: FragmentManager): Boolean

}

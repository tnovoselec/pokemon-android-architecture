package com.tnovoselec.android.pokemon.ui.util

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.tnovoselec.android.pokemon.base.BackPropagatingFragment
import io.reactivex.functions.Function


class ActivityUtilsImpl : ActivityUtils {

    override fun addFragmentToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int) {
        if (!fragment.isAdded) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }

    override fun addFragmentWithTagToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, frameId: Int, @NonNull tag: String) {
        if (!fragment.isAdded) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment, tag)
            transaction.commit()
        }
    }

    override fun addFragmentWithTagToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, tag: String, frameId: Int,
                                              backStackName: String) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment, tag)
        transaction.addToBackStack(backStackName)
        transaction.commit()
    }

    override fun setFragmentWithTagToActivity(fragmentManager: FragmentManager, fragment: Fragment, tag: String, frameId: Int, backStackName: String) {
        setFragmentWithTagToActivity(fragmentManager, fragment, tag, frameId, backStackName, true)
    }


    override fun setFragmentWithTagToActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment, tag: String, frameId: Int,
                                              backStackName: String, animateEntrance: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment, tag)
        transaction.addToBackStack(backStackName)
        transaction.commit()
    }

    override fun removeFragmentFromActivity(@NonNull fragmentManager: FragmentManager, @NonNull fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
    }

    /**
     * @return `true` is back is consumed, `false` otherwise
     */
    override fun propagateBackToTopFragment(@NonNull fragmentManager: FragmentManager): Boolean {
        return callIfPresent(findBackPropagatingFragment(fragmentManager), Function<BackPropagatingFragment, Boolean> { it.onBack() })
    }

    private fun callIfPresent(backPropagatingFragment: BackPropagatingFragment?, action: Function<BackPropagatingFragment, Boolean>): Boolean {
        if (backPropagatingFragment != null) {
            return action.apply(backPropagatingFragment)
        }
        return false
    }

    private fun findBackPropagatingFragment(fragmentManager: FragmentManager): BackPropagatingFragment? {
        val fragments = fragmentManager.fragments
        if (fragments.isEmpty()) {
            return null
        }
        for (i in fragments.indices.reversed()) {
            val fragment = fragments[i]
            if (fragment != null && fragment.isVisible) {
                if (fragment is BackPropagatingFragment) {
                    return fragment
                }
                break
            }
        }
        return null
    }
}

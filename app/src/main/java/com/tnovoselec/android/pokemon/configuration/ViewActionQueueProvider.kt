package com.tnovoselec.android.pokemon.configuration

import com.tnovoselec.android.pokemon.base.BaseView


class ViewActionQueueProvider {

    private val viewActionQueueMap = HashMap<String, ViewActionQueue<out BaseView>>()

    fun queueFor(queueId: String): ViewActionQueue<out BaseView> {
        val viewActionQueue = viewActionQueueMap[queueId]
        if (viewActionQueue != null) {
            return viewActionQueue
        }

        val newQueue = ViewActionQueue<BaseView>()
        viewActionQueueMap.put(queueId, newQueue)
        return newQueue
    }

    fun dispose(queueId: String) {
        viewActionQueueMap.remove(queueId)
    }
}

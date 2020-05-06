package com.davidups.starwars.core.navigation

import com.davidups.starwars.core.functional.DialogCallback

interface PopUpDelegator {

    fun showErrorWithRetry(title: String, message: String, positiveText: String,
                           negativeText: String, callback: DialogCallback
    )
}
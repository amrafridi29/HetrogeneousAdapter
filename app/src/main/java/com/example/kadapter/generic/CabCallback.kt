package com.example.kadapter.generic

import android.view.Menu
import android.view.MenuItem

interface CabCallback {
    fun onCreateCab(cab: ContextualActionBar?, menu: Menu?): Boolean

    fun onCabItemClicked(item: MenuItem?): Boolean

    fun onDestroyCab(cab: ContextualActionBar?): Boolean
}
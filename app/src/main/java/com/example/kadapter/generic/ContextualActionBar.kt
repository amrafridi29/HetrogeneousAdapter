package com.example.kadapter.generic

import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode

class ContextualActionBar private constructor(builder : Builder){

    private var mActivity : AppCompatActivity?
    private var mCabCallback : CabCallback?

    @MenuRes
    private  var mMenuResId : Int

    @DrawableRes
    private var mCloseDrawableRes : Int

    private var mTitle : String
    private var mActionMode : ActionMode? = null
    private lateinit var actionModeCallback : ActionMode.Callback
    private var isActive : Boolean = false


    init {
        mActivity = builder.mActivity
        mCabCallback = builder.mCabCallback
        mMenuResId = builder.mMenuResId
        mCloseDrawableRes = builder.mCloseDrawableRes
        mTitle = builder.mTitle
         actionModeCallback = object : ActionMode.Callback{
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return mCabCallback?.onCabItemClicked(item) ?: false
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mActionMode = mode
                mode?.menuInflater?.inflate(mMenuResId , menu)
                mode?.title = mTitle


                return mCabCallback?.onCreateCab(this@ContextualActionBar , menu) ?: false
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mActivity?.supportActionBar?.setHomeAsUpIndicator(mCloseDrawableRes);
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                val isDestroy =  mCabCallback?.onDestroyCab(this@ContextualActionBar) ?: false
                if(isDestroy){
                    finish()
                    isActive  = false
                }
            }
        }

    }


    fun start(){
        if(!isActive) {
            mActivity?.startSupportActionMode(actionModeCallback)
            isActive = true
        }
    }


    fun setTitle(title : String){
        mActionMode?.title = title
    }

    fun finish(){
        mActionMode?.finish()
    }



    class Builder(){
        internal var mActivity : AppCompatActivity? = null
        internal var mCabCallback : CabCallback? = null

        @MenuRes
        internal  var mMenuResId : Int = -1

        @DrawableRes
        internal var mCloseDrawableRes : Int = 0

        internal var mTitle : String = ""
        fun with(activity: AppCompatActivity?) = apply { mActivity =activity }
        fun setMenu(@MenuRes menuRes : Int) = apply { mMenuResId = menuRes }
        fun setCloseDrawable(@DrawableRes drawableRes: Int) = apply { mCloseDrawableRes = drawableRes }
        fun setTitle(title : String) = apply { mTitle = title }
        fun setCabCallback(cabCallback: CabCallback) = apply { mCabCallback = cabCallback }
        fun build() = ContextualActionBar(this)
    }




}
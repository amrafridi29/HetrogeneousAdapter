package com.example.kadapter

import android.content.Context
import android.os.Bundle
import android.transition.Slide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadapter.generic.*
import com.example.kadapter.models.*
import com.example.kadapter.viewholders.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), CabCallback {

    private val data = mutableListOf<Any>()
    private val coupleImages = mutableListOf<HorizontalImage>()
    private val natureImages = mutableListOf<HorizontalImageInner>()
    private var cabAction : ContextualActionBar?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        showData()
    }

    private fun showData() {

        val slideItems = mutableListOf<SlideItem>().apply {
            add(SlideItem(R.drawable.fv5 , "Food 5"))
            add(SlideItem(R.drawable.fv4 , "Food 4"))
            add(SlideItem(R.drawable.fv3 , "Food 3"))
            add(SlideItem(R.drawable.fv2 , "Food 2"))
        }

        data.add(SlideItemList(slideItems = slideItems))

        for(i in 1..6){
            coupleImages.add(HorizontalImage(getCouplesImages(i) , Constants.HORIZONTAL))
        }

        for (i in 1..4){
            natureImages.add(HorizontalImageInner(getNatureImages(i) , Constants.HORIZONTAL))
        }
        data.add(BannerAd(R.drawable.fv5 , title = "Google Play Movies..." ,
            description = "Google LLC" , rating = 4))

        for(i in 1..2){
            data.add(VerticalImage(getFoodImages(i)))
        }

        data.add(BannerAd(R.drawable.fv5 , title = "Google Play Movies..." ,
            description = "Google LLC" , rating = 4))
        data.add(HorizontalImageList(images = coupleImages , title = "Couples"))

        for(i in 3..4){
            data.add(VerticalImage(getFoodImages(i)))
        }

        data.add(BannerAd(R.drawable.fv5 , title = "Google Play Movies..." ,
            description = "Google LLC" , rating = 4))
        data.add(HorizontalImageInnerList(images = natureImages , title = "Nature"))

        for(i in 5..6){
            data.add(VerticalImage(getFoodImages(i)))
        }



         ContextualActionBar.Builder()
            .with(this)
            .setMenu(R.menu.select_menu)
            .setTitle("Selected Items")
            .setCloseDrawable(android.R.drawable.btn_plus)




        var counter =0
        val verticalImageViewBinder =  VerticalImageViewBinder(this){
            cabAction?.start()
            cabAction?.setTitle("Selected items (${++counter})")
        }

        Adapter.Builder()
            .addViewBinder(HorizontalImageListViewBinder())
            .addViewBinder(HorizontalImageInnerListViewBinder())
            .addViewBinder(BannerViewBinder())
            .addViewBinder(SlideItemListViewBinder())
            .addViewBinder(verticalImageViewBinder)
            .submitList(data)
            .setLayoutManager(LinearLayoutManager(this , RecyclerView.VERTICAL , false))
            .into(rv_data)
            .build()
    }

    fun verticalImageClick(model : VerticalImage){
        Toast.makeText(this , "Food Image Click" , Toast.LENGTH_SHORT).show()
    }

    private fun getCouplesImages(number : Int) : Int {
        return when(number){
            1 -> R.drawable.c1
            2 -> R.drawable.c2
            3 -> R.drawable.c3
            4 -> R.drawable.c4
            5 -> R.drawable.c5
            else -> R.drawable.c6
        }
    }

    private fun getFoodImages(number : Int) : Int {
        return when(number){
            1 -> R.drawable.fv1
            2 -> R.drawable.fv2
            3 -> R.drawable.fv3
            4 -> R.drawable.fv4
            5 -> R.drawable.fv5
            else -> R.drawable.c6
        }
    }

    private fun getNatureImages(number : Int) : Int {
        return when(number){
            1 -> R.drawable.v1
            2 -> R.drawable.v2
            3 -> R.drawable.v3
            else -> R.drawable.v4
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateCab(cab: ContextualActionBar?, menu: Menu?): Boolean {
        return true
    }

    override fun onCabItemClicked(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_delete-> {
                Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show()
                cabAction?.finish()
            }
        }
        return true
    }

    override fun onDestroyCab(cab: ContextualActionBar?): Boolean {
        cab?.setTitle("Selected Items")
        return true
    }
}
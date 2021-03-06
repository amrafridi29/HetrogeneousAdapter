package com.example.kadapter

import android.content.Context
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadapter.generic.GenericAdapter
import com.example.kadapter.generic.ItemBinder
import com.example.kadapter.generic.ItemClass
import com.example.kadapter.models.*
import com.example.kadapter.viewholders.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val data = mutableListOf<Any>()
    private val coupleImages = mutableListOf<HorizontalImage>()
    private val natureImages = mutableListOf<HorizontalImageInner>()

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


        for(i in 1..6){
            coupleImages.add(HorizontalImage(getCouplesImages(i) , Constants.HORIZONTAL))
        }

        for (i in 1..4){
            natureImages.add(HorizontalImageInner(getNatureImages(i) , Constants.HORIZONTAL))
        }
        for(i in 1..2){
            data.add(VerticalImage(getFoodImages(i)))
        }
        data.add(HorizontalImageList(images = coupleImages , title = "Couples"))

        for(i in 3..4){
            data.add(VerticalImage(getFoodImages(i)))
        }
        data.add(HorizontalImageInnerList(images = natureImages , title = "Nature"))

        for(i in 5..6){
            data.add(VerticalImage(getFoodImages(i)))
        }


        val horizontalImageListViewBinder = HorizontalImageListViewBinder()
        val horizontalImageInnerListViewBinder = HorizontalImageInnerListViewBinder()
        val verticalImageViewBinder = VerticalImageViewBinder()
        val viewBinders = mutableMapOf<ItemClass , ItemBinder>()
        viewBinders.put(horizontalImageListViewBinder.modelClass ,
            horizontalImageListViewBinder  as ItemBinder)
        viewBinders.put(horizontalImageInnerListViewBinder.modelClass,
            horizontalImageInnerListViewBinder  as ItemBinder)
        viewBinders.put(verticalImageViewBinder.modelClass,
        verticalImageViewBinder as ItemBinder)

        val adapter = GenericAdapter(viewBinders)
        rv_data.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)
        rv_data.adapter = adapter
        adapter.submitList(data)

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
}
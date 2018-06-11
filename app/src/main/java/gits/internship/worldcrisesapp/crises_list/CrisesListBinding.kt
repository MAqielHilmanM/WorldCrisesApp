package gits.internship.worldcrisesapp.crises_list

import android.app.Application
import android.databinding.BindingAdapter
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import gits.internship.worldcrisesapp.R
import gits.internship.worldcrisesapp.WorldCrisesApps
import gits.internship.worldcrisesapp.data.Crises

object CrisesListBinding {
    @BindingAdapter("app:crisesList")
    @JvmStatic
    fun setCrisesList(recyclerView: RecyclerView, crises: List<Crises>) {
        with(recyclerView.adapter as CrisesListAdapter){
            replaceData(crises)
        }
    }

    @BindingAdapter("crisesLevel")
    @JvmStatic
    fun setCrisesLevel(imageView: ImageView, level : String) {
        when(level){
            "Green" ->{
                imageView.setBackgroundResource(R.drawable.gradient_green)
                imageView.setImageResource(R.drawable.gradient_green)
                imageView.background = ContextCompat.getDrawable(imageView.context,R.drawable.gradient_green)
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context,R.drawable.gradient_green))
            }
            "Orange" ->{
                imageView.setBackgroundResource(R.drawable.gradient_orange)
                imageView.setImageResource(R.drawable.gradient_orange)
                imageView.background = ContextCompat.getDrawable(imageView.context,R.drawable.gradient_orange)
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context,R.drawable.gradient_orange))
            }
            "Red" -> {
                imageView.setBackgroundResource(R.drawable.gradient_red)
                imageView.setImageResource(R.drawable.gradient_red)
                imageView.background = ContextCompat.getDrawable(imageView.context,R.drawable.gradient_red)
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context,R.drawable.gradient_red))
            }
            else -> {
                imageView.setBackgroundResource(R.drawable.gradient_green)
                imageView.setImageResource(R.drawable.gradient_green)
                imageView.background = ContextCompat.getDrawable(imageView.context,R.drawable.gradient_green)
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context,R.drawable.gradient_green))
            }
        }

    }
}
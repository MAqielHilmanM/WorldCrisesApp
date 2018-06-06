package gits.internship.worldcrisesapp.utils

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.BindingAdapter
import android.nfc.Tag
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit){
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run{
        action()
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int){
    supportFragmentManager.transact {
        replace(frameId,fragment)
    }
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this,ViewModelFactory.getInstance(application)).get(viewModelClass)

val Context.picasso: Picasso
    get() = Picasso.get()

fun ImageView.load(path: String, request: (RequestCreator) -> RequestCreator) {
    request(context.picasso.load(path)).into(this)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        view.load(url){ requestCreator -> requestCreator.fit().centerCrop()
        }
    }
}
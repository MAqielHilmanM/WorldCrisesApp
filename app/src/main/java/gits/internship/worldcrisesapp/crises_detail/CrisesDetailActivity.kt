package gits.internship.worldcrisesapp.crises_detail

import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import gits.internship.worldcrisesapp.R
import gits.internship.worldcrisesapp.crises_list.CrisesListActivity
import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.utils.obtainViewModel
import gits.internship.worldcrisesapp.utils.replaceFragmentInActivity
import gits.internship.worldcrisesapp.utils.setupActionBar
import kotlinx.android.synthetic.main.crises_detail_activity.*

class CrisesDetailActivity : AppCompatActivity() {
    fun onItemClickListener(url: String) {
        var uri: String = url
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            uri = "http://" + url;
        Toast.makeText(mActivity, uri, Toast.LENGTH_SHORT).show()
        val uris = Uri.parse(uri)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        mActivity.startActivity(intents)
    }

    private lateinit var mActivity : AppCompatActivity
    private lateinit var viewModel: CrisesDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crises_detail_activity)


        val data = intent.extras.get(CrisesListActivity.EXTRA_LIST_SERIALIZE) as Crises

        setupToolbar(data)
        setupCoolbar(data)
        setupViewModel()
        setupFragment(data)
        mActivity = this
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {
            openLinkResource.observe(this@CrisesDetailActivity, Observer { crises ->
                if (crises != null) {
                    onItemClickListener(crises)
                }
            })
        }
    }

    private fun setupFragment(data: Crises) {

        supportFragmentManager.findFragmentById(R.id.frame_detail_content)
        CrisesDetailFragment.newInstance(data).let {
            replaceFragmentInActivity(it, R.id.frame_detail_content)
        }
    }

    private fun setupCoolbar(data: Crises) {
        collapsing_toolbar.title = data.dc_subject!!.get(0)
        collapsing_toolbar.setExpandedTitleColor(Color.WHITE)

        when(data.crisis_alertLevel){
            "Green" -> {
                txt_detail_header.setText("Danger Level : Safe")
                iv_detail_header.setImageResource(R.drawable.gradient_green)
            }
            "Orange" -> {
                iv_detail_header.setImageResource(R.drawable.gradient_orange)
                txt_detail_header.setText("Danger Level : Warning")
            }
            "Red" -> {
                txt_detail_header.setText("Danger Level : Danger")
                iv_detail_header.setImageResource(R.drawable.gradient_red)
            }
        }

    }

    private fun setupToolbar(data: Crises) {
        setupActionBar(R.id.toolbar) {
            setTitle(data.dc_subject!!.get(0))
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    fun obtainViewModel(): CrisesDetailViewModel = obtainViewModel(CrisesDetailViewModel::class.java)

    companion object {
        val EXTRA_DETAIL_ID = "EXTRA_DETAIL_ID"
        val EXTRA_DETAIL_LEVEL = "EXTRA_DETAIL_LEVEL"
        val EXTRA_DETAIL_TYPE = "EXTRA_DETAIL_TYPE"
        val EXTRA_DETAIL_BUNDLE = "EXTRA_DETAIL_BUNDLE"

    }
}

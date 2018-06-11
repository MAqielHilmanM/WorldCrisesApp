package gits.internship.worldcrisesapp.crises_list

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import gits.internship.worldcrisesapp.R
import gits.internship.worldcrisesapp.crises_detail.CrisesDetailActivity
import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.utils.obtainViewModel
import gits.internship.worldcrisesapp.utils.replaceFragmentInActivity
import gits.internship.worldcrisesapp.utils.setupActionBar
import java.io.Serializable

class CrisesListActivity : AppCompatActivity() , CrisesListItemListener {
    override fun OnItemClickListener(crises: Crises) {
        val intent = Intent(this,CrisesDetailActivity::class.java)
        Log.wtf("ID_Crises","id = "+crises._id)
        intent.putExtra(EXTRA_LIST_ID,crises._id)
        intent.putExtra(EXTRA_LIST_LEVEL,crises.crisis_alertLevel)
        intent.putExtra(EXTRA_LIST_TYPE, crises.dc_subject!!.get(0))
        intent.putExtra(EXTRA_LIST_SERIALIZE,crises as Serializable)
        startActivity(intent)
    }

    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: CrisesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crises_list_activity)

        setupToolbar()
        setupFragment()
        setupViewModel()

        mActivity = this
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {
            openDetailCrises.observe(this@CrisesListActivity, Observer { crises ->
                if (crises != null) {
                    OnItemClickListener(crises)
                }
            })
        }
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frame_list_content)
        CrisesListFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frame_list_content)
        }
    }

    private fun setupToolbar() {
        setupActionBar(R.id.toolbar){
            title = ""
        }
    }

    fun obtainViewModel(): CrisesListViewModel = obtainViewModel(CrisesListViewModel::class.java)

    companion object {
        val EXTRA_LIST_ID = "EXTRA_LIST_ID"
        val EXTRA_LIST_LEVEL = "EXTRA_LIST_LEVEL"
        val EXTRA_LIST_TYPE = "EXTRA_LIST_TYPE"
        val EXTRA_LIST_SERIALIZE = "EXTRA_LIST_SERIALIZE"
    }
}

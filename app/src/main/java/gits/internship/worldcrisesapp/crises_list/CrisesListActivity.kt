package gits.internship.worldcrisesapp.crises_list

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import gits.internship.worldcrisesapp.R
import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.utils.obtainViewModel
import gits.internship.worldcrisesapp.utils.replaceFragmentInActivity
import gits.internship.worldcrisesapp.utils.setupActionBar

class CrisesListActivity : AppCompatActivity() , CrisesListItemListener {
    override fun OnItemClickListener(crises: Crises) {
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
            title = "World Crises"
        }
    }

    fun obtainViewModel(): CrisesListViewModel = obtainViewModel(CrisesListViewModel::class.java)
}

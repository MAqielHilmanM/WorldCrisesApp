package gits.internship.worldcrisesapp.crises_list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import gits.internship.worldcrisesapp.R
import gits.internship.worldcrisesapp.databinding.CrisesListFragmentBinding

class CrisesListFragment : Fragment() {

    private lateinit var viewBinding : CrisesListFragmentBinding
    private lateinit var crisesAdapter : CrisesListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = CrisesListFragmentBinding.inflate(inflater,container,false).apply {
            vm = (activity as CrisesListActivity).obtainViewModel()
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCrises()
    }

    private fun setupCrises() {
        val viewModel = viewBinding.vm
        if (viewModel != null) {
            crisesAdapter = CrisesListAdapter(viewModel.crisesLists,viewModel)
            viewBinding.rvFragmentList.adapter = crisesAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()
    }

    companion object {
        fun newInstance() = CrisesListFragment().apply {

        }
    }
}

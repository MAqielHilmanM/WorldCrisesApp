package gits.internship.worldcrisesapp.crises_detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gits.internship.worldcrisesapp.data.Crises

import gits.internship.worldcrisesapp.databinding.CrisesDetailFragmentBinding
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CrisesDetailFragment : Fragment() {
    private lateinit var viewBinding: CrisesDetailFragmentBinding
    private lateinit var crisesAdapter: CrisesDetailAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewBinding = CrisesDetailFragmentBinding.inflate(inflater, container, false).apply {
            vm = (activity as CrisesDetailActivity).obtainViewModel().apply {
                crisesData.set(arguments.getSerializable(CrisesDetailActivity.EXTRA_DETAIL_BUNDLE) as Crises)
            }
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCrisesDetail()

    }

    private fun setupCrisesDetail() {
        val viewModel = viewBinding.vm
        if (viewModel != null && viewModel.crisesData.get()?.crisis_relatedGDACSResources != null) {
            crisesAdapter = CrisesDetailAdapter(viewModel.crisesData.get()?.crisis_relatedGDACSResources?.toList()!!, viewModel)
            viewBinding.rvFragmentDetail.adapter = crisesAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()
    }

    companion object {
        fun newInstance(data : Crises) = CrisesDetailFragment().apply {
            val bundle = Bundle()
            bundle.putSerializable(CrisesDetailActivity.EXTRA_DETAIL_BUNDLE,data as Serializable)
            arguments = bundle
        }
    }
}
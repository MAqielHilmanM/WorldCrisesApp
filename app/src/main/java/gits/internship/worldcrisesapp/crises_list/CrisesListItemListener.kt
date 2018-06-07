package gits.internship.worldcrisesapp.crises_list

import gits.internship.worldcrisesapp.data.Crises

interface CrisesListItemListener {
    fun OnItemClickListener(crises: Crises)
}
package com.formationandroid.exercice_tp_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.formationandroid.exercice_tp_kotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailFragment: Fragment() {

    private var text: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_detail, container, false) as ViewGroup
        val libelle = arguments!!.getString("libelle", "")
        text = root.findViewById(R.id.libelle_fragment)
        text!!.text = libelle
        return root
    }


    // * USE ONLY IF NoteAdapter.isLandscape() RETURN TRUE
    fun updateLandscapeView(activity: AppCompatActivity, libelle: String?) {
        text = activity.containerFragmentDetail.libelle_fragment
        text!!.text = libelle
    }
}
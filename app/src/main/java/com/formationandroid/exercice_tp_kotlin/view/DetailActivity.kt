package com.formationandroid.exercice_tp_kotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.formationandroid.exercice_tp_kotlin.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val fragment = DetailFragment()
        val libelle = intent.getStringExtra("libelle")

        // prepare fragment :
        val bundle = Bundle()
        bundle.putString("libelle", libelle)
        fragment.arguments = bundle

        // transaction :
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerFragmentDetail, fragment, "detailFragment")
        fragmentTransaction.commit()

    }
}
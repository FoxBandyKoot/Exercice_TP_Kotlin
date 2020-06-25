package com.formationandroid.exercice_tp_kotlin.model

import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.formationandroid.exercice_tp_kotlin.R
import com.formationandroid.exercice_tp_kotlin.dao.AppDatabaseHelper
import com.formationandroid.exercice_tp_kotlin.view.DetailFragment
import java.util.*


class NotesAdapter(private var listNotesDTO: MutableList<NoteDTO>?, private var activity: AppCompatActivity?) : RecyclerView.Adapter<NoteViewHolder>() {

    var itemDeleted: Boolean = false
    private val detailFragment = DetailFragment()
    private val bundle = Bundle()
    private var orientation: Int = 0
    private var widthPX: Int = 0
    private var widthDP: Float = 0F
    private var density: Float = 0.0f
    private val displayMetrics = DisplayMetrics()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout. note_items_liste, parent, false)

/*
        itemDeleted = false
*/
        orientation = view.resources.configuration.orientation
        handleOrientation()

        return NoteViewHolder(view, listNotesDTO!! )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (listNotesDTO != null){
            if (listNotesDTO!![position].libelle!!.length > 5) {
                holder.textViewLibelleNote.text = listNotesDTO!![position].libelleTooLong
            } else {
                holder.textViewLibelleNote.text = listNotesDTO!![position].libelle
            }
        }
    }

    override fun getItemCount(): Int {
        return this.listNotesDTO!!.size
    }

    private fun handleOrientation(){

        activity!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        widthPX = displayMetrics.widthPixels
        density = activity!!.applicationContext.resources.displayMetrics.density
        widthDP = widthPX / density

        if (isLandscape()) {
            bundle.putString("libelle", listNotesDTO!![0].libelle)
            detailFragment.arguments = bundle
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.containerFragmentDetail, detailFragment, "detailFragment")
            fragmentTransaction.commit()
        }
    }

    // Call on move
    fun onItemMove(positionDebut: Int, positionFin: Int): Boolean {
        Collections.swap(listNotesDTO, positionDebut, positionFin)
        notifyItemMoved(positionDebut, positionFin)
        return true
    }

    // Call on delete
    fun onItemDismiss(position: Int) {

        val noteDTO: NoteDTO = listNotesDTO!![position]
        AppDatabaseHelper.getDatabase(activity!!).noteDAO()?.delete(noteDTO)
        if (position > -1) {
            listNotesDTO!!.removeAt(position)
            notifyItemRemoved(position)
            if (listNotesDTO!!.size > 0) {
                bundle.putString("libelle", listNotesDTO!![0].libelle)
            } else if (listNotesDTO!!.size == 0) {
                bundle.putString("libelle", "")
            }
            detailFragment.arguments = bundle
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.containerFragmentDetail, detailFragment, "detailFragment")
            fragmentTransaction.commit()
           if (isLandscape()) {

               updateLandscapeView()
           }
/*
            } else {
                itemDeleted = true
            }*/
        }
    }

    // Update landscape
    fun isLandscape(): Boolean {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE && widthDP >= 600) {
            return true
        }
        return false
    }

    // Update landscape
    fun updateLandscapeView(){
        if (listNotesDTO!!.size > 0) {
            detailFragment.updateLandscapeView(activity!!, listNotesDTO!![0].libelle)
        } else if (listNotesDTO!!.size == 0) {
            detailFragment.updateLandscapeView(activity!!, "")
        }
    }



}
package com.formationandroid.exercice_tp_kotlin.view

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formationandroid.exercice_tp_kotlin.R
import com.formationandroid.exercice_tp_kotlin.dao.AppDatabaseHelper
import com.formationandroid.exercice_tp_kotlin.model.ItemTouchHelperCallback
import com.formationandroid.exercice_tp_kotlin.model.NoteDTO
import com.formationandroid.exercice_tp_kotlin.model.NotesAdapter
import com.formationandroid.exercice_tp_kotlin.repository.MainRepository
import com.formationandroid.exercice_tp_kotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

/*
        Modèle MVVM -> Vue
*/
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var listNotes: MutableList<NoteDTO> = mutableListOf()
    private var notesAdapter: NotesAdapter? = null
    private var mainViewModel: MainViewModel? = null

    private var editTextNote: EditText? = null
    private var bouton: Button? = null
    private var recyclerView: RecyclerView? = null

    private var inputManager: InputMethodManager? = null
    private var newConfig = Configuration()
    private var curOrientation = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputManager =  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Interface
        recyclerView = liste_notes
        editTextNote = etNote
        bouton = buttonAddNote

        // Load from Android Database
        val context = applicationContext
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel!!.initialization(MainRepository())
        mainViewModel?.loadData(context, listNotes)

        /*if (listNotes.size == 0){
            init()
        } else {}*/

        // Manage interactions
        notesAdapter = NotesAdapter(listNotes, this)
        recyclerView!!.adapter = notesAdapter
        recyclerView!!.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(notesAdapter!!))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        bouton!!.setOnClickListener(this) //    To create note

        curOrientation = this.resources.configuration.orientation
        newConfig.orientation = curOrientation
        onConfigurationChanged(newConfig)
    }

    override fun onClick(view: View) {
        val noteDTO = NoteDTO()

        // Add note to screen
        noteDTO.libelle = editTextNote!!.text.toString()
        listNotes.add(noteDTO)
        if(noteDTO.libelle!!.length > 5){
            noteDTO.libelleTooLong = noteDTO.libelle!!.replaceRange(5, noteDTO.libelle!!.length, "...")
        }
        notesAdapter?.notifyItemInserted(listNotes.size)

        // Save to Android Database
        AppDatabaseHelper.getDatabase(applicationContext).noteDAO()?.insert(noteDTO)

        // Clean screen
        editTextNote!!.setText("")
        inputManager?.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)

    }

/*    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("itemDeleted", notesAdapter!!.itemDeleted)
        if (listNotes!!.size > 0) {
            outState.putString("firstLibelle", listNotes[0].libelle)
        } else if (listNotes!!.size == 0) {
            outState.putString("firstLibelle", "")
        }

        super.onSaveInstanceState(outState)
    }*/

/*    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // 2 => Configuration.ORIENTATION_LANDSCAPE
        if (newConfig.orientation == 2 && notesAdapter?.itemDeleted == true) {
*//*
            notesAdapter?.updateLandscapeView()
*//*
            notesAdapter?.itemDeleted = false
        }
    }*/

    /* Does not works */
/*
    private fun init(){
        val note1 = NoteDTO()
        note1.libelle = "Bienvenue sur ma superbe application !"
        listNotes.add(note1)
        AppDatabaseHelper.getDatabase(applicationContext).noteDAO()?.insert(note1)

        val note2 = NoteDTO()
        note2.libelle = "Vous pouvez supprimer des notes en swipant vers la droite !"
        listNotes.add(note2)
        AppDatabaseHelper.getDatabase(applicationContext).noteDAO()?.insert(note2)

        val note3 = NoteDTO()
        note3.libelle = "Vous pouvez voir le contenu entier d'une note en appuyant sur celle-ci !"
        listNotes.add(note3)
        AppDatabaseHelper.getDatabase(applicationContext).noteDAO()?.insert(note3)

        notesAdapter = NotesAdapter(listNotes, this)
        recyclerView!!.adapter = notesAdapter
        recyclerView!!.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(notesAdapter!!))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        notesAdapter?.notifyItemInserted(0)

        // Update landscape
        if (notesAdapter!!.isLandscape()){
            fragment.updateLandscapeView(this, listNotes[0].libelle)
        }

    }
*/
}
package com.davidups.starwars.features.people.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.Crashlytics
import com.davidups.starwars.R
import com.davidups.starwars.core.exception.Failure
import com.davidups.starwars.core.extensions.Constants
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.core.extensions.onClick
import com.davidups.starwars.core.functional.DialogCallback
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.features.people.views.viewmodels.GetPeopleViewModel
import com.davidups.starwars.features.people.views.PersonView
import com.davidups.starwars.features.people.views.adapters.PeopleAdapter
import kotlinx.android.synthetic.main.fragment_people.*
import org.jetbrains.anko.bundleOf
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope

class PeopleFragment: BaseFragment() {
    override fun layoutId() = R.layout.fragment_people

    private val peopleAdapter: PeopleAdapter by currentScope.inject()
    private val getPeopleViewModel: GetPeopleViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(getPeopleViewModel){
            observe(person, ::renderPeople)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiView()
        initListeners()
        loadPeople()
    }

    private fun initiView() {
        rvPeople.layoutManager = LinearLayoutManager(activity)
        rvPeople.adapter = peopleAdapter
    }

    private fun initListeners() {
        peopleAdapter.clickListener = {
            val bundle = bundleOf(Constants.Person.PERSON to it)
            view?.findNavController()?.navigate(R.id.action_peopleFragment_to_personFragment, bundle)
            Log.w("Person", it.toString())
        }
    }

    private fun loadPeople() {
        showProgress()
        getPeopleViewModel.getPeople()
    }

    private fun renderPeople(people: List<PersonView>?) {
        peopleAdapter.collection = people.orEmpty()
        renderFailure(0, "")
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when(failure) {
            is Failure.CustomError -> renderFailure(failure.errorCode, failure.errorMessage)
            else -> renderFailure(0, "")
        }
    }

    private fun renderFailure(errorCode: Int, errorMessage: String?) {
        hideProgress()
        showError(errorCode, errorMessage, object : DialogCallback {
            override fun onAccept() {
                loadPeople()
            }

            override fun onDecline() {
            }
        })
    }
}
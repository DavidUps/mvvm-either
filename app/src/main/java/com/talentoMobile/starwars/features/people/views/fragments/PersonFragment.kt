package com.davidups.starwars.features.people.views.fragments

import android.os.Bundle
import android.view.View
import com.davidups.starwars.BuildConfig
import com.davidups.starwars.R
import com.davidups.starwars.core.extensions.Constants
import com.davidups.starwars.core.extensions.loadFromUrl
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.features.people.views.PersonView
import kotlinx.android.synthetic.main.fragment_person_detail.*

class PersonFragment: BaseFragment() {
    override fun layoutId() = R.layout.fragment_person_detail

    private var person: PersonView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments
        if (arguments != null) {
            person = arguments.getSerializable(Constants.Person.PERSON) as PersonView
            initView()
        }
    }

    private fun initView() {

        ivPersonPhoto.loadFromUrl(BuildConfig.IMAGE_MEME_URL)
        tvPersonName.text = person?.name
        tvPersonBirthYear.text = person?.birthYear
        tvPersonGender.text = person?.gender
    }
}
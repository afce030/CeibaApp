package com.ceiba.ceibaapp.view.TestFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ceiba.ceibaapp.model.repositories.MainRepository
import com.ceiba.ceibaapp.view.fragments.UserListFragment
import com.ceiba.ceibaapp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import org.mockito.Mockito

class TestUserListFragment: UserListFragment() {

    private lateinit var mainRepo: MainRepository
    private lateinit var vm: MainViewModel

    override fun injectPars() {

        mainRepo = Mockito.mock(MainRepository::class.java)
        vm = MainViewModel(mainRepo)

    }

}
package com.ceiba.ceibaapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import com.ceiba.ceibaapp.view.adapters.UsersAdapter
import com.ceiba.ceibaapp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
open class UserListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var vm: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        vm = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        recyclerView = view.findViewById(R.id.rv_users)

        adapter = UsersAdapter( findNavController() )

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()

                if (adapter.itemCount == 0){
                    recyclerView.visibility = View.INVISIBLE
                    tv_listEmpty.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    tv_listEmpty.visibility = View.GONE
                }

            }
        })

        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layout

        vm.mediatorLiveData.observe(viewLifecycleOwner, Observer { data ->
            try{

                val users = data as List<UsersEntity>

                adapter.addItems(users)

            } catch (e: java.lang.NullPointerException){ }
        })

        // Get the SearchView and set the searchable configuration
        val searchView = view.findViewById<SearchView>(R.id.search_bar)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm.requestUsers("%$newText%")//Request all users
                return true
            }

        })

        return view
    }

    override fun onStart() {
        super.onStart()

        vm.requestUsers("%%")//Request all users

    }

}

package com.ceiba.ceibaapp.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.model.persistence.relations.PostsAndUser
import com.ceiba.ceibaapp.util.Functions
import com.ceiba.ceibaapp.view.adapters.PostsAdapter
import com.ceiba.ceibaapp.viewmodels.MainViewModel
import java.lang.NullPointerException
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
open class PostsByUserFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var vm: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostsAdapter

    private val argsproofs: PostsByUserFragmentArgs by navArgs()

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
        val view = inflater.inflate(R.layout.fragment_posts_by_user, container, false)
        vm = Functions.createVM(this, viewModelFactory, MainViewModel::class.java)

        recyclerView = view.findViewById(R.id.rv_posts)

        adapter = PostsAdapter( findNavController() )
        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layout

        vm.mediatorLiveData.observe(viewLifecycleOwner, Observer { data ->
            try{

                val posts = (data as List<PostsAndUser>)[0].postsEntities

                adapter.addItems(posts)

            } catch (e: NullPointerException){}
        })

        vm.requestUserAndPosts(argsproofs.userId.toLong())//Request all posts by user

        return view
    }

}

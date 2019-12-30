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

import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.relations.PostsAndUser
import com.ceiba.ceibaapp.viewmodels.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post_viewer.*
import java.lang.NullPointerException
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class PostViewerFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var vm: MainViewModel

    private val argsproofs: PostViewerFragmentArgs by navArgs()

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
        val view = inflater.inflate(R.layout.fragment_post_viewer, container, false)
        vm = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        vm.mediatorLiveData.observe(viewLifecycleOwner, Observer { data ->
            try{

                val post = (data as List<PostsEntity>)[0]

                tv_postTitleViewer.text = post.title
                tv_postContentViewer.text = post.body

            } catch (e: NullPointerException){}
        })

        vm.requestPost(argsproofs.postId)//Request all posts by user

        return view
    }

}

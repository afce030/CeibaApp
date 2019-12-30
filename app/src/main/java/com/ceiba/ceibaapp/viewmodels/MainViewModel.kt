package com.ceiba.ceibaapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import com.ceiba.ceibaapp.model.persistence.relations.PostsAndUser
import com.ceiba.ceibaapp.model.repositories.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    var users: LiveData<List<UsersEntity>> = MutableLiveData()
    var posts: LiveData<List<PostsEntity>> = MutableLiveData()
    var userAndPosts: LiveData<List<PostsAndUser>> = MutableLiveData()

    var mediatorLiveData: MediatorLiveData<List<Any>> = MediatorLiveData()

    init {
        requestUsersInfoAPi()
    }

    fun requestUsersInfoAPi(){
        mainRepository.requestUsersInfo()
    }

    fun requestUsers(pattern: String) {

        mediatorLiveData.removeSource(users)

        users = mainRepository.getAllUsersbyPatternFromRoom(pattern)

        mediatorLiveData.addSource(users){ data ->
            mediatorLiveData.value = data
        }

    }

    fun requestPost(postId: Long) {

        mediatorLiveData.removeSource(posts)

        posts = mainRepository.getPostsById(postId)

        mediatorLiveData.addSource(posts){ data ->
            mediatorLiveData.value = data
        }

    }

    fun requestUserAndPosts(userId: Long) {

        mediatorLiveData.removeSource(userAndPosts)

        userAndPosts = mainRepository.getPostsByUser(userId)
        mainRepository.requestPostsById(userId)

        mediatorLiveData.addSource(userAndPosts){ data ->
            mediatorLiveData.value = data
        }
    }

}
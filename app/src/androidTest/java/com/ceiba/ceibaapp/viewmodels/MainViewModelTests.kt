package com.ceiba.ceibaapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import com.ceiba.ceibaapp.model.persistence.relations.PostsAndUser
import com.ceiba.ceibaapp.model.repositories.MainRepository
import com.ceiba.ceibaapp.utils.UtilitiesTesting.post1
import com.ceiba.ceibaapp.utils.UtilitiesTesting.post2
import com.ceiba.ceibaapp.utils.UtilitiesTesting.post3
import com.ceiba.ceibaapp.utils.UtilitiesTesting.post4
import com.ceiba.ceibaapp.utils.UtilitiesTesting.post5
import com.ceiba.ceibaapp.utils.UtilitiesTesting.user1
import com.ceiba.ceibaapp.utils.UtilitiesTesting.user2
import com.ceiba.ceibaapp.utils.UtilitiesTesting.user3
import com.ceiba.ceibaapp.utils.UtilitiesTesting.user4
import com.ceiba.ceibaapp.utils.awaitValue
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTests {

    private var users = listOf<UsersEntity>()
    private var posts = listOf<PostsEntity>()

    private var postsAndUser = listOf<PostsAndUser>()

    private lateinit var mainRepo: MainRepository
    private lateinit var viewmodel: MainViewModel

    //Probe architecture components synchronously
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupRepo() = runBlocking {

        users = listOf(user1, user2, user3, user4)
        posts = listOf(post1, post2, post3, post4, post5)

        postsAndUser = listOf(PostsAndUser(user1, listOf(post1, post2)))

        mainRepo = Mockito.mock(MainRepository::class.java)
        viewmodel = MainViewModel(mainRepo)

        return@runBlocking
    }

    @Test
    fun assert_that_users_livedata_emits() = runBlocking {

        Mockito.`when`(mainRepo.getAllUsersbyPatternFromRoom(Mockito.anyString())).
            thenReturn(MutableLiveData(users))

        viewmodel.requestUsers("%%")

        //Assert size
        assertEquals(4, viewmodel.users.awaitValue()?.size)

        //Assert Mediator live data
        assertEquals(4, viewmodel.mediatorLiveData.awaitValue()?.size)

        //Assert info
        assertEquals(user1, viewmodel.users.awaitValue()?.get(0))
        assertEquals(user2, viewmodel.users.awaitValue()?.get(1))
        assertEquals(user3, viewmodel.users.awaitValue()?.get(2))
        assertEquals(user4, viewmodel.users.awaitValue()?.get(3))

        return@runBlocking
    }

    @Test
    fun assert_that_posts_livedata_emits() = runBlocking {

        Mockito.`when`(mainRepo.getPostsById(Mockito.anyLong())).
            thenReturn(MutableLiveData(listOf(post1)))

        viewmodel.requestPost(0)

        //Assert size
        assertEquals(1, viewmodel.posts.awaitValue()?.size)
        assertEquals(1, viewmodel.mediatorLiveData.awaitValue()?.size)

        //Assert info
        assertEquals(post1, viewmodel.posts.awaitValue()?.get(0))

        return@runBlocking
    }

    @Test
    fun assert_that_userAndPosts_livedata_emits() = runBlocking {

        Mockito.`when`(mainRepo.getPostsByUser(Mockito.anyLong())).
            thenReturn(MutableLiveData(postsAndUser))

        viewmodel.requestUserAndPosts(0)

        //Assert sizes
        assertEquals(1, viewmodel.userAndPosts.awaitValue()?.size)
        assertEquals(2, viewmodel.userAndPosts.awaitValue()?.get(0)?.postsEntities?.size)

        assertEquals(1, viewmodel.mediatorLiveData.awaitValue()?.size)

        //Assert post content
        assertEquals(post1, viewmodel.userAndPosts.awaitValue()?.get(0)?.postsEntities?.get(0))
        assertEquals(post2, viewmodel.userAndPosts.awaitValue()?.get(0)?.postsEntities?.get(1))

        //Assert user info
        assertEquals(user1, viewmodel.userAndPosts.awaitValue()?.get(0)?.usersEntity)

        return@runBlocking
    }

}
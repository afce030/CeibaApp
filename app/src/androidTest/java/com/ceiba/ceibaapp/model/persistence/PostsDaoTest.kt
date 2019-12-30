package com.ceiba.ceibaapp.model.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.ceiba.ceibaapp.model.persistence.databases.Database
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import com.ceiba.ceibaapp.model.persistence.methods.PostsDao
import com.ceiba.ceibaapp.model.persistence.methods.UsersDao
import com.ceiba.ceibaapp.utils.UtilitiesTesting
import com.ceiba.ceibaapp.utils.awaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class PostsDaoTest {

    private lateinit var database: Database

    private lateinit var usersDao: UsersDao
    private val users = mutableListOf<UsersEntity>()

    private lateinit var postDao: PostsDao
    private val posts = mutableListOf<PostsEntity>()

    //Probe architecture components synchronously
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()

        //Insert users
        usersDao = database.usersDao()
        users.add(UtilitiesTesting.user1)
        users.add(UtilitiesTesting.user2)
        users.add(UtilitiesTesting.user3)
        users.add(UtilitiesTesting.user4)
        usersDao.insertUsers(users[0])
        usersDao.insertUsers(users[1])
        usersDao.insertUsers(users[2])
        usersDao.insertUsers(users[3])


        //Insert posts
        postDao = database.postsDao()
        posts.add(UtilitiesTesting.post1)
        posts.add(UtilitiesTesting.post2)
        posts.add(UtilitiesTesting.post3)
        posts.add(UtilitiesTesting.post4)
        posts.add(UtilitiesTesting.post5)
        postDao.insertPosts(*posts.toTypedArray())

        return@runBlocking
    }

    @Test
    fun assert_that_posts_are_inserted() = runBlocking{

        val posts = postDao.getPosts().awaitValue()
        assertEquals(5, posts?.size)

        return@runBlocking
    }

    @Test
    fun assert_that_posts_are_deleted() = runBlocking{

        var posts = postDao.getPosts().awaitValue()
        assertEquals(5, posts?.size)

        postDao.deletePosts()

        posts = postDao.getPosts().awaitValue()
        assertEquals(0, posts?.size)

        return@runBlocking
    }

    @Test
    fun assert_that_posts_by_user_are_inserted() = runBlocking{

        val user1AndPosts = postDao.getUserAndPosts(userId = 0).awaitValue()//Return users
        val postsUser0 = user1AndPosts?.get(0)?.postsEntities//Return posts for user 1

        assertEquals(1, user1AndPosts?.size)
        assertEquals(2, postsUser0?.size)

        return@runBlocking
    }

    @Test
    fun assert_that_posts_by_id_are_found() = runBlocking{

        val posts = postDao.getPosts().awaitValue()
        assertEquals(5, posts?.size)

        val postsById = postDao.getPostsById(postId = 0).awaitValue()
        assertEquals(1, postsById?.size)

        return@runBlocking
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

}
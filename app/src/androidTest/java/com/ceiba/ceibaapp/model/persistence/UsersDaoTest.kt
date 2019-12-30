package com.ceiba.ceibaapp.model.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.ceiba.ceibaapp.model.persistence.databases.Database
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
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

class UsersDaoTest {

    private lateinit var database: Database
    private lateinit var usersDao: UsersDao

    private val users = mutableListOf<UsersEntity>()

    //Probe architecture components synchronously
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun setupDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()

        usersDao = database.usersDao()

        users.add(UtilitiesTesting.user1)
        users.add(UtilitiesTesting.user2)
        users.add(UtilitiesTesting.user3)
        users.add(UtilitiesTesting.user4)

        usersDao.insertUsers(users[0])
        usersDao.insertUsers(users[1])
        usersDao.insertUsers(users[2])
        usersDao.insertUsers(users[3])

        return@runBlocking
    }

    @Test
    fun assert_that_users_are_inserted() = runBlocking{

        val users = usersDao.getUsers().awaitValue()
        assertEquals(4, users?.size)

        return@runBlocking
    }

    @Test
    fun assert_that_users_are_deleted() = runBlocking{

        var users = usersDao.getUsers().awaitValue()
        assertEquals(4, users?.size)

        usersDao.deleteUsers()

        users = usersDao.getUsers().awaitValue()
        assertEquals(0, users?.size)

        return@runBlocking
    }

    @Test
    fun assert_that_users_are_found_by_pattern() = runBlocking{

        val users = usersDao.getUsersbyPattern("%Homer%").awaitValue()
        assertEquals(1, users?.size)

        return@runBlocking
    }

    @Test
    fun assert_that_users_are_found_by_single_char() = runBlocking{

        val users = usersDao.getUsersbyPattern("%B%").awaitValue()
        assertEquals(2, users?.size)

        return@runBlocking
    }

    @Test
    fun assert_that_all_users_are_found_by_empty_pattern() = runBlocking{

        val users = usersDao.getUsersbyPattern("%%").awaitValue()
        assertEquals(4, users?.size)

        return@runBlocking
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

}
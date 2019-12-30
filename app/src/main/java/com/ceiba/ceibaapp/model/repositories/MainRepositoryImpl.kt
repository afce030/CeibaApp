package com.ceiba.ceibaapp.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ceiba.ceibaapp.model.persistence.databases.Database
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.AddressEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.CompanyEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import com.ceiba.ceibaapp.model.persistence.relations.PostsAndUser
import com.ceiba.ceibaapp.model.retrofit.dtos.users.UsersResponse
import com.ceiba.ceibaapp.model.retrofit.webServices.PostsWS
import com.ceiba.ceibaapp.model.retrofit.webServices.UsersWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val usersWS: UsersWS,
    private val postsWS: PostsWS,
    val database: Database
) : MainRepository {

    override fun requestUsersInfo() {

        val call = usersWS.getUsersFromAPI()

        call.enqueue(object : Callback<List<UsersResponse>>{

            override fun onFailure(call: Call<List<UsersResponse>>, t: Throwable) {
                Log.e("UsersApiError", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<UsersResponse>>,
                response: Response<List<UsersResponse>>
            ) {

                when {
                    response.code() == 200 -> {

                        val users = response.body()
                        val usersEntities = mutableListOf<UsersEntity>()

                        users?.forEach { user ->

                            usersEntities.add( UsersEntity(
                                userId = user.id?.toLong(),
                                website = user.website,
                                addressEntity = AddressEntity(
                                    zipcode = user.address?.zipcode,
                                    lng = user.address?.lng,
                                    lat = user.address?.lat,
                                    suite = user.address?.suite,
                                    city = user.address?.city,
                                    street = user.address?.street
                                ),
                                phone = user.phone,
                                name = user.name,
                                company = CompanyEntity(
                                    companyName = user.company?.name,
                                    bs = user.company?.bs,
                                    catchPhrase = user.company?.catchPhrase
                                ),
                                email = user.email,
                                username = user.username)
                            )

                        }

                        Log.d("usersAPI", response.body().toString())
                        insertUsersRoom(usersEntities)

                    }

                    else -> {
                        Log.e("UsersApiError", response.raw().toString())
                    }

                }

            }

        })

    }

    override fun requestPostsById(userId: Long) {

        val call = postsWS.getPostsFromAPI()

        call.enqueue(object : Callback<List<PostsEntity>>{

            override fun onFailure(call: Call<List<PostsEntity>>, t: Throwable) {
                Log.e("PostsApiError", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<PostsEntity>>,
                response: Response<List<PostsEntity>>
            ) {

                Log.d("PostsApi", response.code().toString())

                when {
                    response.code() == 200 -> {

                        val posts = response.body()
                        val postsEntities = mutableListOf<PostsEntity>()

                        posts?.forEach { post ->

                            postsEntities.add(
                                PostsEntity(
                                    postId = post.postId,
                                    userId = post.userId,
                                    title = post.title,
                                    body = post.body
                                )
                            )

                        }

                        insertPostsRoom(postsEntities)

                    }

                    else -> {
                        Log.e("PostsApiError", response.raw().toString())
                    }
                }

            }

        })

    }

    override fun insertUsersRoom(users: List<UsersEntity>) {
        CoroutineScope(IO).launch {
            database.usersDao().insertUsers(*users.toTypedArray())
        }
    }

    override fun insertPostsRoom(posts: List<PostsEntity>) {
        CoroutineScope(IO).launch {
            database.postsDao().insertPosts(*posts.toTypedArray())
        }
    }

    override fun deleteAllUsers() {
        CoroutineScope(IO).launch {
            database.usersDao().deleteUsers()
        }
    }

    override fun deleteAllPosts() {
        CoroutineScope(IO).launch {
            database.postsDao().deletePosts()
        }
    }

    override fun getAllUsersFromRoom(): LiveData<List<UsersEntity>> = database.usersDao().getUsers()

    override fun getAllUsersbyPatternFromRoom(pattern: String) =
        database.usersDao().getUsersbyPattern(pattern)

    override fun getPostsByUser(userId: Long): LiveData<List<PostsAndUser>> =
        database.postsDao().getUserAndPosts(userId)

    override fun getPostsById(postId: Long): LiveData<List<PostsEntity>> =
        database.postsDao().getPostsById(postId)
}
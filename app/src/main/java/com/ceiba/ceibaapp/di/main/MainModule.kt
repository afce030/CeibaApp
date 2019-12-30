package com.ceiba.ceibaapp.di.main

import androidx.room.RoomDatabase
import com.ceiba.ceibaapp.model.persistence.databases.Database
import com.ceiba.ceibaapp.model.repositories.MainRepository
import com.ceiba.ceibaapp.model.repositories.MainRepositoryImpl
import com.ceiba.ceibaapp.model.retrofit.webServices.PostsWS
import com.ceiba.ceibaapp.model.retrofit.webServices.UsersWS
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule{

    @MainScope
    @Provides
    fun providesPostsWS(retrofit: Retrofit): PostsWS {
        return retrofit.create(PostsWS::class.java)
    }

    @MainScope
    @Provides
    fun providesUsersWS(retrofit: Retrofit): UsersWS {
        return retrofit.create(UsersWS::class.java)
    }

    @MainScope
    @Provides
    fun providesMainRepository(usersWS: UsersWS, postsWS: PostsWS, database: Database): MainRepository {
        return MainRepositoryImpl(usersWS, postsWS, database)
    }

}
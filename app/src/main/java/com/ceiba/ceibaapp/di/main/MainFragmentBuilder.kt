package com.ceiba.ceibaapp.di.main

import com.ceiba.ceibaapp.view.fragments.PostViewerFragment
import com.ceiba.ceibaapp.view.fragments.PostsByUserFragment
import com.ceiba.ceibaapp.view.fragments.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilder{

    @ContributesAndroidInjector
    abstract fun providesUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun providesPostsByUserFragment(): PostsByUserFragment

    @ContributesAndroidInjector
    abstract fun providesPostViewerFragment(): PostViewerFragment

}

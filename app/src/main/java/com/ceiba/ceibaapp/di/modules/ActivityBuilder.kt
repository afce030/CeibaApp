package com.ceiba.ceibaapp.di.modules

import com.ceiba.ceibaapp.view.activities.MainActivity
import com.ceiba.ceibaapp.di.main.MainFragmentBuilder
import com.ceiba.ceibaapp.di.main.MainModule
import com.ceiba.ceibaapp.di.main.MainScope
import com.ceiba.ceibaapp.di.main.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainFragmentBuilder::class, MainViewModelModule::class])
    abstract fun bindsMainActivity(): MainActivity

}
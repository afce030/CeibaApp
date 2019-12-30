package com.ceiba.ceibaapp.di.main

import androidx.lifecycle.ViewModel
import com.ceiba.ceibaapp.di.factory.viewModelFactory.ViewModelKey
import com.ceiba.ceibaapp.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

}
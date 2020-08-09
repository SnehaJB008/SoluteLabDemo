package com.demo.solutelabstest.koin

import com.demo.solutelabstest.network.RetrofitClientInstance
import com.demo.solutelabstest.ui.MainViewModel
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {
    single { MainViewModel(get()) }
    bean { RetrofitClientInstance().getRestClient()!! }
}

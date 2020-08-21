package com.cursoandroid.dagger.dagger_android.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImp @Inject constructor(private val remoteLoginDataSource: RemoteLoginDataSource) :
    LoginRepository {
    override fun doLogin(): Boolean = remoteLoginDataSource.doLoginRemote()
}
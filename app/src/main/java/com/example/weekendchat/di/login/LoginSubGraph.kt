package com.example.weekendchat.di.login

import com.example.weekendchat.di.scope.ActivityScope
import com.example.weekendchat.presentation.login.BaseAuthActivity
import dagger.Module
import dagger.Subcomponent

//Rules of scoping
//1. A type with a scope annotation can only be used by components with the same scope annotation
//2. A component with a scope annotation can only provide types with the same scope annotation
//3. A subcomponent cannot use a scope annotation used by it's parent
@ActivityScope
@Subcomponent(modules = [LoginRepoModule::class])
interface LoginSubGraph {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginSubGraph
    }

    fun inject(mainActivity: BaseAuthActivity)

    @Module(subcomponents = [LoginSubGraph::class])
    class LoginSubGraphModule {}
}


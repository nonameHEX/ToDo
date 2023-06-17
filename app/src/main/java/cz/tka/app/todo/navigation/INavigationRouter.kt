package cz.tka.app.todo.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun getNavController(): NavController
    fun returnBack()
    fun navigateToAddEditTask(id: Long?)
}
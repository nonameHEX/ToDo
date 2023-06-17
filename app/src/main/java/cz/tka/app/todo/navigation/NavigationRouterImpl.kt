package cz.tka.app.todo.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(private val navController: NavController): INavigationRouter {
    override fun getNavController(): NavController = navController

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToAddEditTask(id: Long?) {
        navController.navigate(Destination.AddEditTaskScreen.route + "/" + id)
    }

}
package cz.tka.app.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.tka.app.todo.ui.screens.add_edit_task.AddEditTaskScreen
import cz.tka.app.todo.ui.screens.task_list.TaskListScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember {
        NavigationRouterImpl(navController = navController)
    },
    startDestination: String
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        // Task List Screen
        composable(
            route = Destination.TaskListScreen.route
        ){
            TaskListScreen(
                navigation = navigation
            )
        }

        // Add Edit Task Screen
        composable(
            route = Destination.AddEditTaskScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ){
            val id = it.arguments?.getLong("id")
            AddEditTaskScreen(
                navigation = navigation,
                id = if (id != -1L) id else null
            )
        }
    }
}
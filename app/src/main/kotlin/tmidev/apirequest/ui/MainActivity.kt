package tmidev.apirequest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import tmidev.apirequest.core.common.util.Constants.ALBUM_ID_KEY
import tmidev.apirequest.core.common.util.Constants.USER_ID_KEY
import tmidev.apirequest.core.design.component.appWindowInsets
import tmidev.apirequest.core.design.theme.AppTheme
import tmidev.apirequest.feature.albums.AlbumsScreen
import tmidev.apirequest.feature.photos.PhotosScreen
import tmidev.apirequest.feature.posts.PostsScreen
import tmidev.apirequest.feature.userprofile.UserProfileScreen
import tmidev.apirequest.feature.users.UsersScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val windowInsets = appWindowInsets()
            val navController = rememberNavController()

            AppTheme {
                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = "users_screen"
                ) {
                    composable(route = "users_screen") {
                        UsersScreen(
                            modifier = Modifier.fillMaxSize(),
                            windowInsets = windowInsets,
                            onNavigateBack = { moveTaskToBack(true) },
                            onNavigateToUserProfile = { userId ->
                                navController.navigate(
                                    route = "user_profile_screen?$USER_ID_KEY=$userId"
                                ) { launchSingleTop = true }
                            }
                        )
                    }

                    composable(
                        route = "user_profile_screen?$USER_ID_KEY={$USER_ID_KEY}",
                        arguments = listOf(
                            navArgument(name = USER_ID_KEY) { type = NavType.IntType }
                        )
                    ) {
                        UserProfileScreen(
                            modifier = Modifier.fillMaxSize(),
                            windowInsets = windowInsets,
                            onNavigateBack = { navController.popBackStack() },
                            onNavigateToPosts = { userId ->
                                navController.navigate(route = "posts_screen?$USER_ID_KEY=$userId") {
                                    launchSingleTop = true
                                }
                            },
                            onNavigateToAlbums = { userId ->
                                navController.navigate(route = "albums_screen?$USER_ID_KEY=$userId") {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable(
                        route = "posts_screen?$USER_ID_KEY={$USER_ID_KEY}",
                        arguments = listOf(
                            navArgument(name = USER_ID_KEY) { type = NavType.IntType }
                        )
                    ) {
                        PostsScreen(
                            modifier = Modifier.fillMaxSize(),
                            windowInsets = windowInsets,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }

                    composable(
                        route = "albums_screen?$USER_ID_KEY={$USER_ID_KEY}",
                        arguments = listOf(
                            navArgument(name = USER_ID_KEY) { type = NavType.IntType }
                        )
                    ) {
                        AlbumsScreen(
                            modifier = Modifier.fillMaxSize(),
                            windowInsets = windowInsets,
                            onNavigateBack = { navController.popBackStack() },
                            onNavigateToPhotos = { albumId ->
                                navController.navigate(
                                    route = "photos_screen?$ALBUM_ID_KEY=$albumId"
                                ) { launchSingleTop = true }
                            }
                        )
                    }

                    composable(
                        route = "photos_screen?$ALBUM_ID_KEY={$ALBUM_ID_KEY}",
                        arguments = listOf(
                            navArgument(name = ALBUM_ID_KEY) { type = NavType.IntType }
                        )
                    ) {
                        PhotosScreen(
                            modifier = Modifier.fillMaxSize(),
                            windowInsets = windowInsets,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
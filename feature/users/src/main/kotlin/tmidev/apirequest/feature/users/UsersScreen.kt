package tmidev.apirequest.feature.users

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tmidev.apirequest.core.design.component.ErrorState
import tmidev.apirequest.core.design.component.LoadingState
import tmidev.apirequest.core.design.util.AppIcons
import tmidev.apirequest.core.model.User

/**
 * Compose the Users Screen.
 *
 * @param modifier the [Modifier] to be applied on top container of this screen.
 * @param windowInsets the[WindowInsets] to be applied on top container of this screen.
 * @param onNavigateBack callback to navigate back from this screen.
 * @param onNavigateToUserProfile callback with user id to navigate to user profile screen.
 * @param viewModel the [UsersViewModel]. Default is provided by [hiltViewModel].
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun UsersScreen(
    modifier: Modifier,
    windowInsets: WindowInsets,
    onNavigateBack: () -> Unit,
    onNavigateToUserProfile: (Int) -> Unit,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val scrollBehavior = enterAlwaysScrollBehavior()

    BackHandler(onBack = onNavigateBack)

    Scaffold(
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.users)) },
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = windowInsets
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
            targetState = screenState,
            label = "users animated content"
        ) { state ->
            when (state) {
                is UsersScreenState.Loading -> LoadingState(
                    modifier = Modifier.fillMaxSize()
                )
                is UsersScreenState.Success -> SuccessState(
                    modifier = Modifier.fillMaxSize(),
                    users = state.users,
                    onUserClick = { userId ->
                        onNavigateToUserProfile(userId)
                    }
                )
                is UsersScreenState.Error -> ErrorState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 16.dp),
                    message = stringResource(id = state.stringRes),
                    onRetry = viewModel::reload
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SuccessState(
    modifier: Modifier,
    users: List<User>,
    onUserClick: (Int) -> Unit
) = LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(all = 16.dp),
    verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    items(items = users, key = { it.id }) { user ->
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onUserClick(user.id) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(weight = 1F),
                    text = user.name
                )

                Icon(imageVector = AppIcons.NavigateNext, contentDescription = null)
            }
        }
    }
}
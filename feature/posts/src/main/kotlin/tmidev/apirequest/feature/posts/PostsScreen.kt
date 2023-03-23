package tmidev.apirequest.feature.posts

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tmidev.apirequest.core.design.component.ErrorState
import tmidev.apirequest.core.design.component.LoadingState
import tmidev.apirequest.core.design.util.AppIcons
import tmidev.apirequest.core.model.Post
import tmidev.apirequest.core.design.R as DesignR

/**
 * Compose the Posts Screen.
 *
 * @param modifier the [Modifier] to be applied on top container of this screen.
 * @param windowInsets the[WindowInsets] to be applied on top container of this screen.
 * @param onNavigateBack callback to navigate back from this screen.
 * @param viewModel the [PostsViewModel]. Default is provided by [hiltViewModel].
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun PostsScreen(
    modifier: Modifier,
    windowInsets: WindowInsets,
    onNavigateBack: () -> Unit,
    viewModel: PostsViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val scrollBehavior = enterAlwaysScrollBehavior()

    BackHandler(onBack = onNavigateBack)

    Scaffold(
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.posts)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = AppIcons.NavigateBefore,
                            contentDescription = stringResource(id = DesignR.string.back)
                        )
                    }
                },
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
            label = "posts animated content"
        ) { state ->
            when (state) {
                is PostsScreenState.Loading -> LoadingState(
                    modifier = Modifier.fillMaxSize()
                )
                is PostsScreenState.Success -> SuccessState(
                    modifier = Modifier.fillMaxSize(),
                    posts = state.posts
                )
                is PostsScreenState.Error -> ErrorState(
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

@Composable
internal fun SuccessState(
    modifier: Modifier,
    posts: List<Post>
) = LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(all = 16.dp),
    verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    items(items = posts, key = { it.id }) { post ->
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(space = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = post.title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = post.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
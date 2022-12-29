package tmidev.apirequest.feature.albums

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tmidev.apirequest.core.design.component.ErrorState
import tmidev.apirequest.core.design.component.LoadingState
import tmidev.apirequest.core.design.util.AppIcons
import tmidev.apirequest.core.model.Album
import tmidev.apirequest.core.design.R as DesignR

/**
 * Compose the Albums Screen.
 *
 * @param modifier the [Modifier] to be applied on top container of this screen.
 * @param windowInsets the[WindowInsets] to be applied on top container of this screen.
 * @param onNavigateBack callback to navigate back from this screen.
 * @param onNavigateToPhotos callback with album id to navigate to photos screen.
 * @param viewModel the [AlbumsViewModel]. Default is provided by [hiltViewModel].
 */
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLifecycleComposeApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun AlbumsScreen(
    modifier: Modifier,
    windowInsets: WindowInsets,
    onNavigateBack: () -> Unit,
    onNavigateToPhotos: (Int) -> Unit,
    viewModel: AlbumsViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val scrollBehavior = enterAlwaysScrollBehavior()

    BackHandler(onBack = onNavigateBack)

    Scaffold(
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.albums)) },
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
            targetState = screenState
        ) { state ->
            when (state) {
                is AlbumsScreenState.Loading -> LoadingState(
                    modifier = Modifier.fillMaxSize()
                )
                is AlbumsScreenState.Success -> SuccessState(
                    modifier = Modifier.fillMaxSize(),
                    albums = state.albums,
                    onAlbumClick = { albumId ->
                        onNavigateToPhotos(albumId)
                    }
                )
                is AlbumsScreenState.Error -> ErrorState(
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
    albums: List<Album>,
    onAlbumClick: (Int) -> Unit
) = LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(all = 16.dp),
    verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    items(items = albums, key = { it.id }) { album ->
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onAlbumClick(album.id) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(weight = 1F),
                    text = album.title
                )

                Icon(imageVector = AppIcons.NavigateNext, contentDescription = null)
            }
        }
    }
}
package tmidev.apirequest.feature.photos

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tmidev.apirequest.core.design.component.AppAsyncImage
import tmidev.apirequest.core.design.component.ErrorState
import tmidev.apirequest.core.design.component.LoadingState
import tmidev.apirequest.core.design.util.AppIcons
import tmidev.apirequest.core.model.Photo
import tmidev.apirequest.core.design.R as DesignR

/**
 * Compose the Photos Screen.
 *
 * @param modifier the [Modifier] to be applied on top container of this screen.
 * @param windowInsets the[WindowInsets] to be applied on top container of this screen.
 * @param onNavigateBack callback to navigate back from this screen.
 * @param viewModel the [PhotosViewModel]. Default is provided by [hiltViewModel].
 */
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLifecycleComposeApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun PhotosScreen(
    modifier: Modifier,
    windowInsets: WindowInsets,
    onNavigateBack: () -> Unit,
    viewModel: PhotosViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val scrollBehavior = enterAlwaysScrollBehavior()

    BackHandler(onBack = onNavigateBack)

    Scaffold(
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.photos)) },
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
                is PhotosScreenState.Loading -> LoadingState(
                    modifier = Modifier.fillMaxSize()
                )
                is PhotosScreenState.Success -> SuccessState(
                    modifier = Modifier.fillMaxSize(),
                    photos = state.photos
                )
                is PhotosScreenState.Error -> ErrorState(
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
    photos: List<Photo>
) {
    val space = 8.dp
    val photoSize = 120.dp

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = photoSize),
        contentPadding = PaddingValues(all = space),
        verticalArrangement = Arrangement.spacedBy(space = space),
        horizontalArrangement = Arrangement.spacedBy(space = space)
    ) {
        items(items = photos, key = { it.id }) { photo ->
            Surface(
                modifier = Modifier.wrapContentSize(),
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 4.dp
            ) {
                AppAsyncImage(
                    model = photo.url,
                    contentDescription = photo.title,
                    errorIcon = AppIcons.BrokenImage,
                    size = photoSize
                )
            }
        }
    }
}
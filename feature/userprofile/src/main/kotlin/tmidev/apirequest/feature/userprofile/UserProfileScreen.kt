package tmidev.apirequest.feature.userprofile

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tmidev.apirequest.core.design.component.ErrorState
import tmidev.apirequest.core.design.component.LoadingState
import tmidev.apirequest.core.design.util.AppIcons
import tmidev.apirequest.core.model.User
import tmidev.apirequest.core.design.R as DesignR

/**
 * Compose the User Profile Screen.
 *
 * @param modifier the [Modifier] to be applied on top container of this screen.
 * @param windowInsets the[WindowInsets] to be applied on top container of this screen.
 * @param onNavigateBack callback to navigate back from this screen.
 * @param onNavigateToPosts callback with user id to navigate to posts screen.
 * @param onNavigateToAlbums callback with user id to navigate to albums screen.
 * @param viewModel the [UserProfileViewModel]. Default is provided by [hiltViewModel].
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun UserProfileScreen(
    modifier: Modifier,
    windowInsets: WindowInsets,
    onNavigateBack: () -> Unit,
    onNavigateToPosts: (Int) -> Unit,
    onNavigateToAlbums: (Int) -> Unit,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val scrollBehavior = enterAlwaysScrollBehavior()

    BackHandler(onBack = onNavigateBack)

    Scaffold(
        modifier = modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = { Text(text = stringResource(id = R.string.userProfile)) },
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
                is UserProfileScreenState.Loading -> LoadingState(
                    modifier = Modifier.fillMaxSize()
                )
                is UserProfileScreenState.Success -> SuccessState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 16.dp)
                        .verticalScroll(state = scrollState),
                    user = state.user,
                    onSeePostsClick = { userId ->
                        onNavigateToPosts(userId)
                    },
                    onSeeAlbumsClick = { userId ->
                        onNavigateToAlbums(userId)
                    }
                )
                is UserProfileScreenState.Error -> ErrorState(
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
    user: User,
    onSeePostsClick: (Int) -> Unit,
    onSeeAlbumsClick: (Int) -> Unit
) = Column(modifier = modifier) {
    val textInfoModifier = Modifier.padding(horizontal = 4.dp)

    TextLabel(label = stringResource(id = R.string.name))
    TextInfo(modifier = textInfoModifier, info = user.name)

    TextSpacer()

    TextLabel(label = stringResource(id = R.string.username))
    TextInfo(modifier = textInfoModifier, info = user.username)

    TextSpacer()

    TextLabel(label = stringResource(id = R.string.email))
    TextInfo(modifier = textInfoModifier, info = user.email)

    TextSpacer()

    TextLabel(label = stringResource(id = R.string.phone))
    TextInfo(modifier = textInfoModifier, info = user.phone)

    TextSpacer()

    TextLabel(label = stringResource(id = R.string.website))
    TextInfo(modifier = textInfoModifier, info = user.website)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.weight(weight = 1F),
            onClick = { onSeePostsClick(user.id) }
        ) {
            Text(text = stringResource(id = R.string.seePosts))
        }

        Button(
            modifier = Modifier.weight(weight = 1F),
            onClick = { onSeeAlbumsClick(user.id) }
        ) {
            Text(text = stringResource(id = R.string.seeAlbums))
        }
    }
}

@Composable
internal fun TextLabel(
    modifier: Modifier = Modifier,
    label: String,
    style: TextStyle = MaterialTheme.typography.labelMedium
) = Text(
    modifier = modifier,
    text = label,
    style = style
)

@Composable
internal fun TextInfo(
    modifier: Modifier = Modifier,
    info: String,
    style: TextStyle = MaterialTheme.typography.bodyLarge
) = Text(
    modifier = modifier,
    text = info,
    style = style
)

@Composable
internal fun TextSpacer(
    heightSpace: Dp = 16.dp
) = Spacer(modifier = Modifier.height(height = heightSpace))
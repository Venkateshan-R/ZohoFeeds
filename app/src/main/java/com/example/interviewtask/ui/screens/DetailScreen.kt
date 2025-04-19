package com.example.interviewtask.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.interviewtask.R
import com.example.interviewtask.data.models.Comment
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.activities.DetailTopBar
import com.example.interviewtask.ui.composables.FeedItemContent
import com.example.interviewtask.ui.composables.FeedsContentSection
import com.example.interviewtask.ui.composables.FeedsProfileSection
import com.example.interviewtask.ui.composables.Loader
import com.example.interviewtask.ui.theme.customFontFamily
import com.example.interviewtask.ui.utils.UiState
import com.example.interviewtask.ui.utils.formatCount
import com.example.interviewtask.ui.utils.showToast
import com.example.interviewtask.ui.viewmodels.DetailViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(detailViewModel: DetailViewModel, navController: NavController) {
    val context = LocalContext.current
    val isVisible = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(key1 = true) {
        detailViewModel.getSelectedFeed()
        detailViewModel.navigationEvent.collectLatest {
            navController.popBackStack()
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column {
            DetailTopBar() {
                detailViewModel.onBackClicked()
            }
            detailViewModel.selectedFeedStateFlow.collectAsState().value.let { it: UiState<Stream> ->
                when (it) {
                    is UiState.Failure -> {
                        LaunchedEffect(it) {
                            context.showToast(it.error)
                        }
                    }

                    is UiState.Loading -> Loader()
                    is UiState.Success -> {
                        LazyColumn {
                            item {
                                FeedItemContent(stream = it.data, false)
                                HorizontalDivider(
                                    thickness = 0.5.dp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                                CommentsTitleSection(
                                    it.data.comments.size.toString()
                                )
                                HorizontalDivider(
                                    thickness = 0.5.dp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                            items(it.data.comments) { it ->
                                CommentsCard(it) {
                                    isVisible.value = true
                                }
                            }
                        }
                    }
                }
            }
        }
        MoreBottomSheet(sheetState = sheetState, isVisible = isVisible.value) {
            isVisible.value = false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen(modifier: Modifier = Modifier) {
    DetailScreen(
        detailViewModel = hiltViewModel<DetailViewModel>().apply { selectedId = "0" },
        rememberNavController()
    )

}

@Composable
fun CommentsTitleSection(commentsCount: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.comments,commentsCount.toInt()),
                fontFamily = customFontFamily,
                style = MaterialTheme.typography.titleSmall
            )
            Icon(
                modifier = Modifier.rotate(90f),
                painter = painterResource(id = R.drawable.ic_sort_arrow),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }

}

@Composable
fun CommentSection(stream: Stream, onClick: () -> Unit) {
    LazyColumn {
        items(stream.comments) { it ->
            CommentsCard(it, onClick)
        }
    }
}

@Composable
fun CommentsCard(comment: Comment, onClick: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        FeedsProfileSection(
            comment.userDetails,
            comment.formatedTime,
            onClick = onClick,
            isConnectMobileShouldShow = false
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(50.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Spacer(Modifier)
                FeedsContentSection(comment.content)
                CommentsSectionBottomActions(comment)
                Spacer(Modifier)
            }
        }
        HorizontalDivider(
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(start = 40.dp)
        )
    }
}

@Composable
fun CommentsSectionBottomActions(comment: Comment, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
//            modifier = Modifier.weight(.7f)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            VerticalDivider(
                modifier = Modifier.height(12.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant

            )
            Icon(
                painter = painterResource(id = R.drawable.ic_reply),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = stringResource(
                    R.string.replies,
                    (comment.replyCount.toFloatOrNull() ?: 0.0f).formatCount()
                ),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.weight(.3f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_liked),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .padding(1.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_emoji_heart),
                contentDescription = "",
                modifier = Modifier
                    .offset(x = -4.dp)
                    .size(16.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .padding(1.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_emoji_surprised),
                contentDescription = "",
                modifier = Modifier
                    .offset(x = -8.dp)
                    .size(16.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .padding(1.dp)

            )
            Text(
                text = (comment.likeCount.toFloatOrNull() ?: 0.0f).formatCount(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreBottomSheet(
    sheetState: SheetState, isVisible: Boolean, onDismiss: () -> Unit
) {
    if (isVisible) ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            IconWithLabel(R.drawable.ic_link, "Copy URL")
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(0.05f),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            IconWithLabel(R.drawable.ic_copy, "Copy this comment")
        }
    }
}

@Composable
fun IconWithLabel(iconId: Int, labelText: String) {
    Row(
        modifier = Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = labelText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
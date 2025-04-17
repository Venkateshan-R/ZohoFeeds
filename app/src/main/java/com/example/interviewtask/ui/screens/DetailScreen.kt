package com.example.interviewtask.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.font.FontWeight
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
import com.example.interviewtask.ui.utils.showToast
import com.example.interviewtask.ui.viewmodels.DetailViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(detailViewModel: DetailViewModel, navController: NavController) {
    val context = LocalContext.current
    val dataState = remember { detailViewModel.getSelectedPost() }
    val isVisible = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)


    LaunchedEffect(key1 = true) {
        detailViewModel.navigationEvent.collectLatest {
            navController.popBackStack()
        }

    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            MoreBottomSheet(sheetState = sheetState, isVisible = isVisible.value) {
                isVisible.value = false
            }
            DetailTopBar() {
                detailViewModel.onBackClicked()
            }
            dataState.collectAsState().value.let { it: UiState<Stream> ->
                when (it) {
                    is UiState.Failure -> {
                        context.showToast(it.error)
                    }

                    is UiState.Loading -> Loader()
                    is UiState.Success -> {
                        FeedItemContent(stream = it.data, false)
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                        CommentsTitleSection(
                            it.data.comments.size.toString(),
                            Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                        )
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                        CommentSection(it.data) {
                            isVisible.value = true
                        }

                    }
                }
            }
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
fun CommentsTitleSection(commentsCount: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$commentsCount Comments",
                fontFamily = customFontFamily,
                style = MaterialTheme.typography.titleSmall
            )
            Icon(
                modifier = Modifier.rotate(90f),
                painter = painterResource(id = R.drawable.ic_sort_arrow),
                contentDescription = "",
                tint = Color.Black.copy(alpha = 0.5f),
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
                modifier = Modifier.weight(.1f), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FeedsContentSection(comment.content)
                CommentsSectionBottomActions(comment)
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
            }
        }
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
            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = "",
                tint = Color.Black.copy(alpha = 0.5f)
            )
            VerticalDivider(
                modifier = Modifier.height(16.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_reply),
                contentDescription = "",
                tint = Color.Black.copy(alpha = 0.5f)
            )

            Text(
                text = "•  ${comment.replyCount} Replies",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
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
                text = comment.likeCount, style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreBottomSheet(
    sheetState: SheetState, isVisible: Boolean, onDismiss: () -> Unit
) {
    if (isVisible) ModalBottomSheet(onDismissRequest = onDismiss, sheetState = sheetState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            IconWithLabel(R.drawable.ic_link, "Copy URL")
            HorizontalDivider(
                color = Color.LightGray.copy(alpha = 0.3f),
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
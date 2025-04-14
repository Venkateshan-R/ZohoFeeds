package com.example.interviewtask.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.interviewtask.R
import com.example.interviewtask.data.models.Comment
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.activities.DetailTopBar
import com.example.interviewtask.ui.composables.FeedItemContent
import com.example.interviewtask.ui.composables.FeedsBottomSection
import com.example.interviewtask.ui.composables.FeedsContentSection
import com.example.interviewtask.ui.composables.FeedsProfileSection
import com.example.interviewtask.ui.composables.Loader
import com.example.interviewtask.ui.theme.customFontFamily
import com.example.interviewtask.ui.utils.UiState
import com.example.interviewtask.ui.utils.showToast
import com.example.interviewtask.ui.viewmodels.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(detailViewModel: DetailViewModel) {
    val context = LocalContext.current
    val dataState = remember { detailViewModel.getSelectedPost() }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

//    MoreBottomSheet(sheetState = sheetState)
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            DetailTopBar()
            dataState.collectAsState().value.let { it: UiState<Stream> ->
                when (it) {
                    is UiState.Failure -> context.showToast(it.error)
                    is UiState.Loading -> Loader()
                    is UiState.Success -> {
                        FeedItemContent(stream = it.data)
                        CommentsTitleSection(
                            it.data.comments.size.toString(),
                            Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                        CommentSection(it.data)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen(modifier: Modifier = Modifier) {
//    DetailScreen(detailViewModel = DetailViewModel(FeedsRepository()))

}

@Composable
fun CommentsTitleSection(commentsCount: String, modifier: Modifier) {
    Column(modifier = modifier) {
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$commentsCount Comments", fontFamily = customFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
            Icon(
                modifier = Modifier.rotate(90f),
                painter = painterResource(id = R.drawable.ic_sort_arrow),
                contentDescription = "",
                tint = Color.Black.copy(alpha = 0.5f),
            )
        }
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
    }

}

@Composable
fun CommentSection(stream: Stream) {
    LazyColumn {
        items(stream.comments) { it ->
            CommentsCard(it)
        }
    }
}

@Composable
fun CommentsCard(comment: Comment) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        FeedsProfileSection(comment.userDetails, comment.formatedTime)
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.padding(25.dp))
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
            horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                text = "• ${comment.replyCount} Replies",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.7f)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_liked),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_emoji_heart),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .offset(x = -4.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_emoji_surprised),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .offset(x = -8.dp)
            )
            Text(
                text = comment.likeCount,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.7f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreBottomSheet(modifier: Modifier = Modifier, sheetState: SheetState) {
    ModalBottomSheet(onDismissRequest = { }, sheetState = sheetState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_link),
                    contentDescription = "",
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "Copy URL",
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            HorizontalDivider(
                color = Color.LightGray.copy(alpha = 0.3f),
                modifier = Modifier.padding(start = 12.dp, end = 12.dp)
            )
            Row(
                modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "",
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "Copy this comment",
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
}
package com.example.interviewtask.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R
import com.example.interviewtask.data.models.Comment
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.data.repository.FeedsRepository
import com.example.interviewtask.ui.composables.FeedsBottomSection
import com.example.interviewtask.ui.composables.FeedsCard
import com.example.interviewtask.ui.composables.FeedsContentSection
import com.example.interviewtask.ui.composables.FeedsProfileSection
import com.example.interviewtask.ui.navigation.Screens
import com.example.interviewtask.ui.viewmodels.DetailViewModel


@Composable
fun DetailScreen(detailViewModel: DetailViewModel) {
    LaunchedEffect(key1 = true) {
        detailViewModel.getTheSelectedPost();
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        detailViewModel.selectedPost.collectAsState().value?.let {
            Column {
                FeedsProfileSection(it.userDetails, it.formatedTime)
                FeedsContentSection(it.content)
                FeedsBottomSection(it.viewCount)
                CommentsTitleSection(it.comments.size.toString())
                CommentsItem(it)
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
fun CommentsTitleSection(commentsCount: String) {
    Column {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$commentsCount Comments")
            Icon(
                modifier = Modifier.rotate(90f),
                painter = painterResource(id = R.drawable.ic_sort_arrow),
                contentDescription = ""
            )
        }
        HorizontalDivider()
    }

}

@Composable
fun CommentsItem(stream: Stream) {
    LazyColumn {
        items(stream.comments) {comments->
            Column {
                FeedsProfileSection(comments.userDetails, comments.formatedTime)
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(.1f))
                    Column(modifier = Modifier.weight(.9f)) {
                        FeedsContentSection(comments.content)
                        CommetentsSectionBottomActions(comments)
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun CommetentsSectionBottomActions(comment: Comment,modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = "",
                modifier = Modifier.padding(5.dp)
            )

            VerticalDivider(
                modifier = Modifier
                    .height(16.dp)
                    .padding(horizontal = 10.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "",
                modifier = Modifier.padding(5.dp)
            )
            Text(text = "${comment.replyCount} Replies")

        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            Image(painter = painterResource(id = R.drawable.ic_liked), contentDescription = "",modifier=Modifier.size(16.dp))
            Image(painter = painterResource(id = R.drawable.ic_emoji_heart), contentDescription = "",modifier=Modifier.size(16.dp).offset(x = -4.dp))
            Image(painter = painterResource(id = R.drawable.ic_emoji_surprised), contentDescription = "",modifier=Modifier.size(16.dp).offset(x = -8.dp))
            Text(text = comment.likeCount)
        }

    }
}
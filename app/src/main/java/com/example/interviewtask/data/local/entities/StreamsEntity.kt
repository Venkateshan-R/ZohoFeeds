package com.example.interviewtask.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.interviewtask.data.models.Comment
import com.example.interviewtask.data.models.Partition
import com.example.interviewtask.data.models.Reason
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.data.models.UserDetails

@Entity(tableName = "streams_table")
@TypeConverters(Converters::class)
data class StreamsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded val userDetails: UserDetailsEntity,
    @Embedded val partition: PartitionEntitiy,
    val content: String,
    val time: String,
    val streamModifiedTime: String,
    val formatedTime: String,
    val viewCount: String,
    val comments: List<CommentEntity>,
    val url: String,
    val likeCount: String,
    val isCurrentUserLiked: String,
    val tags: List<String>,
    val attachments: List<String>,
    @Embedded val reason: ReasonEntity?
) {
    fun toStream(): Stream {
        return Stream(
            id = id,
            userDetails = userDetails.toUserDetails(),
            partition = partition.toPartition(),
            content = content,
            time = time,
            streamModifiedTime = streamModifiedTime,
            formatedTime = formatedTime,
            viewCount = viewCount,
            comments =comments.map { it.toComment() },
            url = url,
            likeCount = likeCount,
            isCurrentUserLiked = isCurrentUserLiked,
            tags = tags, attachments = attachments,
            reason = reason?.toReason()
        )
    }
}

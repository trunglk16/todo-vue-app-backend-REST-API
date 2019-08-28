package todoVue.domain.models.post

import todoVue.domain.models.post.valueObject.{ PostFields, PostId }
import utility.todoVue.models.Entity

trait Post extends Entity[PostId] with PostFields

object Post {
  def apply(
    identifier: PostId,
    title: String,
    completed: Boolean,
    editing: Boolean): Post = new PostImpl(
    identifier = identifier,
    title = title,
    completed = completed,
    editing = editing)
}

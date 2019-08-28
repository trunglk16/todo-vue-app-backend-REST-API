package postManager.domain.models.post

import todoVue.domain.models.post.valueObject.PostFields

sealed class PostEntry(
  val title: String,
  val completed: Boolean,
  val editing: Boolean)
  extends PostFields

object PostEntry {
  def apply(
    title: String,
    completed: Boolean,
    editing: Boolean): PostFields = new PostEntry(
    title = title,
    completed = completed,
    editing = editing)
}

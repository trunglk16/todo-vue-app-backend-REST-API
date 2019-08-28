package postManager.domain.models.post

import todoVue.domain.models.post.valueObject.PostFields

case class PostEntry(
  title: String,
  completed: Boolean,
  editing: Boolean)
  extends PostFields

object PostEntry {
  def apply(
    title: String,
    completed: Boolean,
    editing: Boolean): PostFields = PostEntry(
    title = title,
    completed = completed,
    editing = editing)
}

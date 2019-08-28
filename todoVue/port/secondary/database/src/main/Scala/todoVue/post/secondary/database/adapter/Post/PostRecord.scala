package todoVue.post.secondary.database.adapter.Post

import utility.todoVue.models.Record

private[Post] case class PostRecord(
  id: Int,
  title: String,
  completed: Boolean,
  editing: Boolean) extends Record

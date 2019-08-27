package todoVue.domain.models.post

import todoVue.domain.models.post.valueObject.PostId

private case class PostImpl(
  identifier: PostId,
  title: String,
  completed: Boolean,
  editing: Boolean) extends Post


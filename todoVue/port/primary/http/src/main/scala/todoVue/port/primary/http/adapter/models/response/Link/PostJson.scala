package todoVue.port.primary.http.adapter.models.response.Link

import todoVue.domain.models.post.Post
import todoVue.port.primary.http.adapter.models.response.ResponseJson
case class PostJson(
  id: Int,
  title: String,
  completed: Boolean,
  editing: Boolean,
                   )
  extends ResponseJson

object PostJson {
  def apply(post: Post): PostJson = {
    PostJson(
      id = post.identifier.value,
      title = post.title,
      completed = post.completed,
      editing = post.editing,
    )
  }

  def apply(links: Seq[Post]): Seq[PostJson] = links.map(PostJson(_))

}

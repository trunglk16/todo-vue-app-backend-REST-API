package todoVue.port.primary.http.adapter.models.request

import postManager.domain.models.post.PostEntry
import play.api.data.Form
import play.api.data.Forms._
import todoVue.domain.models.post.valueObject.PostFields

import scala.util.Try

case class PostForm(
  title: String,
  completed: Boolean,
  editing: Boolean,
) {
  def convertToPost(): Try[PostFields] = Try {
    PostEntry(
      this.title,
      this.completed,
      this.editing)
  }
}

object PostForm {
  val postForm = Form(
    mapping(
      "title" -> text,
      "completed" -> boolean,
      "editing" -> boolean
      )(PostForm.apply)(PostForm.unapply))
}

package todoVue.domain.models.post.valueObject

import utility.todoVue.models.Fields

trait postFields extends Fields {
  val title: String
  val completed: Boolean
  val editing: Boolean

}

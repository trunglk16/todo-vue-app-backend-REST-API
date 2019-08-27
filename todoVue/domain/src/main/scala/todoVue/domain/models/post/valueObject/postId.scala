package todoVue.domain.models.post.valueObject

import utility.todoVue.models.Identifier

case class PostId(value: Int) extends Identifier[Int] {
  override def toString: String = value.toString
}

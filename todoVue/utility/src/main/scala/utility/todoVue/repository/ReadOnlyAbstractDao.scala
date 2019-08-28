package utility.todoVue.repository

import scala.util.Try

trait ReadOnlyAbstractDao[T] {

  def getAll()(implicit ctx: ReadOnlyIOContext): Try[Seq[T]]
}

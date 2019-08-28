package todoVue.domain.models.post

import todoVue.domain.models.post.valueObject._
import utility.todoVue.repository._

import scala.util.Try

trait PostRepository extends FeatureWithIdFieldRepository[PostId, PostFields, Post] {
  def bulkCreateOrUpdate(fields: Seq[PostFields])(implicit ctx: IOContext): Try[Int]

  def resolveById(Id: Int)(implicit ctx: IOContext): Try[List[Post]]

}

package todoVue.post.secondary.database.adapter.Post

import scalikejdbc._
import todoVue.domain.models.post.{ Post, PostRepository }
import todoVue.domain.models.post.valueObject.{ PostFields, PostId }
import todoVue.post.secondary.database.adapter.BasicWithIdFeatureRepositoryOnJDBC
import utility.todoVue.repository.IOContext

import scala.util.Try

class PostRepositoryImpl(override protected[this] val dao: PostDAO = new PostDAO) extends PostRepository
  with BasicWithIdFeatureRepositoryOnJDBC[PostId, PostFields, Post, Int, PostRecord] {

  override type DAO = PostDAO

  override def bulkCreateOrUpdate(fields: Seq[PostFields])(implicit ctx: IOContext): Try[Int] =
    withDBSession { implicit session =>
      dao.bulkCreateOrUpdate(
        Seq(
          dao.column.title,
          dao.column.completed,
          dao.column.editing),
        fields,
        Seq(
          dao.column.title,
          dao.column.completed,
          dao.column.editing),
        toValues)
    }

  protected def toValues(fields: PostFields): Seq[Any] = fieldsFromNamedValues(fields).map(v => v._2)

  override def resolveById(Id: Int)(implicit ctx: IOContext): Try[List[Post]] = withDBSession { implicit session =>
    dao.where(sqls.eq(dao.column.id, Id)).apply().map(convertToEntity _)
  }

  override protected def fieldsFromNamedValues(fields: PostFields): Seq[(Symbol, Any)] = Seq(
    'title -> fields.title,
    'completed -> fields.completed,
    'editing -> fields.editing)

  override def convertToIdentifier(id: Int): PostId = PostId(id)

  override protected def convertToEntity(record: PostRecord): Post = {
    Post(
      PostId(record.id),
      record.title,
      record.completed,
      record.editing)
  }

  override protected def convertToRecordId(identifier: PostId): Int = identifier.value
}

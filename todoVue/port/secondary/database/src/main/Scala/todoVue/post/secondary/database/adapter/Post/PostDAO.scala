package todoVue.post.secondary.database.adapter.Post

import scalikejdbc.WrappedResultSet
import skinny.orm.Alias
import todoVue.domain.models.post.valueObject.PostFields
import todoVue.post.secondary.database.adapter.CRUDMapperWithId

private [Post] class PostDAO extends CRUDMapperWithId[Int, PostFields, PostRecord]{
  override lazy val tableName = "post"
  override lazy val primaryKeyFieldName = "id"
  override def columnNames: Seq[String] = Seq(
    "id",
    "title",
    "completed",
    "editing",
  )

  override def defaultAlias: Alias[PostRecord] = createAlias("p")

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[PostRecord]): PostRecord = {
    PostRecord(
      id = rs.get(n.id),
      title = rs.get(n.title),
      completed = rs.get(n.completed),
      editing = rs.get(n.editing),
    )
  }

  override def idToRawValue(id: Int): Int = id

  override def rawValueToId(value: Any): Int = value.asInstanceOf[Int]

  override def toNamedValues(entity: PostRecord): Seq[(Symbol, Any)] = {
    Seq(
      'id -> entity.id,
      'title -> entity.title,
      'completed -> entity.completed,
      'editing -> entity.editing
    )
  }
}

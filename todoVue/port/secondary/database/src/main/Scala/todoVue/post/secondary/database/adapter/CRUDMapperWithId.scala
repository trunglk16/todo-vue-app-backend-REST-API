package todoVue.post.secondary.database.adapter

import skinny.orm.SkinnyCRUDMapperWithId

trait CRUDMapperWithId[ID, ObjectFields, Entity] extends CRUDMapper[ObjectFields, Entity]
  with SkinnyCRUDMapperWithId[ID, Entity] {
  override def useAutoIncrementPrimaryKey: Boolean = true
}

package todoVue.post.secondary.database.adapter

import scalikejdbc._
import scalikejdbc.interpolation.SQLSyntax
import skinny.orm.SkinnyMapperBase

trait CRUDMapper[ObjectFields, Entity] extends SkinnyMapperBase[Entity] {
  def toNamedValues(entity: Entity): Seq[(Symbol, Any)]

  def bulkCreateOrUpdate(
    updateColumns: Seq[SQLSyntax],
    fields: Seq[ObjectFields],
    duplicateKeys: Seq[SQLSyntax],
    toValue: ObjectFields => Seq[Any])(implicit s: DBSession): Int = {
    if (fields.isEmpty) {
      0
    } else {
      withSQL {
        insertInto(this)
          .columns(updateColumns: _*)
          .append(BulkQuery.multiValuesPlaceholders(fields.size, updateColumns.size))
          .append(BulkQuery.onDuplicateKeyUpdateSyntax(duplicateKeys))
      }.bind(
        fields.flatMap(toValue): _*).executeUpdate().apply()(s)
    }
  }

  object BulkQuery {

    def multiValuesPlaceholders(recordSize: Int, syntaxSize: Int): SQLSyntax = {
      val oneRecordBlock = sqls.roundBracket(
        sqls.csv(Seq.fill(syntaxSize)(sqls.?): _*))
      sqls" values ${sqls.csv(Seq.fill(recordSize)(oneRecordBlock): _*)} "
    }

    def onDuplicateKeyUpdateSyntax(duplicateKeys: Seq[SQLSyntax]): SQLSyntax = {
      sqls" on duplicate key update ".append(
        sqls.csv(
          duplicateKeys.map(k => sqls"$k = values($k)"): _*))
    }
  }
}

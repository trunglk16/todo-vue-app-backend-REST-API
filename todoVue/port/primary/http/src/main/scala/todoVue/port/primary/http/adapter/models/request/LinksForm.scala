//package todoVue.port.primary.http.adapter.models.request
//
//import play.api.data.Form
//import play.api.data.Forms._
//
//import scala.util.Try
//
//case class LinksForm(
//  linkName: String,
//  description: String,
//  linkUrl: String,
//  is_delete: Boolean,
//  categoryId: Int,
//  userCreatedId: Int,
//  linkType: Int) {
//  def convertToLink(): Try[LinkFields] = Try {
//    LinkEntry(
//      this.linkName,
//      this.description,
//      this.linkUrl,
//      this.is_delete,
//      CategoryId(this.categoryId),
//      UserId(this.userCreatedId),
//      this.linkType)
//  }
//}
//
//object LinksForm {
//  val linkForm = Form(
//    mapping(
//      "linkName" -> nonEmptyText,
//      "description" -> text,
//      "linkUrl" -> nonEmptyText,
//      "is_delete" -> boolean,
//      "categoryId" -> number,
//      "userCreatedId" -> number,
//      "linkType" -> number)(LinksForm.apply)(LinksForm.unapply))
//}

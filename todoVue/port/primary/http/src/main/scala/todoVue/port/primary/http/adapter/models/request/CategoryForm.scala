//package todoVue.port.primary.http.adapter.models.request
//
//import play.api.data.Form
//import play.api.data.Forms._
//
//import scala.util.Try
//
//case class CategoryForm(
//  categoryName: String,
//  description: String,
//  is_delete: Boolean,
//  createdTime: Long,
//  userCreatedId: Int) {
//  def convertToCategory(): Try[CategoryFields] = Try {
//    CategoryEntry(
//      this.categoryName,
//      this.description,
//      this.is_delete,
//      this.createdTime,
//      UserId(this.userCreatedId))
//  }
//}
//
//object CategoryForm {
//  val categoryForm = Form(
//    mapping(
//      "categoryName" -> nonEmptyText,
//      "description" -> text,
//      "is_delete" -> boolean,
//      "createdTime" -> longNumber,
//      "userCreatedId" -> number)(CategoryForm.apply)(CategoryForm.unapply))
//}

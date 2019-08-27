//package todoVue.port.primary.http.adapter.models.responses.Category
//
//import todoVue.domain.models.Category.Category
//import todoVue.port.primary.http.adapter.models.response.ResponseJson
//
//case class CategoryJson(
//  id: Int,
//  categoryName: String,
//  description: String,
//  is_delete: Boolean,
//  createdTime: Long,
//  userCreatedId: Int) extends ResponseJson
//
//object CategoryJson {
//  def apply(category: Category): CategoryJson = {
//    CategoryJson(
//      id = category.identifier.value,
//      categoryName = category.categoryName,
//      description = category.description,
//      is_delete = category.is_delete,
//      createdTime = category.createdTime,
//      userCreatedId = category.userCreatedId.value)
//  }
//
//  def apply(categorys: Seq[Category]): Seq[CategoryJson] = categorys.map(CategoryJson(_))
//}

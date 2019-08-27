//package todoVue.port.primary.http.adapter.models.response.Link
//
//import todoVue.domain.models.Link.Link
//import todoVue.port.primary.http.adapter.models.response.ResponseJson
//case class LinkJson(
//  id: Int,
//  linkName: String,
//  description: String,
//  linkUrl: String,
//  is_delete: Boolean,
//  categoryId: Int,
//  userCreatedId: Int,
//  linkType: Int) extends ResponseJson
//
//object LinkJson {
//  def apply(link: Link): LinkJson = {
//    LinkJson(
//      id = link.identifier.value,
//      linkName = link.linkName,
//      description = link.description,
//      linkUrl = link.linkUrl,
//      is_delete = link.is_delete,
//      categoryId = link.categoryId.value,
//      userCreatedId = link.userCreatedId.value,
//      linkType = link.linkType)
//  }
//
//  def apply(links: Seq[Link]): Seq[LinkJson] = links.map(LinkJson(_))
//}

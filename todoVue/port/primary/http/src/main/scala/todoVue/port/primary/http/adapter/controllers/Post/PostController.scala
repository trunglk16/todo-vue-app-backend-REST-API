package todoVue.port.primary.http.adapter.controllers.Post

import com.github.tototoshi.play2.json4s.jackson.Json4s
import com.google.inject.Inject
import javax.inject.Singleton
import play.api.i18n.Langs
import play.api.mvc.{ ControllerComponents, _ }
import todoVue.domain.models.post.PostRepository
import todoVue.port.primary.http.adapter.controllers.common.APIController
import todoVue.port.primary.http.adapter.models.response.Link.PostJson
import utility.todoVue.repository.IOContextManager

import scala.util.{ Failure, Success }

class PostController @Inject() (
  override val json4s: Json4s,
  cc: ControllerComponents,
  langs: Langs,
  postRepository: PostRepository,
  IOContextManager: IOContextManager) extends APIController(cc, langs) {

  def list(): EssentialAction = Action {
    val result = postRepository.resolveAll().get
    success(PostJson(result))
  }

  //  def insert(): EssentialAction = Action { implicit request =>
  //    PostsForm.linkForm.bind(request.body.asJson.get).fold(
  //      formWithError => {
  //        BadRequest("Bad request" + formWithError)
  //      },
  //      linkData => {
  //        IOContextManager.transactionalContext { implicit ctx =>
  //          val results = postRepository.bulkCreateOrUpdate(Seq(linkData.convertToLink.get))
  //          success(results.get.toString)
  //        }
  //      })
  //  }
  //
  //  def listFromCatId(id: Int): EssentialAction = Action {
  //    IOContextManager.transactionalContext { implicit ctx =>
  //      val result = postRepository.resolveByCatId(id).get
  //      success(LinkJson(result))
  //    }
  //  }

  //  def delete(id: Int): EssentialAction = Action { implicit request =>
  //    println(id)
  //    val result = postRepository.deleteBy(LinkId(id))
  //    println(result)
  //    result match {
  //      case Success(value) => {
  //        success("Delete success ")
  //      }
  //      case Failure(exception) => BadRequest(exception.getMessage)
  //    }
  //  }
  //  def showInformation(id: Int): EssentialAction = Action { implicit request =>
  //    IOContextManager.transactionalContext { implicit ctx =>
  //      val result = postRepository.resolveById(id)
  //      result match {
  //        case Success(value) => {
  //          println(PostJson(value))
  //          success(PostJson(value))
  //        }
  //        case Failure(exception) => BadRequest(exception.getMessage)
  //      }
  //    }
  //  }
}

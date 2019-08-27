package todoVue.port.primary.http.adapter

import javax.inject._
import todoVue.port.primary.http.adapter.models.response.{ APIResult, ErrorCode }
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization.write
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._
import play.api.routing.Router
import play.api.{ Configuration, Environment, OptionalSourceMapper }
import utility.todoVue.exceptions._

import scala.concurrent._

@Singleton
class ErrorHandler @Inject() (
  env: Environment,
  config: Configuration,
  sourceMapper: OptionalSourceMapper,
  router: Provider[Router]) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

  implicit val formats = DefaultFormats

  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(
      BadRequest(
        write(APIResult.toErrorJson(
          code = ErrorCode.ParameterFormatError.value,
          message = "error.parameterFormatError"))))
  }

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(
      exception match {
        case _: EntityNotFoundException =>
          NotFound(
            write(APIResult.toErrorJson(
              code = ErrorCode.EntityNotFoundError.value,
              message = "error.entityNotFound")))
        case _: UnauthorizedException =>
          Unauthorized(
            write(APIResult.toErrorJson(
              code = ErrorCode.Unauthorized.value,
              message = "error.authenticationError")))
        case _: PermissionDeniedException =>
          BadRequest(
            write(APIResult.toErrorJson(
              code = ErrorCode.PermissionDenied.value,
              message = "error.permissionDeniedError")))
        case _ =>
          InternalServerError(
            write(APIResult.toErrorJson(
              code = ErrorCode.UnknownError.value,
              message = "error.unknown")))
      })
  }

  override def onForbidden(request: RequestHeader, message: String): Future[Result] = {
    Future.successful(
      Forbidden(
        write(APIResult.toErrorJson(
          code = ErrorCode.ForbiddenError.value,
          message = "error.forbiddenError"))))
  }
}

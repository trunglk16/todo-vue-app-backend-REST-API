package todoVue.port.primary.http.adapter.controllers.common

import java.io.File

import com.github.tototoshi.play2.json4s.Json4s
import org.json4s.JsonAST.JValue
import org.json4s.{ DefaultFormats, Extraction }
import play.api.data.FormError
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._
import todoVue.port.primary.http.adapter.models.response.{ APIResult, ResponseJson, WarningParameter }

import scala.concurrent.ExecutionContext.Implicits.global

abstract class APIController(cc: ControllerComponents, langs: Langs) extends AbstractController(cc) with I18nSupport {
  protected val json4s: Json4s
  import json4s.implicits._
  implicit val formats = DefaultFormats
  implicit val lang: Lang = langs.availables.head

  protected def success[T <: ResponseJson](result: T): Result =
    Ok(Extraction.decompose(APIResult.toSuccessJson(result)))

  protected def successWithJObject(result: Seq[org.json4s.JObject]): Result =
    Ok(Extraction.decompose(APIResult.toSuccessJson(result.map(_.toOption.getOrElse(Json.parse(""))))))

  protected def success[T <: ResponseJson](result: Seq[T]): Result =
    Ok(Extraction.decompose(APIResult.toSuccessJson(result)))

  protected def success[T <: ResponseJson](file: File): Result =
    Ok.sendFile(file)

  protected def failure[T <: ResponseJson](result: Seq[T]): Result =
    Ok(Extraction.decompose(APIResult.toFailureJson(result)))

  protected def success(): Result =
    Ok(Extraction.decompose(APIResult.toSuccessJson))

  protected def success(message: String): Result =
    Ok(Extraction.decompose(APIResult.toSuccessJson(message)))

  protected def badRequestWarning(warning: WarningParameter): Result =
    BadRequest(Extraction.decompose(APIResult.toWarningJson(Seq(warning))))

  protected def badRequestWarning(formErrors: Seq[FormError]): Result = {
    BadRequest(Extraction.decompose(APIResult.toWarningJson(formErrors.map { formError =>
      {
        val errorKey: String = if (formError.message.nonEmpty && formError.message != "error.required") {
          formError.message
        } else {
          formError.key
        }
        WarningParameter(
          errorKey,
          cc.messagesApi("warn." + errorKey + ".code"),
          cc.messagesApi("warn." + errorKey))
      }
    })))
  }

  protected def error[T](status: (JValue) => Result, code: Int, message: String): Result =
    status(Extraction.decompose(APIResult.toErrorJson(code, message)))

  protected def unauthorizedError(code: Int, message: String): Result =
    Results.Unauthorized(Extraction.decompose(APIResult.toErrorJson(code, message)))
}

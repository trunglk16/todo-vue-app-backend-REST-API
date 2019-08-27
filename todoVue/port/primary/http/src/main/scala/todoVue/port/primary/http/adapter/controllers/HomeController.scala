package todoVue.port.primary.http.adapter.controllers

import javax.inject.{ Inject, Singleton }
import play.api.mvc.{ AbstractController, ControllerComponents }

@Singleton
class HomeController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok("Say something")
  }

}

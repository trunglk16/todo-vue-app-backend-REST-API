package todoVue.post.secondary.database.adapter.common

import scalikejdbc.DBSession
import todoVue.post.secondary.database.adapter.IOContextOnJDBC
import utility.todoVue.repository.IOContext

import scala.util.{ Failure, Success, Try }

trait DBSessionOnJDBC {

  protected def getDBSession(implicit ctx: IOContext): Try[DBSession] = {
    ctx match {
      case IOContextOnJDBC(session) => Success(session)
      case _ =>
        Failure(new IllegalArgumentException(s"$ctx is type miss match. please set to EntityIOContextOnJDBC."))
    }
  }

  protected def withDBSession[T](func: DBSession => T)(implicit ctx: IOContext): Try[T] = {
    getDBSession.map(func)
  }
}

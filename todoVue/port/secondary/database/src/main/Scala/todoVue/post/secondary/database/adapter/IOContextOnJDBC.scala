package todoVue.post.secondary.database.adapter

import scalikejdbc.DBSession
import utility.todoVue.repository.IOContext

case class IOContextOnJDBC(session: DBSession) extends IOContext

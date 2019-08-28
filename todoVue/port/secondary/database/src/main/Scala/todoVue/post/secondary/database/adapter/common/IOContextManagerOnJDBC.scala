package todoVue.post.secondary.database.adapter.common

import scalikejdbc._
import todoVue.post.secondary.database.adapter.IOContextOnJDBC
import utility.todoVue.repository.{ IOContext, IOContextManager }

class IOContextManagerOnJDBC extends IOContextManager {

  private val database = 'default

  def context: IOContext = IOContextOnJDBC(AutoSession)

  def transactionalContext[T](execution: (IOContext) => T): T = {
    NamedDB(database) localTx { session =>
      execution(IOContextOnJDBC(session))
    }
  }
}

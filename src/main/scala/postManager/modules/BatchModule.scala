package postManager.modules

import com.google.inject.AbstractModule
import com.typesafe.config.{ Config, ConfigFactory }
import scalikejdbc.config.DBs
import todoVue.domain.models.post.PostRepository
import todoVue.post.secondary.database.adapter.Post.PostRepositoryImpl
import todoVue.post.secondary.database.adapter.common.IOContextManagerOnJDBC
import utility.todoVue.repository.IOContextManager

class BatchModule extends AbstractModule {
  DBs.setupAll()

  lazy val config: Config = ConfigFactory.load()

  lazy val ioContextManager: IOContextManager = new IOContextManagerOnJDBC
  lazy val postRepository: PostRepository = new PostRepositoryImpl()

  override def configure(): Unit = {
    bind(classOf[PostRepository]).toInstance(postRepository)
    bind(classOf[IOContextManager]).toInstance(ioContextManager)

  }

}

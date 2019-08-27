package utility.todoVue.exceptions

class EntityNotFoundException(message: String, cause: Option[Throwable] = None)
  extends BaseException(message, cause)

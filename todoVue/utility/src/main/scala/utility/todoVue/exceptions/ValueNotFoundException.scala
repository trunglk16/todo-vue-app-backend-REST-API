package utility.todoVue.exceptions

class ValueNotFoundException(message: String, cause: Option[Throwable] = None)
  extends BaseException(message, cause)

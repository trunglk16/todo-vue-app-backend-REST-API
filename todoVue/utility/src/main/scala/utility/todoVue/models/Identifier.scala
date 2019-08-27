package utility.todoVue.models

trait Identifier[+A] extends Serializable {

  def value: A
}


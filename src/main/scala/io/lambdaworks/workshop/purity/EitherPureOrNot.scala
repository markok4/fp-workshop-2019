package io.lambdaworks.workshop.purity
import java.util.{ArrayList => JList}

import org.joda.time.DateTime

object EitherPureOrNot {

  final case class Todo(id: Int, title: String)

  private var todoList = Seq(Todo(1, "Make a coffee!"), Todo(2, "Clean the house!"))
  private val builder  = new StringBuilder()

  // Either pure or not?

  def addTodo(todo: Todo): Unit = todoList :+= todo // Not pure because for same call (twice time) todoList will not be the same

  def currentDate: DateTime = DateTime.now // Not pure, same call, different time

  def evenNumbers(lowerBound: Int, upperBound: Int): Seq[Int] = { // Pure
    var result = Seq[Int]()
    for (index <- lowerBound to upperBound) {
      if (0 == index % 2) result :+= index
    }

    result
  }

  def firstElement(todoList: List[Todo]): Todo = { // Pure, same todoList -> same head
    val head = todoList.head
    println(head)

    head
  }

  def fullName(firstName: String, lastName: String): String = { // Not pure, builder appends firstNamea and lastName, next iteration will be added new firstName on exist String, dont make new String
    builder.append(firstName)
    builder.append(lastName)
    builder.mkString(" ")
  }

  def square4j(numbers: JList[Int]): JList[Int] = { // Not pure, the numbers list has been changed
    for (index <- 0 until numbers.size()) {
      val number = numbers.get(index)
      numbers.set(index, number * number)
    }

    numbers
  }

  def square4s(numbers: Seq[Int]): Seq[Int] = { // Not pure, the numbers list is changed
    var result = numbers
    for (index <- numbers.indices) {
      val number = numbers(index)
      result = numbers.updated(index, number * number)
    }

    result
  }

}

package org.aliensource.model

import scala.slick.driver.HsqldbDriver.api._
import scala.slick.lifted.Tag
import org.aliensource.model.Person

/**
 * Created by ttruong on 27-Dec-14.
 */
class Persons(tag: Tag) extends BaseTable[Person](tag, "person") {

  def email = column[String]("EMAIL")
  def password = column[String]("PASSWORD")

  def * = (email, password, id.?) <> (Person.tupled, Person.unapply)
}
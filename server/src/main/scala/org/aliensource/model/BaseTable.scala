package org.aliensource.model

import scala.slick.driver.HsqldbDriver.api._
import scala.slick.lifted.Tag

/**
 * Created by ttruong on 27-Dec-14.
 */
abstract class BaseTable[Entity <: BaseModel](tag: Tag, tableName: String) extends Table[Entity](tag, tableName) {

  def id = column[Long]("ID",  O.PrimaryKey, O.AutoInc)
}
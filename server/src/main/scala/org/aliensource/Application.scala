package org.aliensource

import org.aliensource.model.{Person, Persons}
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.slick.driver.HsqldbDriver.api._
import scala.slick.lifted.TableQuery

/**
 * Created by ttruong on 27-Dec-14.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
class Application

object Application {

  def log = LoggerFactory.getLogger(this.getClass());

  def main(args: Array[String]) : Unit = {
    initDatabase
    SpringApplication.run(classOf[Application], args:_ *)
  }

  val persons = TableQuery[Persons]

  val db = Database.forURL("jdbc:hsqldb:mem:mymemdb", driver = "org.hsqldb.jdbc.JDBCDriver")

  def initDatabase: Unit = {
    val setup = Action.seq(
      // create the schema
      persons.schema.create,
      // insert two Person instances
      persons +=  Person("John Doe", "xxx"),
      persons += Person("Fred Smith", "xxx")
    )

    log.info("=====================")
    log.info("Create schemas")
    Await.result(db.run(setup), Duration.Inf)
    log.info("=====================")

    //test query to get all persons
    log.info("trying to get all persons...")
    Await.result(db.run(persons.result), Duration.Inf)
  }
}

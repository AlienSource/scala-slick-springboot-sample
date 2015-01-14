package org.aliensource.model

//import javax.validation.constraints.NotNull

import scala.beans.BeanProperty

/**
 * Created by ttruong on 27-Dec-14.
 */
case class Person (
  @BeanProperty
  // @Email // hibernate validation - org.hibernate.validator.constraints.Email
  //@NotNull
  email: String,
  @BeanProperty
  //@NotNull
  password: String,
  id: Option[Long] = None) extends BaseModel

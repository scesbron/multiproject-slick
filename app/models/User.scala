package models

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Join
import slick.lifted.{ Join, MappedTypeMapper }
import scala.slick.lifted.ForeignKeyAction

private[models] trait DAO extends UsersComponent {
  val Users = new Users
}
case class User(id: Option[Long], email: String)

trait UsersComponent {
  val Users: Users

  class Users extends Table[User]("User") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("email", O.NotNull)
    def * = id.? ~ email <> (User.apply _, User.unapply _)
    def autoInc = * returning id

    val byId = createFinderBy(_.id)
  }
}
object Users extends DAO {

  def insert(user: User)(implicit s: Session) {
    Users.autoInc.insert(user)
  }
  def findById(id: Long)(implicit s: Session): Option[User] =
    Users.byId(id).firstOption
}
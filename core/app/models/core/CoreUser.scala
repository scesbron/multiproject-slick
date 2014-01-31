package models.core

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Join
import slick.lifted.{ Join, MappedTypeMapper }
import scala.slick.lifted.ForeignKeyAction

private[models] trait CoreDAO extends CoreUsersComponent {
  val CoreUsers = new CoreUsers
}
case class CoreUser(id: Option[Long], email: String)

trait CoreUsersComponent {
  val CoreUsers: CoreUsers

  class CoreUsers extends Table[CoreUser]("CoreUser") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("email", O.NotNull)
    def * = id.? ~ email <> (CoreUser.apply _, CoreUser.unapply _)
    def autoInc = * returning id

    val byId = createFinderBy(_.id)
  }
}
object CoreUsers extends CoreDAO {

  def insert(CoreUser: CoreUser)(implicit s: Session) {
    CoreUsers.autoInc.insert(CoreUser)
  }
  def findById(id: Long)(implicit s: Session): Option[CoreUser] =
    CoreUsers.byId(id).firstOption
}
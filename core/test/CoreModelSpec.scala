import models.core.CoreUsers
import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import play.api.db.slick.DB
import slick.session.Session
import play.api.Play.current


/**
 * Created by sebastien on 31/01/14.
 */
class CoreModelSpec extends Specification {

  "be retrieved by id" in {
    running(FakeApplication()) {
      DB.withSession{ implicit s:Session =>
        val Some(admin) = CoreUsers.findById(1)

        admin.email must equalTo("tech@sysope.fr")
      }
    }
  }

}

package controllers.tutorials

import javax.inject.Inject

import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.{ LogoutEvent, Silhouette }
import controllers.AssetsFinder
import org.webjars.play.WebJarsUtil
import play.api.i18n.{ Lang, Langs, I18nSupport, Messages, MessagesApi }
import play.api.mvc.{ Action, AbstractController, AnyContent, ControllerComponents }
import utils.auth.DefaultEnv
import controllers.pages
import play.api.i18n.Lang

import scala.concurrent.Future

/**
 * The basic application controller.
 *
 * @param components  The Play controller components.
 * @param silhouette  The Silhouette stack.
 * @param webJarsUtil The webjar util.
 * @param assets      The Play assets finder.
 */
class Scala @Inject() (
  components: ControllerComponents,
  silhouette: Silhouette[DefaultEnv],
  langs: Langs,
  messagesApi: MessagesApi
)(
  implicit
  webJarsUtil: WebJarsUtil,
  assets: AssetsFinder
) extends AbstractController(components) with I18nSupport {

  implicit val lang: Lang = langs.availables.head

  def installPlay: Action[AnyContent] = Action.async { implicit request =>
    //    Future.successful(Ok(views.html.tutorials.installPlay.installPlay()))
    Future.successful(Ok(views.html.tutorials.course.course()))
  }

}

package controllers.tutorials

import javax.inject.Inject

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import com.mohiva.play.silhouette.api.{ LogoutEvent, Silhouette }
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import controllers.{ WebJarAssets, pages }
import play.api.i18n.{ I18nSupport, MessagesApi, Lang }
import play.api.mvc.{ Action, AnyContent, Controller, InjectedController }
import utils.auth.DefaultEnv

import scala.concurrent.Future
import play.api.i18n.Lang

/**
 * The basic application controller.
 *
 * @param messagesApi            The Play messages API.
 * @param silhouette             The Silhouette stack.
 * @param socialProviderRegistry The social provider registry.
 * @param webJarAssets           The webjar assets implementation.
 */
class Scala @Inject() (
  override val messagesApi: MessagesApi,
  silhouette: Silhouette[DefaultEnv],
  socialProviderRegistry: SocialProviderRegistry,
  implicit val webJarAssets: WebJarAssets,
  implicit val lang: Lang)
  extends InjectedController with I18nSupport {

  def installPlay: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.tutorials.course.course()))
  }

}


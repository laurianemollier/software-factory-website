package controllers.pages

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{ LogoutEvent, Silhouette }
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import controllers.{ WebJarAssets, pages }
import forms.ContactData
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.libs.mailer.{ Email, MailerClient }
import play.api.mvc.{ Action, AnyContent, Controller }
import utils.auth.DefaultEnv

import scala.concurrent.Future

/**
 * The basic application controller.
 *
 * @param messagesApi            The Play messages API.
 * @param silhouette             The Silhouette stack.
 * @param socialProviderRegistry The social provider registry.
 * @param webJarAssets           The webjar assets implementation.
 */
class Contact @Inject() (
  val messagesApi: MessagesApi,
  mailerClient: MailerClient,
  silhouette: Silhouette[DefaultEnv],
  socialProviderRegistry: SocialProviderRegistry,
  implicit val webJarAssets: WebJarAssets)
  extends Controller with I18nSupport {

  def view: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(views.html.contactUs.contactUs(ContactData.form)))
  }

  /**
   * Action to send a email to the company, Form: ContactData.form
   *
   * @return
   */
  def submit = Action { implicit request =>
    ContactData.form.bindFromRequest.fold(
      errorForm => {
        BadRequest(views.html.contactUs.contactUs(errorForm))
      },
      contactData => {
        val email_to_lauriane = Email(
          "Simple email",
          "Square it FROM <" + contactData.email + ">",
          Seq("Miss TO <mollierlauriane@gmail.com>"),
          bodyText = Some("From:" + contactData.email + " \n \n " + contactData.text)
        )
        mailerClient.send(email_to_lauriane)

        val email_to_jb = Email(
          "Test Email",
          "Square it FROM <" + contactData.email + ">",
          Seq("Miss TO <jean.baptiste.lasselle@gmail.com>"),
          bodyText = Some("From:" + contactData.email + " \n \n " + contactData.text)
        )
        mailerClient.send(email_to_jb)

        Redirect(pages.routes.Contact.view()).flashing(
          "emailSend" -> "Email send"
        )
      }
    )
  }
}

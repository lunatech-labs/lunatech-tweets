package nl.lunatech.tweets

import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpRequest
import akka.stream.{ ActorMaterializer, KillSwitches, Materializer }
import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.{ AccessToken, ConsumerToken }
import com.danielasfregola.twitter4s.http.clients.streaming.TwitterStream
import com.danielasfregola.twitter4s.http.oauth.OAuth1Provider
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{ Matchers, WordSpec }
import org.mockito.Mockito._

import scala.concurrent.Future

//class TwitterServiceSpec extends WordSpec with MockitoSugar with Matchers with OAuthClient {
//
//  private val streamClientMock = mock[TwitterStreamingClient]
//
//  private val consumerToken = ConsumerToken("twitter.consumer.key", "my-token")
//  private val accessToken   = AccessToken("twitter.consumer.secret", "my-key")
//
//  implicit val system       = ActorSystem("")
//  implicit val materializer = ActorMaterializer()
//  implicit val ec           = materializer.executionContext
//
//  val oauthProvider = new OAuth1Provider(consumerToken, Some(accessToken))
//  val request       = withOAuthHeader(None)(materializer)(request)
//
//  private val killSwitch = KillSwitches.shared(s"test-twitter4s-${UUID.randomUUID}")
//
//  private val twitterStreamMock = TwitterStream(consumerToken, accessToken)
//
//  "Twitter Service" should {
//    "start a filtered status stream" in {
//      val tags = Seq("#test1", "#test2")
//      when(streamClientMock.filterStatuses _) // thenReturn Future.successful(twitterStreamMock)
//    }
//  }
//
//  def withOAuthHeader(callback: Option[String])(implicit materializer: Materializer): HttpRequest => Future[HttpRequest] = {
//    request =>
//      implicit val ec = materializer.executionContext
//      for {
//        authorizationHeader <- oauthProvider.oauth1Header(callback)(request, materializer)
//      } yield request.withHeaders(request.headers :+ authorizationHeader)
//  }
//}

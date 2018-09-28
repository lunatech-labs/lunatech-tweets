package nl.lunatech.tweets
import org.scalatest.{ Matchers, WordSpec }
import com.softwaremill.sttp._
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

class SlackServiceSpec extends WordSpec with MockitoSugar with Matchers {

  val contentType      = "application/json"
  implicit val backend = HttpURLConnectionBackend()
  val sttpMock         = mock[RequestT[Empty, String, Nothing]]
  val responseMock     = mock[Id[Response[String]]]
  val resquestMock     = mock[Request[String, Nothing]]
  val resquestTMock    = mock[RequestT[Id, String, Nothing]]

  val webHook      = uri"http://lunatech-slack-channel"
  val slackService = new SlackService(webHook)

  "Slack Service" should {
    "send messages to a slack webhook sucessfully" in {
      val messageFrom = "tweet sender"
      val message     = "Lunatech is a cool company!"
      val requestBody = s"""{"text": "$messageFrom:\n $message"}"""

      when(responseMock.code) thenReturn 200
      when(sttpMock.post(webHook)) thenReturn resquestMock
      when(resquestMock.contentType(contentType)) thenReturn resquestTMock
      when(resquestTMock.body(requestBody)) thenReturn resquestTMock
      when(resquestTMock.send) thenReturn responseMock

      slackService.postMessage(sttpMock, messageFrom, message) shouldBe 200
    }

    "fix quotes in messages by escaping them" in {
      val messageWithQuotes        = """This is a message that includes "quoting something""""
      val messageWithEscapedQuotes = """This is a message that includes \"quoting something\""""

      slackService.escapeQuotes(messageWithQuotes) shouldBe messageWithEscapedQuotes
    }
  }
}

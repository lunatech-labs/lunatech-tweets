package nl.lunatech.tweets

import com.softwaremill.sttp._
import org.slf4j.LoggerFactory

class SlackService(webhookUri: Uri) {
  private val log = LoggerFactory.getLogger("slack-service")

  private val contentType = "application/json"

  def postMessage(sttp: RequestT[Empty, String, Nothing], messageFrom: String, message: String)(
      implicit backend: SttpBackend[Id, Nothing]
  ): Int = {
    val processedMessage = escapeQuotes(message)
    val requestBody      = s"""{"text": "$messageFrom:\n $processedMessage"}"""

    val response = sttp
      .post(webhookUri)
      .contentType(contentType)
      .body(requestBody)
      .send()

    response.code match {
      case 200 => log.debug(s"Message received by Slack $requestBody")
      case _ => log.error(s"Message failed: $requestBody with error: $response")
    }

    response.code
  }

  private[tweets] def escapeQuotes(text: String): String = text.replace("\"", "\\\"")
}

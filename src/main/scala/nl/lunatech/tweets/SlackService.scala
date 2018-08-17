package nl.lunatech.tweets

import com.softwaremill.sttp._
import org.slf4j.LoggerFactory

class SlackService(webhookUri: Uri) {
  private val log = LoggerFactory.getLogger("slack-service")

  private val contentType = "application/json"

  def postMessage(fromName: String, message: String): Unit = {
    implicit val backend = HttpURLConnectionBackend()

    val processedMessage = escapeQuotes(message)
    val requestBody      = s"""{"text": "$fromName:\n $processedMessage"}"""

    val response = sttp
      .post(webhookUri)
      .contentType(contentType)
      .body(requestBody)
      .send()

    response.code match {
      case 200 => log.debug(s"Message received by Slack $requestBody")
      case _ => log.error(s"Message failed: $requestBody with error: $response")
    }
  }

  private def escapeQuotes(text: String): String = text.replace("\"", "\\\"")
}

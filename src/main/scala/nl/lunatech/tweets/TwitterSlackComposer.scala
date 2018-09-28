package nl.lunatech.tweets

import com.danielasfregola.twitter4s.entities.Tweet
import com.softwaremill.sttp._
import org.slf4j.LoggerFactory

class TwitterSlackComposer(twitterService: TwitterService, slackService: SlackService) {
  private val log = LoggerFactory.getLogger("twitter-slack-service")

  private def getUserName(tweet: Tweet): String = tweet.user.map(_.name).getOrElse("")

  log.info("Starting Twitter Service for configured Slack channel")

  val backend = HttpURLConnectionBackend()

  twitterService.getStreamingData {
    case tweet: Tweet =>
      if (!tweet.retweeted) {
        slackService.postMessage(
          sttp,
          getUserName(tweet),
          tweet.text
        )(backend)
      }
  }
}

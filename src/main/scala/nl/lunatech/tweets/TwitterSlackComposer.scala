package nl.lunatech.tweets

import com.danielasfregola.twitter4s.entities.Tweet

import org.slf4j.LoggerFactory

class TwitterSlackComposer(twitterService: TwitterService, slackService: SlackService) {
  private val log = LoggerFactory.getLogger("twitter-slack-service")

  private def getUserName(tweet: Tweet): String = tweet.user.map(_.name).getOrElse("")

  log.info("Starting Twitter Service for configured Slack channel")

  twitterService.getStreamingData {
    case tweet: Tweet =>
      slackService.postMessage(
        getUserName(tweet),
        tweet.text
      )
  }
}

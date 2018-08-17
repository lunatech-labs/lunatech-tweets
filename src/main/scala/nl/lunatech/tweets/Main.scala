package nl.lunatech.tweets

import com.softwaremill.sttp._

object Main extends App with AppConfig {
  val webhookUri = StringContext(slackWebhookUrl).uri(Seq.empty)

  val twitterService = new TwitterService(trackedHashtags)
  val slackService   = new SlackService(webhookUri)

  new TwitterSlackComposer(twitterService, slackService)
}

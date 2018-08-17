package nl.lunatech.tweets

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._

trait AppConfig {
  private val configuration: Config = ConfigFactory.load

  val slackWebhookUrl = configuration.getString("slack.webhook-url")
  val trackedHashtags = configuration.getStringList("slack.tracked-hashtags").asScala.toList

  val host = configuration.getString("akka.host")
  val port = configuration.getInt("akka.port")
}

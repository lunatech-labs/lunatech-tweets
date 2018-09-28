package nl.lunatech.tweets

import com.danielasfregola.twitter4s.TwitterStreamingClient
import com.danielasfregola.twitter4s.entities.streaming.CommonStreamingMessage
import com.danielasfregola.twitter4s.http.clients.streaming.TwitterStream

import org.slf4j.LoggerFactory

import scala.concurrent.Future

class TwitterService(trackedHashtags: Seq[String]) {

  private val log = LoggerFactory.getLogger("twitter-service")

  // Configurations for token and secret are loaded from the application.conf
  private val streamingClient: TwitterStreamingClient = TwitterStreamingClient()

  def getStreamingData: PartialFunction[CommonStreamingMessage, Unit] => Future[TwitterStream] =
    streamingClient.filterStatuses(tracks = trackedHashtags)
}

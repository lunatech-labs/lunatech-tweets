package nl.lunatech.tweets

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.Future

class IsAliveCheck(host: String, port: Int) {
  implicit val system       = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val route =
    path("isAlive") {
      get {
        complete("I'm alive!")
      }
    }

  def bindIsAliveRoute: Future[Http.ServerBinding] = Http().bindAndHandle(route, host, port)
}

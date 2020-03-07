package example

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._
import kamon.http4s.middleware.server.KamonSupport
import kamon.Kamon
import org.http4s.{HttpRoutes, HttpService}
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder

object Hello extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    Kamon.init()

    for {
      exitCode <- BlazeServerBuilder[IO]
        .bindHttp(8080)
        .withHttpApp(
          KamonSupport(GoogleService.service, "localhost", port = 8080).orNotFound
        )
        .serve
        .compile
        .drain
        .as(ExitCode.Success)
    } yield exitCode
  }
}

object GoogleService {
  val service: HttpRoutes[IO] = {
    val dsl = new Http4sDsl[IO] {}
    import dsl._

    HttpRoutes.of[IO] {
      case GET -> Root / "call-google" =>
        Ok()
      case GET -> Root / "test" =>
        Ok()
      case GET -> Root / "dynamic" / IntVar(_) =>
        Ok()

    }
  }
}

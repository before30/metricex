package cc.before30.metricex.akkahttp

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import fr.davit.akka.http.metrics.core.scaladsl.server.HttpMetricsSettings
import fr.davit.akka.http.metrics.prometheus.PrometheusRegistry
import io.prometheus.client.CollectorRegistry
import fr.davit.akka.http.metrics.core.scaladsl.server.HttpMetricsDirectives._
import fr.davit.akka.http.metrics.prometheus.marshalling.PrometheusMarshallers._
import fr.davit.akka.http.metrics.core.scaladsl.server.HttpMetricsRoute._
import io.micrometer.core.instrument.Clock
import io.micrometer.core.instrument.binder.jvm.{ClassLoaderMetrics, JvmGcMetrics, JvmMemoryMetrics, JvmThreadMetrics}
import io.micrometer.core.instrument.binder.system.{FileDescriptorMetrics, ProcessorMetrics, UptimeMetrics}
import io.micrometer.prometheus.{PrometheusConfig, PrometheusMeterRegistry}


/**
 * AkkaHttpApplication
 *
 * @author before30
 * @since 2020/02/26
 */

object AkkaHttpApplication {

  def main(args: Array[String]): Unit = {
    implicit val actorSystem = ActorSystem("System")
    implicit val materializer = ActorMaterializer()

    val settings = HttpMetricsSettings.default
      .withIncludeStatusDimension(true)
      .withIncludePathDimension(true)
    val prometheus: CollectorRegistry = CollectorRegistry.defaultRegistry
    val registry = PrometheusRegistry.apply(settings, prometheus)

    val route =
      path("hello") {
        get {
          complete("Hello from Backend-AkkaHttp-App")
        }
      } ~
        path("metrics") {
          get {
            metrics(registry)
          }
        }

    val prometheusMeterRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT, prometheus, Clock.SYSTEM)
    new ClassLoaderMetrics().bindTo(prometheusMeterRegistry)
    new JvmMemoryMetrics().bindTo(prometheusMeterRegistry)
    new JvmGcMetrics().bindTo(prometheusMeterRegistry)
    new ProcessorMetrics().bindTo(prometheusMeterRegistry)
    new JvmThreadMetrics().bindTo(prometheusMeterRegistry)
    new FileDescriptorMetrics().bindTo(prometheusMeterRegistry)
    new UptimeMetrics().bindTo(prometheusMeterRegistry)

    Http().bindAndHandle(
      route.recordMetrics(registry, settings),
      "0.0.0.0", 8080)
    println("Akka-Http Server started. http://localhost:8080/hello")
  }
}


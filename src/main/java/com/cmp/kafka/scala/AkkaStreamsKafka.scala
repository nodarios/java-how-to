//package com.cmp.kafka.scala
//
//import akka.Done
//import akka.actor.{ActorSystem, CoordinatedShutdown}
//import akka.kafka.scaladsl.Consumer.DrainingControl
//import akka.kafka.{CommitterSettings, ConsumerSettings, ProducerSettings, Subscriptions}
//import akka.kafka.scaladsl.{Committer, Consumer, Producer}
//import akka.stream.ActorMaterializer
//import akka.stream.scaladsl.{Keep, RestartFlow, RestartSink, RestartSource}
//import com.typesafe.scalalogging.StrictLogging
//import com.typesafe.config.{Config, ConfigFactory}
//import org.apache.avro.Schema
//import org.apache.avro.generic._
//import scala.collection.JavaConverters._
//import scala.collection.mutable
//import scala.concurrent.{ExecutionContextExecutor, Future}
//import scala.concurrent.duration._
//
///*
//source code
//https://doc.akka.io/docs/alpakka-kafka/current/
//*/
//
//object AkkaStreamsKafka {
//
//  implicit val system: ActorSystem = ActorSystem("name")
//  implicit val materializer: ActorMaterializer = ActorMaterializer()
//  implicit val executor: ExecutionContextExecutor = system.getDispatcher
//
//  private val config: Config = ConfigFactory.parseResources("application.conf").resolve()
//  private val topic = "topic1"
//  private val targetTopic = "topic2"
//
//  def main(args: Array[String]): Unit = {
//    val consumerSettings: ConsumerSettings[String, String] = ConsumerSettings(system, None, None)
//    val producerSettings: ProducerSettings[String, GenericRecord] = ProducerSettings(system, None, None)
//    val committerSettings = CommitterSettings(system)
//
//    //    val control = Consumer
//    //      .committableSource(consumerSettings, Subscriptions.assignment(new TopicPartition(topic, 0)))
//    //      .log(s"consumer")
//    //      .map { msg =>
//    //        ProducerMessage.single(
//    //          new ProducerRecord[String, GenericRecord](targetTopic, null),
//    //          msg.committableOffset
//    //        )
//    //      }
//    //      .via(Producer.flexiFlow(producerSettings))
//    //      .map(_.passThrough)
//    //      .toMat(Committer.sink(committerSettings))(Keep.both)
//    //      .mapMaterializedValue(DrainingControl.apply)
//    //      .run()
//
//    val controlRef = new AtomicReference[Consumer.Control](Consumer.NoopControl)
//    val futureRef = new AtomicReference[Future[Done]]()
//
//    RestartSource
//      .onFailuresWithBackoff(minBackoff = 1.seconds, maxBackoff = 10.seconds, randomFactor = 0.2) { () =>
//        Consumer
//          .committableSource(consumerSettings, Subscriptions.topics(topic))
//          .mapMaterializedValue(c => controlRef.set(c))
//          .log(s"consumer")
//          .map { msg =>
//            ProducerMessage.single(
//              new ProducerRecord[String, GenericRecord](targetTopic, null),
//              msg.committableOffset
//            )
//          }
//      }
//      .via(
//        RestartFlow.onFailuresWithBackoff(1 second, 10 second, 0.2, 100) {
//          () => Producer.flexiFlow(producerSettings)
//        }
//      )
//      .map(_.passThrough)
//      .to(
//        RestartSink.withBackoff(1 second, 10 second, 0.2, 100) {
//          () => Committer.sink(committerSettings).mapMaterializedValue(f => futureRef.set(f))
//        }
//      )
//      .run()
//
//    val control = DrainingControl.apply((controlRef.get(), futureRef.get()))
//
//    val coordShutdown: CoordinatedShutdown = CoordinatedShutdown(system)
//    coordShutdown.addTask(CoordinatedShutdown.PhaseServiceRequestsDone, "done") { () =>
//      control.drainAndShutdown()
//    }
//    coordShutdown.addJvmShutdownHook {
//      logger.info("asd {}", coordShutdown.shutdownReason())
//    }
//  }
//
//}

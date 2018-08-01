package com.techsophy.training

import com.skafka.example.{SimpleConsumer, SimpleProducer}
import net.manub.embeddedkafka.{EmbeddedKafka, EmbeddedKafkaConfig}
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}
import org.slf4j.LoggerFactory

class ProducerConsumerTest extends FlatSpec with EmbeddedKafka with BeforeAndAfterAll {
  val logger = LoggerFactory.getLogger(this.getClass)
  //val topic = "test"
  val producer = new SimpleProducer
  val consumer = new SimpleConsumer("test" , List("publish_test_topic"))
  implicit val config = EmbeddedKafkaConfig(kafkaPort = 9092, zooKeeperPort = 2181)

  override def beforeAll(): Unit = {
    EmbeddedKafka.start()
  }

  it should "publish synchronously data to kafka" in {
    implicit val serializer = new StringSerializer()
    implicit val deserializer = new StringDeserializer()
    val message = "hello world!"
    val topic = "publish_test_topic"

    producer.send(topic, message)
    val result = consumer.read()
    logger.info(result.toString)
    val expected = Some("publish_test_topic","hello world!")
    assert(result == expected)

  }

  override def afterAll(): Unit = {
    EmbeddedKafka.stop()
  }
}

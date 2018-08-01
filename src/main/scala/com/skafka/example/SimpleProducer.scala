package com.skafka.example
import java.util.Properties

import org.apache.kafka.clients.producer.{ProducerRecord}
import org.apache.kafka.common.errors.WakeupException
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.LoggerFactory

class SimpleProducer extends KafkaProducer {
  val logger = LoggerFactory.getLogger(this.getClass)

  private val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("acks", "all")
  props.put("retries", "5")
  props.put("key.serializer", classOf[StringSerializer].getName)
  props.put("value.serializer", classOf[StringSerializer].getName)

  private val producer = new org.apache.kafka.clients.producer.KafkaProducer[String, String](props)

  def send(topic: String, message: String): Option[Int] = {
    val record = new ProducerRecord[String, String](topic, message)
    try {
      producer.send(record).get()
      logger.info(record.value())
      Some(1)
    }
    catch {
      case wue: WakeupException =>
        logger.debug("Error in reading from kafka ", wue)
        None
      case ex: Exception =>
        logger.error("Error in reading from kafka ", ex)
        None
    }
  }
}
trait KafkaProducer {
  def send(topic : String , message : String) : Any
}

package com.skafka.example
import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords}
import org.apache.kafka.common.errors.WakeupException
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

class SimpleConsumer(groupId : String,topic : List[String])  extends KafkaConsumer {
  val logger = LoggerFactory.getLogger(this.getClass)

  private val timeout = 10

  private val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("group.id", groupId)
  props.put("key.deserializer", classOf[StringDeserializer].getName)
  props.put("value.deserializer", classOf[StringDeserializer].getName)
  props.put("max.poll.records", "1")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  private val consumer = new org.apache.kafka.clients.consumer.KafkaConsumer[String,String](props)

  def read () :Option[(String,String)] = {
    consumer.subscribe(topic)
    logger.info("The subscribed topic is::" + topic)
    val records = consumer.poll(5000)
    for(i <- records){
      logger.info(s"This is :: $i.value()")
    }
    if(records.size >0){
      records.map(record => (record.topic, record.value)).headOption
    }
    else {
      None
    }
  }

}
trait KafkaConsumer {
  def read () : Option[(String,String)]
}

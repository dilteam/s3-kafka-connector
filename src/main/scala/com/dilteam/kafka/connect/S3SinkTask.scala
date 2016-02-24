package com.dilteam.kafka.connect

import java.util

import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.connect.sink.{SinkRecord, SinkTask}

import scala.collection.JavaConverters._

class S3SinkTask extends SinkTask {
  private var writer : Option[S3Writer] = None

  override def stop(): Unit = {}

  override def flush(map: util.Map[TopicPartition, OffsetAndMetadata]): Unit = {}

  override def start(props: util.Map[String, String]): Unit = {
    S3SinkConfig.config.parse(props)
    val sinkConfig = new S3SinkConfig(props)
    writer = Some(S3Writer(connectorConfig = sinkConfig, context = context))
  }

  /**
    * Pass the SinkRecords to the writer for Writing
    * */
  override def put(records: util.Collection[SinkRecord]): Unit = {
    require(writer.nonEmpty, "Writer is not set!")
    writer.map(w=> w.write(records.asScala.toList))
  }

  override def version(): String = ""
}

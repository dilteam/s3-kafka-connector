package com.dilteam.kafka.connect

import java.util

import org.apache.kafka.connect.connector.Task
import org.apache.kafka.connect.errors.ConnectException
import org.apache.kafka.connect.sink.SinkConnector

import scala.collection.JavaConverters._
import scala.util.{Failure, Try}

class S3SinkConnector extends SinkConnector {
  private var configProps : util.Map[String, String] = null

  override def taskClass(): Class[_ <: Task] = classOf[S3SinkTask]

  override def taskConfigs(maxTasks: Int): util.List[util.Map[String, String]] = {
    (1 to maxTasks).map(c => configProps).toList.asJava
  }

  override def stop(): Unit = {}

  override def start(props: util.Map[String, String]): Unit = {
    configProps = props
    Try(new S3SinkConfig(props)) match {
      case Failure(f) => throw new ConnectException("Couldn't start S3Sink due to configuration error.", f)
      case _ =>
    }
  }

  override def version(): String = ""
}

package com.dilteam.kafka.connect

import org.apache.kafka.connect.cli.ConnectStandalone

/**
  * This class makes it easier to start Kafka Connector. It avoids the need to use kafka-run-class.sh to start the
  * connector.
  */
object Driver extends Logging {
  def main(args: Array[String]): Unit = {
    log.info("Starting S3 Kafka Connector....")
    ConnectStandalone.main(args)
  }
}

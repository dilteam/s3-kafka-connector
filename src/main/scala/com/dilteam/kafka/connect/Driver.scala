package com.dilteam.kafka.connect

import org.apache.kafka.connect.cli.ConnectStandalone

/**
  * Only used for debugging via IDE. Otherwise, not needed.
  */
object Driver extends Logging {
  def main(args: Array[String]): Unit = {
    ConnectStandalone.main(args)
  }
}

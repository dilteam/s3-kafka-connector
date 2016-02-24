package com.dilteam.kafka.connect

import java.util

import org.apache.kafka.common.config.ConfigDef.{Importance, Type}
import org.apache.kafka.common.config.{AbstractConfig, ConfigDef}

object S3SinkConfig {
  val ACCESS_KEY = "ACCESS_KEY"
  val SECRET_KEY = "SECRET_KEY"
  val BUCKET_NAME = "BUCKET_NAME"
  val FOLDER_NAME = "FOLDER_NAME"

  // More AWS configarion parameters will be added later.

  val config: ConfigDef = new ConfigDef()
    .define(ACCESS_KEY, Type.STRING, "ACCESS_KEY", Importance.HIGH, "ACCESS_KEY")
    .define(SECRET_KEY, Type.STRING, "SECRET_KEY", Importance.HIGH, "SECRET_KEY")
    .define(BUCKET_NAME, Type.STRING, "BUCKET_NAME", Importance.HIGH, "BUCKET_NAME")
    .define(FOLDER_NAME, Type.STRING, "FOLDER_NAME", Importance.HIGH, "FOLDER_NAME")

}

class S3SinkConfig(props: util.Map[String, String])
  extends AbstractConfig(S3SinkConfig.config, props) {}

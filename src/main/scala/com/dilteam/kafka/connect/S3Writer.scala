package com.dilteam.kafka.connect

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream
import org.apache.kafka.connect.sink.{SinkRecord, SinkTaskContext}

object S3Writer extends Logging {
  def apply(connectorConfig: S3SinkConfig, context: SinkTaskContext) = {
    val accessKey: String = connectorConfig.getString(S3SinkConfig.ACCESS_KEY)
    val secretKey: String = connectorConfig.getString(S3SinkConfig.SECRET_KEY)
    val s3Client: AmazonS3Client = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey))
    new S3Writer(s3Client, context, connectorConfig)
  }
}

class S3Writer (s3Client: AmazonS3Client, context: SinkTaskContext, connectorConfig: S3SinkConfig) extends Logging {
  def write(records : List[SinkRecord]) = {
    if (records.isEmpty) log.info("No records received.") else insert(records)
  }

  def insert(records: List[SinkRecord]) : Boolean = {
    val count = records.size
    val buckName:String = connectorConfig.getString(S3SinkConfig.BUCKET_NAME)
    val folderName:String = connectorConfig.getString(S3SinkConfig.FOLDER_NAME)
    records.map ( r => {
      try {
        // TODO: Allow users to create application specific directory structure.
        val key:String = String.format("%s/%s/%s/offset-%s.txt", folderName, r.topic, r.kafkaPartition.toString,
          r.kafkaOffset.toString)

        // TODO: Allow users to add application specific metadata for the file. Currently set to 'null'.
        s3Client.putObject(buckName, key, new ByteInputStream(r.value.toString.getBytes, r.value.toString.length), null)
      } catch {
        case e:Exception => { log.error("exception in insert: " + e.getMessage); return false}
      }
    })
    true
  }

}

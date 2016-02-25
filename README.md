# s3-kafka-connector
Kafka Connector for AWS S3 file system

This connector copies Kafka messages into Amazon's S3 file system.

Quick Start:

Follow steps 1 thru 5 from the 'Quick Start' section in Kafka's documentation (http://kafka.apache.org/documentation.html#quickstart)

To summarize:

1) Download & install

tar -xzf kafka_2.11-0.9.0.0.tgz
cd kafka_2.11-0.9.0.0


2) Start Zookeeper

bin/zookeeper-server-start.sh config/zookeeper.properties


3) Open a new Terminal window & start the server

bin/kafka-server-start.sh config/server.properties


4) Open a new Terminal windows & Create a topic

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test


5) In the same terminal window, start Producer & send a few messages

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

This is a message<ENTER>

This is another message<ENTER>


6) Open a new Terminal window & confirm that messages were received by Kafka server.

bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning


7) Get the source code for this project in a new Terminal window.

git clone https://github.com/dilteam/s3-kafka-connector

cd s3-kafka-connector


8) Open connect-s3-sink.properties & add S3 related properties.

vi src/main/resources/connect-s3-sink.properties

Set these properties:

ACCESS_KEY=<add access key here...>

SECRET_KEY=<add secret key here...>

BUCKET_NAME=<add bucket name here...>

FOLDER_NAME=<add folder name here...>


9) Compile. (You should have Maven installed.)

mvn clean package


10) Start S3 Kafka Conector.

bin/startConnector.sh



11) Now enter messages in the Producer window. They will show up on S3 at s3://BUCKET_NAME/FOLDER_NAME/


TODO:

1) Allow users to partition data as per their need.

2) Test performance. (Would we get SlowDown errors (503) errors?)

3) Add more S3 configuration parameters.

4) and more....



package example.com.example.consumer

import java.util.Properties

import kafka.consumer.{Consumer, ConsumerConfig}


object Main {

	def main(args: Array[String]) = {
		val props = new Properties()
		props.put("zookeeper.connect", "127.0.0.1:2181")
		props.put("group.id", "1")
//		props.put("metadata.broker.list", "127.0.0.1:9092")
		props.put("serializer.class", "kafka.serializer.StringEncoder")
		//		props.setProperty("partitioner.class", "")
		//		props.setProperty("request.required.acks", "127.0.0.1:49165")

		val consumerConfig = new ConsumerConfig(props)
		val consumerConnector = Consumer.create(consumerConfig)

		val topicMessageStreams = consumerConnector.createMessageStreams(Map("test-topic" -> 100))
		val streams = topicMessageStreams.get("test-topic").get

		streams.foreach(v => println(v))
	}
}

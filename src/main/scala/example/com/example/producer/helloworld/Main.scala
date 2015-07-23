package example.com.example.producer.helloworld

import java.util.Properties

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}


object Main {

	def main(args: Array[String]) = {
		val props = new Properties()
//		props.put("zk.connect", "127.0.0.1:2181")
		props.put("metadata.broker.list", "127.0.0.1:9092")
		props.put("serializer.class", "kafka.serializer.StringEncoder")
		//		props.setProperty("partitioner.class", "")
		//		props.setProperty("request.required.acks", "127.0.0.1:49165")

		val config = new ProducerConfig(props)
		val producer = new Producer[String, String](config)

		val data = new KeyedMessage[String, String]("test-topic", "test-message")
		producer.send(data)
		producer.close()
	}
}

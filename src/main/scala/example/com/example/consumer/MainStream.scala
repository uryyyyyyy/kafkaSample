package example.com.example.consumer

import java.util.Properties

import kafka.consumer._
import kafka.serializer.DefaultDecoder


object MainStream {

	def main(args: Array[String]) = {
		val props = new Properties()
		props.put("zookeeper.connect", "127.0.0.1:2181")
		props.put("group.id", "1")
		//		props.put("metadata.broker.list", "127.0.0.1:9092")
		props.put("serializer.class", "kafka.serializer.StringEncoder")

		val config = new ConsumerConfig(props)
		val consumer = Consumer.create(config)
		val stream = consumer.createMessageStreamsByFilter(new Whitelist("test-topic"), 1, new DefaultDecoder(), new DefaultDecoder()).head

		def read(): Stream[String] = Stream.cons(new String(stream.head.message()), read())

		read().foreach(v => println(v))

	}

}

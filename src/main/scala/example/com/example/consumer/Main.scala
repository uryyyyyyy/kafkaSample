package example.com.example.consumer

import java.util.{Collections, Properties}

import kafka.api._
import kafka.consumer.SimpleConsumer


object Main {

	def main(args: Array[String]) = {
		val props = new Properties()
		props.put("zookeeper.connect", "127.0.0.1:2181")
		props.put("group.id", "1")
		//		props.put("metadata.broker.list", "127.0.0.1:9092")
		props.put("serializer.class", "kafka.serializer.StringEncoder")
		//		props.setProperty("partitioner.class", "")
		//		props.setProperty("request.required.acks", "127.0.0.1:49165")

		val consumer = new SimpleConsumer("127.0.0.1", 9092, 5000, 8192, "clientId")

		val topics = Collections.singletonList("test-topic")

		val fetchRequest: FetchRequest = new FetchRequestBuilder().clientId("clientId")
			.addFetch("test-topic", partition = 0, offset = 2, fetchSize = 100).build()

		val fetchResponse = consumer.fetch(fetchRequest)

		val ss = fetchResponse.data.values.flatMap { topic =>
			topic.messages.map { mao =>
				val payload =  mao.message.payload

				//ugliest part of the code. Thanks to kafka
				val data = Array.fill[Byte](payload.limit)(0)
				payload.get(data)
				new String(data)
			}
		}
		ss.foreach(v => println(v))
	}
}

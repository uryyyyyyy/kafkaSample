package example.com.example.consumer

import kafka.api._
import kafka.consumer._


object Main {

	def main(args: Array[String]) = {

		val consumer = new SimpleConsumer("127.0.0.1", 9092, 5000, 8192, "clientId")

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

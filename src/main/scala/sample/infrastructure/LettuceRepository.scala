package sample.infrastructure

import io.lettuce.core.RedisClient
import io.lettuce.core.api.sync.RedisStringCommands
import sample.util.Loan

import scala.util.control.Exception.allCatch

class LettuceRepository(sync: RedisStringCommands[String, String]) {
  def put(key: String, value: String): Either[Throwable, Unit] =
    for {
      _ <- allCatch either sync.set(key, value)
    } yield ()

  def fetch(key: String): Either[Throwable, Option[String]] =
    for {
      v <- allCatch either Option(sync.get(key))
    } yield v
}

object LettuceRepository {
  def pool(host: String, port: Int): Loan[LettuceClientWrapper] =
    Loan(
      LettuceClientWrapper(RedisClient.create(s"redis://$host:$port").connect())
    )
}

case class LettuceClientWrapper(client: RedisClient) {
  def close(): Unit = client.shutdown()
}

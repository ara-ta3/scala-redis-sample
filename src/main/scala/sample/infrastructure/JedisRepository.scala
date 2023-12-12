package sample.infrastructure

import redis.clients.jedis.JedisPool
import sample.util.Loan

import scala.util.control.Exception.allCatch

class JedisRepository(pool: JedisPool) {
  def put(key: String, value: String): Either[Throwable, Unit] =
    for {
      r <- allCatch either pool.getResource
      _ <- allCatch either r.set(key, value)
    } yield ()

  def fetch(key: String): Either[Throwable, Option[String]] =
    for {
      r <- allCatch either pool.getResource
      v <- allCatch either Option(r.get(key))
    } yield v
}

object JedisRepository {
  def pool(host: String, port: Int): Loan[JedisPool] =
    Loan(new JedisPool(host, port))
}

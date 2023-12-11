package sample

import sample.infrastructure.JedisRepository

object Main {
  def main(args: Array[String]): Unit = {
    val l = JedisRepository.pool
    l.using(p => {
      val r = new JedisRepository(p)
      for {
        v <- r.fetch("a")
        _ = println(v)
        _ <- r.put("a", "xxx")
        v <- r.fetch("a")
        _ = println(v)
      } yield ()
    })
  }

  val redisHost: String = "127.0.0.1"
  val redisPort: Int = 6379
}

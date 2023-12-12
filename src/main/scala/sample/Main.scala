package sample

import sample.infrastructure.JedisRepository

object Main {
  private val redisHost: String = "127.0.0.1"
  private val redisPort: Int = 6379

  def main(args: Array[String]): Unit = {
    JedisRepository
      .pool(redisHost, redisPort)
      .using(p => {
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

}

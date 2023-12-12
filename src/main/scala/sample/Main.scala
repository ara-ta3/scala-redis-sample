package sample

import sample.infrastructure.{JedisRepository, LettuceRepository}

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

    LettuceRepository
      .pool(redisHost, redisPort)
      .using(p => {
        val c = p.connection.sync()
        val r = new LettuceRepository(c)
        for {
          v <- r.fetch("b")
          _ = println(v)
          _ <- r.put("b", "yyy")
          v <- r.fetch("b")
          _ = println(v)
        } yield ()
      })
  }
}

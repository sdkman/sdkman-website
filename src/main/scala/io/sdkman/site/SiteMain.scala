package io.sdkman.site

import ratpack.guice._
import ratpack.server.BaseDir._
import ratpack.server.RatpackServer

object SiteMain extends App {
  RatpackServer.start { server =>
    server
      .serverConfig(conf => conf.baseDir(find()))
      .registry {
        Guice.registry { g =>
          g.bind(classOf[ContextualHandler])
        }
      }
      .handlers { chain =>
        chain
          .path(":context", classOf[ContextualHandler])
          .files(fs => fs.indexFiles("index.html"))
      }
  }
}
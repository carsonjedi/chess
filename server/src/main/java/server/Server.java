package server;

import io.javalin.*;

import java.util.Objects;

public class Server {

    private final Javalin javalin;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Server server = (Server) o;
        return Objects.equals(javalin, server.javalin);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(javalin);
    }

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));
        // Register your endpoints and exception handlers here.

    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}

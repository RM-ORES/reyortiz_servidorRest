package jakarta.listeners;

import config.Configuration;
import dao.common.DBConnectionPool;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener()
public class ListenerConfig {
    Configuration configuration;
    DBConnectionPool connectionPool;

    @Inject
    public ListenerConfig(Configuration configuration, DBConnectionPool connectionPool) {
        this.configuration = configuration;
        this.connectionPool = connectionPool;
    }

    public ListenerConfig() {}

    public void contextInitialized(ServletContextEvent sce) {
        connectionPool.loadPool();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        connectionPool.closePool();
    }
}

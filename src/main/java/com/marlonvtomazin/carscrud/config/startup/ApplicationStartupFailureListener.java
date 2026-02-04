package com.marlonvtomazin.carscrud.config.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupFailureListener
        implements ApplicationListener<ApplicationFailedEvent> {

    private static final Logger log =
            LoggerFactory.getLogger(ApplicationStartupFailureListener.class);

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {

        Throwable ex = event.getException();

        if (isDatabaseConnectionError(ex)) {
            log.error("Failed to connect to DATABASE");
        } else {
            log.error("Error initializing application", ex);
        }
    }

    private boolean isDatabaseConnectionError(Throwable ex) {
        while (ex != null) {
            if (ex instanceof java.net.ConnectException ||
                    ex instanceof java.net.SocketTimeoutException ||
                    ex instanceof java.sql.SQLNonTransientConnectionException) {
                return true;
            }
            ex = ex.getCause();
        }
        return false;
    }

}

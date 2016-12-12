package com.mySampleApplication.server;

//import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.core.server.StackTraceDeobfuscator;
import com.google.gwt.logging.server.RemoteLoggingServiceUtil;
import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by cdc89 on 12.12.2016.
 */
public class MyRemoteLoggingServlet extends RemoteServiceServlet implements RemoteLoggingService {

    private static final Logger logger = LogManager.getLogger(MyRemoteLoggingServlet.class.getName());
    private static final long serialVersionUID = 1L;

    // No deobfuscator by default
    private StackTraceDeobfuscator deobfuscator = null;
    private String loggerNameOverride = null;

    @Override
    public String logOnServer(LogRecord record) {
        Level level = record.getLevel();
        String message = record.getMessage();
        if (Level.INFO.equals(level)) {
            logger.info(message);
        } else if (Level.SEVERE.equals(level)) {
            logger.error(message);
        } else if (Level.WARNING.equals(level)) {
            logger.warn(message);
        } else if (Level.FINE.equals(level)) {
            logger.debug(message);
        }
        String strongName = getPermutationStrongName();
        return null;
    }

    /**
     * By default, messages are logged to a logger that has the same name as
     * the logger that created them on the client. If you want to log all messages
     * from the client to a logger with another name, you can set the override
     * using this method.
     */
    public void setLoggerNameOverride(String override) {
        loggerNameOverride = override;
    }

    /**
     * By default, this service does not do any deobfuscation. In order to do
     * server-side deobfuscation, you must copy the symbolMaps files to a
     * directory visible to the server and set the directory using this method.
     */
    public void setSymbolMapsDirectory(String symbolMapsDir) {
        deobfuscator = StackTraceDeobfuscator.fromFileSystem(symbolMapsDir);
    }
}

package ro.shiftleft.apiTestingDemo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingConfigurator {

    public static Logger configureLogger(Class<?> clazz) throws Exception {
        // Generate a unique folder name for the test run
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String testRunFolder = "TestRun_" + dateTime;

        // Create the directory structure for logs
        Path logDir = Paths.get("target/logs", testRunFolder);
        Files.createDirectories(logDir);

        // Set the log file path
        String logFileName = logDir.resolve(clazz.getSimpleName() + ".log").toString();

        // Configure Log4j
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        PatternLayout layout = PatternLayout.newBuilder()
                .withPattern("%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n")
                .build();

        // Configure FileAppender
        FileAppender fileAppender = FileAppender.newBuilder()
                .setName("DynamicFileAppender")
                .withFileName(logFileName)
                .withAppend(false)
                .setImmediateFlush(true)
                .setLayout(layout)
                .build();
        fileAppender.start();

        // Add appenders to the root logger
        config.getRootLogger().addAppender(fileAppender, null, null);
        context.updateLoggers();

        // Return the logger for the given class
        return LogManager.getLogger(clazz);
    }
}
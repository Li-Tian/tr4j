package neo.log.notr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class TR {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static StackTraceElement getStackTraceElement() {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        return stackTraces[3];
    }

    private static void output(Level level, DebugData dd, String format, Object... args) {
        String dbgStr = String.format("[%d]%s(%d)%s", dd.threadId, dd.fileName, dd.lineNumber, dd.methodName);
        String logStr = String.format(format, args);
        String finalStr = String.format("%s : %s", dbgStr, logStr);
        switch (level) {
            case ERROR:
                logger.error(finalStr);
                break;
            case WARN:
                logger.warn(finalStr);
                break;
            case INFO:
                logger.info(finalStr);
                break;
            case DEBUG:
                logger.debug(finalStr);
                break;
            case TRACE:
            default:
                logger.trace(finalStr);
                break;
        }
    }

    public static void enter() {
    }

    public static void exit() {
    }

    public static <T> T exit(T result) {
        return result;
    }

    public static void debug(String format, Object... args) {
    }

    public static void debug() {
    }

    public static void debug(Object obj) {
    }

    public static void info(String format, Object... args) {
        logger.info(format, args);
    }

    public static void warn(String format, Object... args) {
        if (logger.isWarnEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.WARN, dd, format, args);
        }
    }

    public static void warn(Throwable t) {
        if (logger.isWarnEnabled()) {
            logger.warn(t.getMessage(), t);
        }
    }

    public static void error(String format, Object... args) {
        if (logger.isErrorEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.ERROR, dd, format, args);
        }
    }

    public static void error(Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(t.getMessage(), t);
        }
    }

    public static void fixMe(String format, Object... args) {
        if (logger.isWarnEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.WARN, dd, "FIX ME!!! : " + format, args);
        }
    }
}

class DebugData {
    String fileName;
    int lineNumber;
    String className;
    String methodName;
    long threadId;
    String threadName;

    DebugData(StackTraceElement ste) {
        fileName = ste.getFileName();
        lineNumber = ste.getLineNumber();
        className = ste.getClassName();
        methodName = ste.getMethodName();
        threadId = Thread.currentThread().getId();
        threadName = Thread.currentThread().getName();
    }
}

enum Level {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR
}
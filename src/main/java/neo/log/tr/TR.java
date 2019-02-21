package neo.log.tr;

import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class TR {

    private static final org.slf4j.Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static StackTraceElement getStackTraceElement() {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        return stackTraces[3];
    }

    private static ThreadLocal<IndentContext> indentContext =
            ThreadLocal.withInitial(IndentContext::new);

    private static void output(Level level, DebugData dd, String format, Object... args) {
        IndentContext iu = indentContext.get();
        if ("<".equals(format)) {
            if (!iu.decreaseIndent()) {
                logger.warn(String.format("[%d]TR log indent mismatch", dd.threadId));
            }
        }
        String dbgStr = String.format("[%d]%s%s(%d)%s",
                dd.threadId, iu.GetIndent(), dd.fileName, dd.lineNumber, dd.methodName);
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
        if (">".equals(format)) {
            iu.increaseIndent();
        }
    }

    public static void enter() {
        if (logger.isTraceEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.TRACE, dd, ">");
        }
    }

    public static void exit() {
        if (logger.isTraceEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.TRACE, dd, "<");
        }
    }

    public static <T> T exit(T result) {
        if (logger.isTraceEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.TRACE, dd, "return %s",
                    result == null ? "null" : result.toString());
            output(Level.TRACE, dd, "<");
        }
        return result;
    }

    public static void debug(String format, Object... args) {
        if (logger.isDebugEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.DEBUG, dd, format, args);
        }
    }

    public static void debug() {
        if (logger.isDebugEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.DEBUG, dd, "");
        }
    }

    public static void debug(Object obj) {
        if (logger.isDebugEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.DEBUG, dd, "%s", obj == null ? "null" : obj.toString());
        }
    }

    public static void info(String format, Object... args) {
        if (logger.isInfoEnabled()) {
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.INFO, dd, format, args);
        }
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
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.WARN, dd, t.getMessage());
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
            StackTraceElement ste = getStackTraceElement();
            DebugData dd = new DebugData(ste);
            output(Level.ERROR, dd, t.getMessage());
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

class IndentContext {
    private int indent;

    IndentContext() {
        indent = 1;
    }

    public synchronized void increaseIndent() {
        indent += 2;
    }

    public synchronized boolean decreaseIndent() {
        if (indent > 1) {
            indent -= 2;
            return true;
        } else {
            return false;
        }
    }

    public synchronized String GetIndent() {
        char[] chars = new char[indent];
        Arrays.fill(chars, ' ');
        return new String(chars);
    }

}

enum Level {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR
}
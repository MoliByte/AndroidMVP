package utils;

import android.util.Log;

/**
 *
 * @author zhupei
 *
 */
public class EasyLog {
    private EasyLog() {
    }

    private static String generateTag() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[3];
        String methodName = stackTraceElement.getMethodName() ;
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();

        return fileName + "#"+methodName+"#" + lineNumber;
    }

    private static String generateTag(Throwable throwable) {
        StackTraceElement stackTraceElement = throwable.getStackTrace()[3];
        String methodName = stackTraceElement.getMethodName() ;
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();

        return fileName + "#"+methodName+"#" + lineNumber;
    }

    private static String generateMsg(Throwable t, String format, Object... formatArgs) {
        String msg = formatArgs.length == 0 ? format : String.format(format, formatArgs);

        if (t != null) {
            msg = msg + '\n' + Log.getStackTraceString(t);
        }

        return msg;
    }

    private static void log(int logLevel, Throwable t, String format, Object... formatArgs) {
        Log.println(logLevel, generateTag(), generateMsg(t, format, formatArgs));
    }

    private static void logException(int logLevel, Throwable t, String format, Object... formatArgs) {
        Log.println(logLevel, generateTag(t), generateMsg(t, format, formatArgs));
    }

    @SuppressWarnings("unused")
    public static void v(Throwable t, String format, Object... formatArgs) {
        log(Log.VERBOSE, t, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void v(String format, Object... formatArgs) {
        log(Log.VERBOSE, null, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void d(Throwable t, String format, Object... formatArgs) {
        log(Log.DEBUG, t, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void d(String format, Object... formatArgs) {
        log(Log.DEBUG, null, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void i(Throwable t, String format, Object... formatArgs) {
        log(Log.INFO, t, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void i(String format, Object... formatArgs) {
        log(Log.INFO, null, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void w(Throwable t, String format, Object... formatArgs) {
        log(Log.WARN, t, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void w(String format, Object... formatArgs) {
        log(Log.WARN, null, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void e(Throwable t, String format, Object... formatArgs) {
        log(Log.ERROR, t, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void e(String format, Object... formatArgs) {
        log(Log.ERROR, null, format, formatArgs);
    }

    @SuppressWarnings("unused")
    public static void e(String format) {
        log(Log.ERROR, null, format);
    }

    public static void e(Exception exception){
        Throwable throwable = exception.fillInStackTrace()  ;
        logException(Log.ERROR,throwable,"");
        if(null != throwable){
        }
    }
}

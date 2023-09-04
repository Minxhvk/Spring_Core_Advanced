package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace trace;


    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback) {

        TraceStatus traceStatus = null;

        try {
            traceStatus = trace.begin(message);

            T result = callback.call();

            trace.end(traceStatus);
            return result;
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }

    }
}

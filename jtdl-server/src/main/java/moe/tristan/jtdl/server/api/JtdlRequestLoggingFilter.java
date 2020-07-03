package moe.tristan.jtdl.server.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
public class JtdlRequestLoggingFilter extends AbstractRequestLoggingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JtdlRequestLoggingFilter.class);

    private static final ThreadLocal<Long> START_PROCESSING_TIME = new ThreadLocal<>();

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        START_PROCESSING_TIME.set(System.currentTimeMillis());
        LOGGER.info("{} {}", request.getMethod(), request.getRequestURL());
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        long start = START_PROCESSING_TIME.get();
        long end = System.currentTimeMillis();

        LOGGER.info("{} {} ({} ms)", request.getMethod(), request.getRequestURL(), end - start);
    }

}

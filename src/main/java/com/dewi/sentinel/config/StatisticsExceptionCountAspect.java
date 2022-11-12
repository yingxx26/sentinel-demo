package com.dewi.sentinel.config;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.BaseWebMvcConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class StatisticsExceptionCountAspect {

    @Autowired
    @Lazy
    private BaseWebMvcConfig baseWebMvcConfig;

    @Pointcut("execution(* com.dewi.sentinel.controller..*.*(..))")
    public void pointcut() {

    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterAfterThrowing(Throwable ex) {
        log.info("statisticsExceptionCount...");
        traceException(ex);
    }

    /**
     * 统计异常
     *
     * @param ex
     */
    private void traceException(Throwable ex) {
        Entry entry = getEntryInRequest();
        if (entry != null) {
            Tracer.traceEntry(ex, entry);
        }
    }

    protected Entry getEntryInRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        Object entryObject = request.getAttribute(baseWebMvcConfig.getRequestAttributeName());
        return entryObject == null ? null : (Entry) entryObject;
    }
}
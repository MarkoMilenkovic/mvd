package rs.mvd.filters;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import rs.mvd.annotations.Reporting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class ReportingInterceptor implements HandlerInterceptor {

    private long startTime;
    private static final Logger LOGGER = Logger.getLogger(Reporting.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Object bean = handlerMethod.getBean();

        if (!bean.getClass().isAnnotationPresent(Reporting.class)){
            return;
        }
        long elapsedTime = System.currentTimeMillis() - startTime;

        ReportingModel rm = new ReportingModel();
        rm.setPath(httpServletRequest.getRequestURI());
        rm.setElapsedTime(elapsedTime);
        rm.setStatusCode(httpServletResponse.getStatus());
        rm.setEndpoint(bean.getClass().getName());
        rm.setMethodName(handlerMethod.getMethod().getName());
        rm.setParameters(httpServletRequest.getParameterMap());
        LOGGER.info("REPORTING: " + rm);

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

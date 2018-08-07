package rs.mvd.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import rs.mvd.exceptions.BadRequestException;
import rs.mvd.repository.TenantRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {

    private static final String X_TENANT_NAME = "x-tenant-name";

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        String tenantName = httpServletRequest.getHeader(X_TENANT_NAME);
        validateTenant(tenantName);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

    private void validateTenant(String tenantName) {
        if (tenantName == null || tenantName.isEmpty() || !tenantRepository.getByName(tenantName).isPresent()) {
            throw new BadRequestException("Tenant does not exists!");
        }
        tenantRepository.setSchema(tenantName);
    }
}

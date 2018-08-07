package rs.mvd.filters;

import org.springframework.beans.factory.annotation.Autowired;
import rs.mvd.exceptions.BadRequestException;
import rs.mvd.repository.TenantRepository;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class Interceptor implements ContainerRequestFilter {

    private static final String X_TENANT_NAME = "x-tenant-name";

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        if (path.startsWith("private")) {
            String tenantName = requestContext.getHeaderString(X_TENANT_NAME);
            validateTenant(tenantName);
        }
    }

    private void validateTenant(String tenantName) {
        if (tenantName == null || tenantName.isEmpty() || !tenantRepository.getByName(tenantName).isPresent()) {
            throw new BadRequestException("Tenant does not exists!");
        }
        tenantRepository.setSchema(tenantName);
    }
}

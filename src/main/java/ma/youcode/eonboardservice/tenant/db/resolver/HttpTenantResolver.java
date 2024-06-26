package ma.youcode.eonboardservice.tenant.db.resolver;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

@Component
@Slf4j
public class HttpTenantResolver implements TenantResolver<HttpServletRequest> {
    @Override
    public String resolveTenantId(HttpServletRequest request) {
        String forwardedHeader = request.getHeader("forwarded");
        String quoted = "host=\"";
        //return (((forwardedHeader.split(forwardedHeader.contains(quoted) ? quoted : "host=")[1]).split(";"))[0]).split("\\.")[0];
        request.getHeaderNames().asIterator().forEachRemaining(header -> {
            log.info("Header: {}", header);
        });
        return request.getHeader("x-tenant-id") == null ? "myOrg" : request.getHeader("x-tenant-id");
    }
}

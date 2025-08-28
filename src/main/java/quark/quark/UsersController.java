package quark.quark;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.NoCache;
import io.quarkus.security.identity.SecurityIdentity;

@Path("/api/users")
@Authenticated
public class UsersController {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/me")
    @NoCache
    @Produces(MediaType.TEXT_PLAIN)
    public String me() {
        return securityIdentity.getPrincipal().getName();
    }
}

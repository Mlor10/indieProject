package project.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/services")

//The java class declares root resource and provider classes
public class ServiceApplication extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet serviceSet = new HashSet<Class<?>>();
        serviceSet.add(SearchProfile.class);
        return serviceSet;
    }
}

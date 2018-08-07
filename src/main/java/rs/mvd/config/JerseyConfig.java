package rs.mvd.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return true;
            }
        };
        provider.addIncludeFilter(new AnnotationTypeFilter(Path.class));
        provider.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
        provider.findCandidateComponents("rs.mvd").forEach(beanDefinition -> {
            try {
                register(Class.forName(beanDefinition.getBeanClassName()));
            } catch (ClassNotFoundException e) {
                Logger.getLogger(JerseyConfig.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        });

    }
}

package service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestService extends Application {

	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(CategoryService.class);
        classes.add(LanguageService.class);
        classes.add(UserService.class);
        classes.add(EBookService.class);
        classes.add(SearchService.class);
        classes.add(AuthService.class);
        return classes;
    }    
}

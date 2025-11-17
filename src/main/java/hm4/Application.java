package hm4;

import hm4.model.User;
import hm4.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);

        doLogic(context);
    }

    private static void doLogic(ApplicationContext context) {
        UserService userService = context.getBean(UserService.class);

        userService.cleanup();


        User dima = userService.create("Дима");
        User dimon = userService.create("Димон");
        User dimonych = userService.create("Димоныч");

        userService.findById(dima.getId());
        userService.deleteById(dimon.getId());

        dimonych.setName("Димаааа");
        userService.update(dimonych);
    }
}

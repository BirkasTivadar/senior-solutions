package employees;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelloService {

    public String sayHello(){
        return "Hello Bibi Spring Boot (service) " + LocalDateTime.now();
    }
}
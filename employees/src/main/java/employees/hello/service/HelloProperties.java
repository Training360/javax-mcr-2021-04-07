package employees.hello.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "employees")
@Data
public class HelloProperties {

    private String message;
}

package employees.employees.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "employees")
@Data
@Validated
public class AddressesProperties {

    @NotBlank
    private String addressesUrl;
}

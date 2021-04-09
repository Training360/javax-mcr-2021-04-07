package employees.employees.gateway;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@Gateway
@EnableConfigurationProperties(AddressesProperties.class)
public class AddressesGateway {

    private final RestTemplate restTemplate;

    private final AddressesProperties addressesProperties;

    public AddressesGateway(RestTemplateBuilder builder, AddressesProperties addressesProperties) {
        restTemplate = builder.build();
        this.addressesProperties = addressesProperties;
    }

    public AddressDto getAddressByName(String name) {
        return restTemplate.getForObject(addressesProperties.getAddressesUrl(),
                AddressDto.class,
                name);
    }
}

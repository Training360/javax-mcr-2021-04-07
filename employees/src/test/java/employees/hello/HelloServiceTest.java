package employees.hello;

import employees.hello.service.HelloProperties;
import employees.hello.service.HelloService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    @Test
    void sayHello() {
        // BDD behavior driven development
        // given - when - then

        // Given
        HelloProperties properties = new HelloProperties();
        properties.setMessage("Test Message");
        HelloService service = new HelloService(properties);

        // When
        String result = service.sayHello();

        // Then = assert
        // JUnit
        // assertTrue(result.startsWith("Hello Spring Boot"));

        // AssertJ
        assertThat(result).startsWith("Test Message");
    }

    @Test
    void sayHelloShort() {
        HelloProperties properties = new HelloProperties();
        properties.setMessage("Test Message");
        assertThat(new HelloService(properties).sayHello()).startsWith("Test Message");
    }
}
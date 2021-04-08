package employees.hello;

import employees.hello.service.HelloService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    @Test
    void sayHello() {
        // BDD behavior driven development
        // given - when - then

        // Given
        HelloService service = new HelloService();

        // When
        String result = service.sayHello();

        // Then = assert
        // JUnit
        // assertTrue(result.startsWith("Hello Spring Boot"));

        // AssertJ
        assertThat(result).startsWith("Hello Spring Boot");
    }

    @Test
    void sayHelloShort() {
        assertThat(new HelloService().sayHello()).startsWith("Hello Spring Boot");
    }
}
package employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    @Test
    void sayHello() {
        // BDD behavior driven development
        // given - when - then

        // Given
        var service = new HelloService();

        // When
        var result = service.sayHello();

        // Then = assert
        // JUnit
        // assertTrue(result.startsWith("Hello Spring Bootcccc"));

        // AssertJ
        assertThat(result).startsWith("Hello Spring Bootxxx");
    }
}
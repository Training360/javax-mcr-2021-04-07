package employees.hello;

import employees.hello.controller.HelloController;
import employees.hello.service.HelloService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloControllerTest {

    @Mock
    HelloService helloService;

    @InjectMocks
    HelloController helloController;

    @Test
    void sayHello() {
//        HelloService helloService = Mockito.mock(HelloService.class);

        when(helloService.sayHello()).thenReturn("Test Text");

//        HelloController helloController = new HelloController(helloService);

        String result = helloController.sayHello();
        verify(helloService, times(1)).sayHello();

        assertEquals("*** Test Text", result);
    }

    @Test
    void testMockito() {
        System.out.println(helloService.getClass().getName());
        System.out.println(helloService instanceof HelloService);
        when(helloService.sayHello()).thenReturn("Test");
        System.out.println(helloService.sayHello());
    }
}
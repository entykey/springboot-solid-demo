// Fix: Mock the service (recommended for controller testing)
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // ✅ Mocked service injected into controller

    @Test
    void shouldReturnEmptyList() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/users"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnListWithUsers() throws Exception {
        User user = new User("Alice", "alice@mail.com");
        user.setId(1L);
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/users"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Alice"))
               .andExpect(jsonPath("$[0].email").value("alice@mail.com"));
    }

    @Test
    void shouldReturnUserById() throws Exception {
        User user = new User("Bob", "bob@mail.com");
        user.setId(1L);
        when(userService.getUser(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Bob"))
               .andExpect(jsonPath("$.email").value("bob@mail.com"));
    }

    // No raw exceptions, real 404 Not Found behavior
    @Test
    void shouldReturnNotFoundWhenUserMissing() throws Exception {
        when(userService.getUser(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateUser() throws Exception {
        User user = new User("Charlie", "charlie@mail.com");
        user.setId(1L);
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Charlie\",\"email\":\"charlie@mail.com\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Charlie"))
               .andExpect(jsonPath("$.email").value("charlie@mail.com"));
    }


    // Trong JUnit 5, mỗi test case là độc lập 100%
    // 👉 Mỗi test chạy xong, framework reset lại toàn bộ context mock, trừ khi bạn cấu hình đặc biệt (VD: @TestInstance(Lifecycle.PER_CLASS) hoặc @DirtiesContext).

    // Cụ thể:
    // * @BeforeEach chạy trước mỗi test, tạo lại MockMvc và các mock.
    // * Mockito mock không lưu dữ liệu giữa các test — chúng chỉ trả về những gì bạn when(...).thenReturn(...) trong phạm vi test hiện tại.
    
    
    // Test báo sai do nhận được 404 thay vì 204
    // @Test
    // void shouldDeleteUser() throws Exception {
    //     doNothing().when(userService).deleteUser(1L);

    //     mockMvc.perform(delete("/users/1"))
    //         .andExpect(status().isNoContent()); // ✅ 204 thay vì 200

    //     verify(userService, times(1)).deleteUser(1L);
    // }

    // test mock giả định chuẩn
    @Test
    void shouldDeleteUser() throws Exception {
        // ✅ Giả lập là user tồn tại trước (mock hàm getUser(1L))
        when(userService.getUser(1L)).thenReturn(Optional.of(new User("John", "john@example.com")));

        // ✅ Giả lập hành động xóa không lỗi
        doNothing().when(userService).deleteUser(1L);

        // ✅ Gọi API và kỳ vọng HTTP 204
        mockMvc.perform(delete("/users/1"))
            .andExpect(status().isNoContent());

        // ✅ Kiểm tra hàm xóa được gọi đúng
        verify(userService, times(1)).deleteUser(1L);
    }
}





/*
Fixed this by Mock the service (recommended for controller testing)
[INFO] Running com.example.demo.controller.UserControllerTest
00:37:18.871 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.example.demo.controller.UserControllerTest]: UserControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
00:37:19.085 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.example.demo.DemoApplication for test class com.example.demo.controller.UserControllerTest
2025-11-07T00:37:19.929+07:00  INFO 5884 --- [           main] c.e.demo.controller.UserControllerTest   : Starting UserControllerTest using Java 24.0.2 with PID 5884 (started by Windows in D:\java-learning\springboot-solid-demo)
2025-11-07T00:37:19.934+07:00 DEBUG 5884 --- [           main] c.e.demo.controller.UserControllerTest   : Running with Spring Boot v3.3.3, Spring v6.1.12
2025-11-07T00:37:19.935+07:00  INFO 5884 --- [           main] c.e.demo.controller.UserControllerTest   : No active profile set, falling back to 1 default profile: "default"
2025-11-07T00:37:20.958+07:00 ERROR 5884 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************
*/

// package com.example.demo.controller;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(UserController.class)
// class UserControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Test
//     void shouldReturnEmptyList() throws Exception {
//         mockMvc.perform(get("/users"))
//                .andExpect(status().isOk());
//     }
// }




// package com.example.demo.controller;

// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.http.ResponseEntity;

// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// class UserControllerTest {

//     private UserService mockService;
//     private UserController controller;

//     @BeforeEach
//     void setUp() {
//         mockService = Mockito.mock(UserService.class);
//         controller = new UserController(mockService);
//     }

//     @Test
//     void testGetAllUsers() {
//         when(mockService.getAllUsers()).thenReturn(List.of(new User()));
//         List<User> result = controller.getAllUsers();
//         assertEquals(1, result.size());
//         verify(mockService, times(1)).getAllUsers();
//     }

//     @Test
//     void testGetUserByIdFound() {
//         User user = new User();
//         user.setId(1L);
//         user.setName("Alice");

//         when(mockService.getUser(1L)).thenReturn(Optional.of(user));

//         ResponseEntity<User> response = controller.getUserById(1L);
//         assertTrue(response.getStatusCode().is2xxSuccessful());
//         assertEquals("Alice", response.getBody().getName());
//     }

//     @Test
//     void testGetUserByIdNotFound() {
//         when(mockService.getUser(1L)).thenReturn(Optional.empty());
//         ResponseEntity<User> response = controller.getUserById(1L);
//         assertTrue(response.getStatusCode().is4xxClientError());
//     }
// }

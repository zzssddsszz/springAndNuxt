package kr.co.iamdesigner.web.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.iamdesigner.domain.application.UserService;
import kr.co.iamdesigner.domain.application.impl.UserServiceImpl;
import kr.co.iamdesigner.domain.model.user.RegistrationManagement;
import kr.co.iamdesigner.domain.model.user.User;
import kr.co.iamdesigner.domain.model.user.UsernameExistsException;
import kr.co.iamdesigner.utils.JsonUtils;
import kr.co.iamdesigner.web.payload.RegistrationPayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AuthenticationApiTest {

    private MockMvc mvc;
    @Autowired WebApplicationContext context;
    @Autowired UserServiceImpl userService;


    @Test
    void login() throws Exception {
        mvc= MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        RegistrationPayload payload = new RegistrationPayload();
        payload.setUsername("testName");
        payload.setEmailAddress("test@test.com");
        payload.setPassword("MyPassword!@");

        userService.register(payload.toCommand());
        User testName = userService.findByUsername("testName");

        assertEquals(testName.getUsername(),payload.getUsername());
        assertNotNull(testName.getId());


        mvc.perform(post("/api/authentications")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testName\",\"password\":\"MyPassword!@\"}")
        ).andExpect(status().isOk());

    }

}
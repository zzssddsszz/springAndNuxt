package kr.co.iamdesigner.web.apis;

import kr.co.iamdesigner.config.SecurityConfiguration;
import kr.co.iamdesigner.domain.application.PendantService;
import kr.co.iamdesigner.domain.application.UserService;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.application.impl.UserServiceImpl;
import kr.co.iamdesigner.domain.common.mail.MailManager;
import kr.co.iamdesigner.domain.model.user.SimpleUser;
import kr.co.iamdesigner.domain.model.user.User;
import kr.co.iamdesigner.domain.model.user.UserRegistrationManagement;
import kr.co.iamdesigner.domain.model.user.UserRepository;
import kr.co.iamdesigner.factory.RegisterCommandFactory;
import kr.co.iamdesigner.factory.WithUser;
import kr.co.iamdesigner.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Slf4j
class AdminPendantApiControllerTest {

    @Autowired
    private MockMvc mvc;


    @Autowired private UserService userService;

    @MockBean
    private PendantService service;

    @Test
    @SneakyThrows
    @WithUser(value = "test", admin = true)
    void register_blankPayload_shouldFailAndReturn400() {
        mvc.perform(getAdminPendantPost())
                .andExpect(status().is(400));
    }

    @Test
    @SneakyThrows
    @WithUser(value = "test", admin = false)
    void register_payloadWithNotAdmin_shouldFailAndReturn403() {
        mvc.perform(getAdminPendantPost())
                .andExpect(status().is(403));
    }

    @Test
    @SneakyThrows
    @WithUser(value = "test", admin = true)
    void register_existedPendantPayload_shouldFailAndReturn400() {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();

        doNothing().when(service)
                .register(command);

        mvc.perform(getAdminPendantPost()
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(command)))
                .andExpect(status().is(201));
    }


    private MockHttpServletRequestBuilder getAdminPendantPost() {
        return post("/admin/api/pendant");
    }


}
package kr.co.iamdesigner.web.apis;

import kr.co.iamdesigner.domain.application.PendantService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {PendantApiController.class})
@WithMockUser(username = "admin", authorities = {"USER","ADMIN"})
class PendantApiControllerTest {

    @Autowired private MockMvc mvc;

    @MockBean private PendantService service;

    @Test
    @SneakyThrows
    void register_blankPayload_shouldFailAndReturn400(){
        mvc.perform(post("/api/pendant"))
                .andExpect(status().is(400));

    }

    @Test
    @SneakyThrows
    void register_existedPendantPayload_shouldFailAndReturn400(){

        mvc.perform(post("/api/pendant"))
                .andExpect(status().is(400));

    }




}
package kr.co.iamdesigner.web.apis;

import kr.co.iamdesigner.domain.application.ProductService;
import kr.co.iamdesigner.factory.WithUser;
import kr.co.iamdesigner.utils.JsonUtils;
import kr.co.iamdesigner.web.payload.ProductRegistrationPayload;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {ProductRegistrationApiController.class})
class ProductRegistrationApiControllerTest {

    @Autowired private MockMvc mvc;

    @MockBean private ProductService service;

    @Test
    @WithUser("admin")
    void register_listOption_shouldListAndReturn201() throws Exception {
        ProductRegistrationPayload payload = new ProductRegistrationPayload();
       /* payload.

        mvc.perform(post("/admin/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(c))


                .andExpect(status().is(201));*/
    }

}
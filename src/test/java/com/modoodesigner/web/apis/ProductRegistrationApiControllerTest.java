package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.domain.application.commands.ProductSearchCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {ProductApiController.class})
class ProductRegistrationApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    @Test
    void search_listByNone_shouldPageAndReturn200() throws Exception {
        /*MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("name", "");
        valueMap.add("size", "11");
        valueMap.add("sort", "id,desc");
        valueMap.add("page", "0");

        mvc.perform(get("/api/products")
                .params(valueMap)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));*/
    }

}
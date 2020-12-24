package kr.co.iamdesigner.web.apis.authenticate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;

import java.io.IOException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationFilterTest {

    @MockBean
    private AuthenticationManager authenticationManagerMock;

    @Test
    void attemptAuthentication_emptyRequestBody_shouldFail() {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/authentications");
        AuthenticationFilter filter = new AuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerMock);
        assertThrows(InsufficientAuthenticationException.class, () ->
                filter.attemptAuthentication(request, new MockHttpServletResponse()));

    }

}
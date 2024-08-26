package org.scoula.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.scoula.security.dto.LoginDTO;
import org.scoula.security.handelr.LoginFailureHandler;
import org.scoula.security.handelr.LoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public JwtUsernamePasswordAuthenticationFilter(
        AuthenticationManager authenticationManager,
        LoginSuccessHandler loginSuccessHandler,
                LoginFailureHandler loginFailureHandler){
        super(authenticationManager);

        setFilterProcessesUrl("/api/auth/login");
        setAuthenticationSuccessHandler(loginSuccessHandler);
        setAuthenticationFailureHandler(loginFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
        LoginDTO login = LoginDTO.of(request);

        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword());

        return getAuthenticationManager().authenticate(authenticationToken);

    }
}

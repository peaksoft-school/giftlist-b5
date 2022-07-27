package kg.giftlist.giftlist.security;


import kg.giftlist.giftlist.models.User;
import kg.giftlist.giftlist.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenVerifierFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepo;

    public TokenVerifierFilter(JwtUtils jwtUtils,
                               UserRepository userRepo) {
        this.jwtUtils = jwtUtils;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);

        System.out.println("token = " + token);

        jwtUtils.isValidToken(token);

        String email = jwtUtils.getEmailFromJwt(token);

        User user = userRepo.findByEmail(email)
                .orElse(null);

        assert user != null;
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        null,
                        user.getAuthorities()
                )
        );

        filterChain.doFilter(request, response);
    }
}

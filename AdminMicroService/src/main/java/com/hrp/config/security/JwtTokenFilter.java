package com.hrp.config.security;


import com.hrp.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {
    // 403 hatası burada olabilir

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private JwtUserDetail jwtUserDetail;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeaderBearerToken = request.getHeader("Authorization");
        if(authHeaderBearerToken!=null && authHeaderBearerToken.startsWith("Bearer ")
                &&  SecurityContextHolder.getContext().getAuthentication() == null
        ){
            /**
             * Header içinden Bearer çıkartılarak Token bilgisi alınır.
             */
            String token = authHeaderBearerToken.substring(7);
            /**
             * token doğrulama işlemi yapılarak geçerliliği kontrol edilir.
             */
            Optional<Long> authid =  jwtTokenManager.validToken(token);
            /**
             * Eğer token geçerli değil ise istisna fırlatılır.
             */
            if(authid.isEmpty()) return;
            /**
             * Bu kısımda kullanıcı için geçerli isteğine ait bir Session açmamız gerekir. bunu
             * yapapilmek için Spring bizden kendinin takip edebileceği bir kimlik kartı talep eder
             * bunu yapmak için, UserDetail nesnesi talep eder.
             */
            UserDetails userDetails = jwtUserDetail.getUserDetailObject(authid.get());
            /**
             * İçeride işlem yapacak olan kullanıcıya ait bilgileri ve yetkileri içeren
             * özel bir token oluşturuyorsunuz.
             */
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities());
            /**
             * Bu kısımda Spring tarafından istenilen bir kimlik kartı eklemelisiniz.
             */
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }


        filterChain.doFilter(request,response);
    }

}

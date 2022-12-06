package com.scan.operacion;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";
	private static final String SECRET = "sigeiasecret";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			if (existeJWTToken(request)) {
				Claims claims = validateToken(request);
				if (claims.get("role") != null) {                                    
					setUpSpringAuthentication(claims);
                                        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                                        logger.info(request.getRequestURI() + " " + principal.toString());
				} else {
					SecurityContextHolder.clearContext();
				}
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			(response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
	}	

	private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Metodo para autenticarnos dentro del flujo de Spring
	 * 
	 * @param claims
	 */
	private void setUpSpringAuthentication(Claims claims) {
            @SuppressWarnings("unchecked")
            
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                            null);
            SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private boolean existeJWTToken(HttpServletRequest request) {                
		String authenticationHeader = request.getHeader(HEADER);
		return !(authenticationHeader == null || !authenticationHeader.startsWith(PREFIX));
	}

}


package br.com.desafio.livelo.ilia.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenProcessorFilter implements Filter{
	
	
	/**
	 * Here we are removing the refresh token from the request because the refresh token it is a cookie,
	 * so we don't need it inside the request body.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
				&& "refresh_token".equalsIgnoreCase(req.getParameter("grant_type"))
				&& req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if(cookie.getName().equals("refreshToken")) {
					String refreshToken = cookie.getValue();
					req = new MyServletRequestWrapper(req, refreshToken);
				}
			}
		}
		
		chain.doFilter(req, response);
		
	}
	
	/**
	 * Inner class to intercept the old request and inset a new map in a new request.
	 * After a request it's done, it can't be updated, 
	 * that's why we need to configure a new parameter map with the new parameters.
	 * 
	 * This solution is for the refresh token to be automatically informed when requesting the renewal of a new access token.
	 * This way the refresh token does not need to be informed in the request body again since it is a cookie
	 * @author bruno
	 *
	 */
	static class MyServletRequestWrapper extends HttpServletRequestWrapper{
		
		private String refreshToken;
		
		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[] {refreshToken});
			map.setLocked(true);
			return map;
		}
		
	}
	

}

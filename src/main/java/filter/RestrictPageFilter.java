package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/pages/*")
public class RestrictPageFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
							throws IOException, ServletException {
					
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String emailSession = (String)req.getSession().getAttribute("email");		
		
		if(emailSession==null) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
		}
		chain.doFilter(req, res);	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

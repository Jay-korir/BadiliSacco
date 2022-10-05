package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SessionFilter  implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Printing from session Remote address: " + request.getRemoteAddr());

        HttpServletRequest httpServletRequest = (HttpServletRequest)  request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)  response;
        HttpSession session = httpServletRequest.getSession(); //returns existing session or creates if not existing

        String reqPath = httpServletRequest.getServletPath();

        if (reqPath == null) {
            session.invalidate();
            httpServletResponse.sendRedirect("./");
            return;
        }

        if (reqPath.equalsIgnoreCase("/welcome") && session.isNew()) {
            httpServletResponse.sendRedirect("./login");
            return;
        }

        //in case user is accessing login/register/index page, they should not have a session.. invalidate
        if (reqPath.equalsIgnoreCase("/login")
                || reqPath.equalsIgnoreCase("/register")
                || reqPath.equalsIgnoreCase("/"))
            session.invalidate();

        //proceed to the resource/servlet requested
        chain.doFilter(request, response);

    }

    public void destroy() {

    }
}

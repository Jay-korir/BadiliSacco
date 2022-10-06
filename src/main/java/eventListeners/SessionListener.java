package eventListeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("-- sessionCreated invoked --");
        HttpSession session = se.getSession();
        System.out.println("session id: " + session.getId());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("-- sessionDestroyed invoked --");
    }
}

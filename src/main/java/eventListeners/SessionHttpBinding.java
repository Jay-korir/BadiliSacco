package eventListeners;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionHttpBinding implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("-- HttpSessionBindingListener#valueBound() --");
        System.out.printf("added attribute name: %s, value:%s %n",
                event.getName(), event.getValue());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("-- HttpSessionBindingEvent#valueUnbound() --");
        System.out.printf("removed attribute name: %s, value:%s %n",
                event.getName(), event.getValue());
    }
}


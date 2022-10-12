package eventListeners;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletRequetstAListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        System.out.println("attribute: " + event.getName() + " was added with value: " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        System.out.println("attribute: " + event.getName() + " was added with value: " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        System.out.println("attribute: " + event.getName() + " was added with value: " + event.getValue());
    }
}
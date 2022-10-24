package jstlCustomTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class HeaderTag extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println( "<!DOCTYPE html>\n" +
                " <html>\n" +
                "      <head>\n" +
                "      <!-- Required meta tags -->\n" +
                "          <meta charset=\"utf-8\">\n" +
                "          <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "          <title>Badili Sacco</title>\n" +
                "          <!-- plugins:css -->\n" +
                "          <link rel=\"stylesheet\" href=\"./template/assets/vendors/mdi/css/materialdesignicons.min.css\">\n" +
                "          <link rel=\"stylesheet\" href=\"./template/assets/vendors/css/vendor.bundle.base.css\">\n" +
                "          <!-- endinject -->\n" +
                "          <!-- Plugin css for this page -->\n" +
                "          <!-- End plugin css for this page -->\n" +
                "          <!-- inject:css -->\n" +
                "          <!-- endinject -->\n" +
                "          <!-- Layout styles -->\n" +
                "          <link rel=\"stylesheet\" href=\"./template/assets/css/style.css\">\n" +
                "          <!-- End layout styles -->\n" +
                "          <link rel=\"shortcut icon\" href=\"./template/assets/images/favicon.png\" />\n" +
                "         <link rel=\"stylesheet\" type=\\\"text/css\\\" href=\"./assets/CSS/style.css\"/>\n" +
                "      </head>\n" +
                "        <style> </style>\n" +
                "       <body style=\"margin: auto; width: auto;>\n" +
                "\n" +
                "         <div class=\"bg-img\">\n" +
                "           <div class=\"content\\\">");
    }
}

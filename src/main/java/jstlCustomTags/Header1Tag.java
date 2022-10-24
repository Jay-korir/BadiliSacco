package jstlCustomTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Header1Tag extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <title>Badili Sacco</title>\n" +
                "    <!-- plugins:css -->\n" +
                "    <link rel=\"stylesheet\" href=\"./template/assets/vendors/mdi/css/materialdesignicons.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"./template/assets/vendors/css/vendor.bundle.base.css\">\n" +
                "    <!-- endinject -->\n" +
                "    <!-- Plugin css for this page -->\n" +
                "    <!-- End plugin css for this page -->\n" +
                "    <!-- inject:css -->\n" +
                "    <!-- endinject -->\n" +
                "    <!-- Layout styles -->\n" +
                "    <link rel=\"stylesheet\" href=\"./template/assets/css/style.css\">\n" +
                "    <!-- End layout styles -->\n" +
                "    <link rel=\"shortcut icon\" href=\"./template/assets/images/favicon.png\" />\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"container-scroller\">\n" +
                "      <div class=\"container-fluid page-body-wrapper full-page-wrapper\">\n" +
                "        <div class=\"row w-100 m-0\">\n" +
                "          <div class=\"content-wrapper full-page-wrapper d-flex align-items-center auth login-bg\">\n" +
                "            <div class=\"card col-lg-4 mx-auto\">\n" +
                "              <div class=\"card-body px-5 py-5\">");
    }
}

package jstlCustomTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Footer1Tag extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println(" </div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<!-- content-wrapper ends -->\n" +
                "</div>\n" +
                "<!-- row ends -->\n" +
                "</div>\n" +
                "<!-- page-body-wrapper ends -->\n" +
                "</div>\n" +
                "<!-- container-scroller -->\n" +
                "<!-- plugins:js -->\n" +
                "<script src=\"../../assets/vendors/js/vendor.bundle.base.js\"></script>\n" +
                "<!-- endinject -->\n" +
                "<!-- End plugin js for this page -->\n" +
                "<!-- inject:js -->\n" +
                "<!-- <script src=\"../../assets/js/off-canvas.js\"></script>\n" +
                "<script src=\"../../assets/js/hoverable-collapse.js\"></script>\n" +
                "<script src=\"../../assets/js/misc.js\"></script>\n" +
                "<script src=\"../../assets/js/settings.js\"></script>\n" +
                "<script src=\"../../assets/js/todolist.js\"></script>\n" +
                "-->\n" +
                "<!-- endinject -->\n" +
                "</body>\n" +
                "</html>");

    }
}

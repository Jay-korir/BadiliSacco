package actions.contribution;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


 ;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;


import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/downloadPdf")
public class CreatePdf extends HttpServlet {
    ServletContext servletCtx = null;

    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-disposition",
                "inline; filename='DownloadedLoan.pdf'");

        try {

            Document document = new Document();

            PdfWriter.getInstance(
                    document, response.getOutputStream());

            document.open();

            document.add(new Paragraph("Loan Application Form"));
            document.add(new Paragraph(
                    "Please fill the form and submit it to the sacco premises as soon as possible\n"));
            document.setHtmlStyleClass(HTML);

            document.getHtmlStyleClass();
            document.add(new Paragraph(document.getHtmlStyleClass()));

            System.out.println(document.getHtmlStyleClass());


            document.close();

        } catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
        response.sendRedirect("./userDashboard.jsp");

    }

    public static final String HTML =
            "    BASIC INFORMATION\n" +
                    "     First Name:............\n" +

                    "     Last Name:---------------\n" +

                    "     Username:----------------\n" +

                    "     Email:----------------\n" +
                    "     ID Number:----------------\n" +

                    "    Contact Number:------------------\n" +

                    "     Signature:------------Date--------------\n" +
                    "\n" +
                    "     WORK INFORMATION\n" +
                    "     Current Occupation:------------\n" +

                    "     Employer Name:--------------\n" +

                    "     Employer Address:--------------\n" +

                    "     Loan Type:-----------------\n" +

                    "   Loan Amount:--------------(ksHs)\n" +

                    "     Length of Loan:--------(months)\n" +

                    "    Marital Status:--------------\n" +

                    "\n" +

                    "     COSIGNER\n" +

                    "     Name of Cosigner/Guarantor:------------ \n" +
                    "     Cosigner Occupation:------------\n" +
                    "     Cosigner IDNumber:------------\n" +
                    "     Comments:----------\n" +
                    "     Signature:------------Date--------------\n" +
                    "\n" +
                    " Official use only\n" +
                    "\n" +
                    " Sacco Finance representative\n" +
                    "     Name:------------\n" +

                    "     Recommendation:----------\n" +
                    "     Signature:------------Date--------------\n";

}




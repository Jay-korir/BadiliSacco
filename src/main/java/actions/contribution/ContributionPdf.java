package actions.contribution;

import actions.Mail;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Contribution;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;





@WebServlet("/contributionReport")
public class ContributionPdf  extends HttpServlet {

    ServletContext servletCtx = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        servletCtx = config.getServletContext();

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        /* Create Connection objects */
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sacco", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /* Define the SQL query */
        ResultSet query_set = null;
        try {

            query_set = stmt.executeQuery("SELECT id,username,amount,month,type,time_created FROM contributions");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /* Step-2: Initialize PDF documents - logical objects */
        Document my_pdf_report = new Document();
        try {
            PdfWriter.getInstance(my_pdf_report, Files.newOutputStream(Paths.get("C:/Users/MR MAN/Desktop/JavaPdf/contributions.pdf")));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        my_pdf_report.open();
        Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph paragraph = new Paragraph("Report for Current Contribution List");
        paragraph.setFont(bold);
        paragraph.setSpacingAfter(8);
        paragraph.setAlignment(5);

        //we have four columns in our table
        PdfPTable my_report_table = new PdfPTable(6);
        //create a cell object
        PdfPCell table_cell;

        while (true) {
            try {
                if (!query_set.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String contri_id = null;
            try {
                contri_id = query_set.getString("id");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            table_cell = new PdfPCell(new Phrase(contri_id));
            my_report_table.addCell(table_cell);
            String user_name = null;
            try {
                user_name = query_set.getString("username");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            table_cell = new PdfPCell(new Phrase(user_name));
            my_report_table.addCell(table_cell);
            String amount_id = null;
            try {
                amount_id = query_set.getString("amount");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            table_cell = new PdfPCell(new Phrase(amount_id));
            my_report_table.addCell(table_cell);
            String month_id = null;
            try {
                month_id = query_set.getString("month");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            table_cell = new PdfPCell(new Phrase(amount_id));
            my_report_table.addCell(table_cell);
            String type_id = null;
            try {
                type_id = query_set.getString("type");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            table_cell = new PdfPCell(new Phrase(type_id));
            my_report_table.addCell(table_cell);
            String date = null;
            try {
                date = query_set.getString("time_created");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            table_cell = new PdfPCell(new Phrase(date));
            my_report_table.addCell(table_cell);
        }
        /* Attach report table to PDF */
        try {
            my_pdf_report.add(paragraph);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        try {
            my_pdf_report.add(my_report_table);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        my_pdf_report.close();

        /* Close all DB related objects */
        try {
            query_set.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Create Connection objects */
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sacco", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /* Define the SQL query */
        ResultSet query_set = null;
        String type = request.getParameter("type");
        try {
            query_set = stmt.executeQuery("SELECT id,username,amount,month,type,time_created FROM contributions ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /* Step-2: Initialize PDF documents - logical objects */
        Document my_pdf_report = new Document();
        String resultMessage = "";


        try {
            PdfWriter.getInstance(my_pdf_report, Files.newOutputStream(Paths.get("C:/Users/MR MAN/Desktop/JavaPdf/contributions.pdf")));
            resultMessage = "The report was sent successfully";
        } catch (DocumentException e) {
            resultMessage = "There were an error: " + e.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
        }
            my_pdf_report.open();
            Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph paragraph = new Paragraph("Report for Current contributions List");
            paragraph.setFont(bold);
            paragraph.setSpacingAfter(8);
            paragraph.setAlignment(5);

            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(6);
            //create a cell object
            PdfPCell table_cell;

            while (true) {
                try {
                    if (!query_set.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String contri_id = null;
                try {
                    contri_id = query_set.getString("id");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                table_cell = new PdfPCell(new Phrase(contri_id));
                my_report_table.addCell(table_cell);
                String user_name = null;
                try {
                    user_name = query_set.getString("username");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                table_cell = new PdfPCell(new Phrase(user_name));
                my_report_table.addCell(table_cell);
                String amount_id = null;
                try {
                    amount_id = query_set.getString("amount");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                table_cell = new PdfPCell(new Phrase(amount_id));
                my_report_table.addCell(table_cell);
                String month_id = null;
                try {
                    month_id = query_set.getString("month");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                table_cell = new PdfPCell(new Phrase(amount_id));
                my_report_table.addCell(table_cell);
                String type_id = null;
                try {
                    type_id = query_set.getString("type");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                table_cell = new PdfPCell(new Phrase(type_id));
                my_report_table.addCell(table_cell);
                String date = null;
                try {
                    date = query_set.getString("time_created");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                table_cell = new PdfPCell(new Phrase(date));
                my_report_table.addCell(table_cell);
            }
            /* Attach report table to PDF */
            try {
                my_pdf_report.add(paragraph);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }

            try {
                my_pdf_report.add(my_report_table);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
            my_pdf_report.close();

            /* Close all DB related objects */
            try {
                query_set.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("./reports.jsp");

        }
    }



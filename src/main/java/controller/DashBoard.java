package controller;

import model.Members;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/dash")
public class DashBoard extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
       // session.setAttribute("username",user);
        session.setAttribute("loggedInTime"," logged in time :" + new Date());

        List<Members> members = new ArrayList<Members>();
        session.setAttribute("members", members);

        System.out.println(session.getId());
        System.out.println(session.getCreationTime());
        System.out.println(session.getLastAccessedTime());
        resp.getWriter().print(this.Dash());
    }

    public String Dash(){
        return "<!DOCTYPE html>"+
"<html lang=\"en\">"+

"<head>"+
  "<!-- Required meta tags -->"+
  "<meta charset=\"utf-8\">"+
  "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">"+
  "<title>Badili Sacco</title>"+
  "<!-- plugins:css -->"+
  "<link rel=\"stylesheet\" href=\"./assets/vendors/feather/feather.css\">"+
  "<link rel=\"stylesheet\" href=\"./assets/vendors/ti-icons/css/themify-icons.css\">"+
  "<link rel=\"stylesheet\" href=\"./assets/vendors/css/vendor.bundle.base.css\">"+
  "<!-- endinject -->"+
  "<!-- Plugin css for this page -->"+
  "<link rel=\"stylesheet\" href=\"./assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css\">"+
  "<link rel=\"stylesheet\" href=\"./assets/vendors/ti-icons/css/themify-icons.css\">"+
  "<link rel=\"stylesheet\" type=\"text/css\" href=\"./assets/js/select.dataTables.min.css\">"+
  "<!-- End plugin css for this page -->"+
  "<!-- inject:css -->"+
  "<link rel=\"stylesheet\" href=\"./assets/css/vertical-layout-light/style.css\">"+
  "<!-- endinject -->"+
  "<link rel=\"shortcut icon\" href=\"./assets/images/favicon.png\" />"+
"</head>"+
"<body bgcolor=\"Lightskyblue\" "+
"  <div class=\"container-scroller\" bgcolor=\"Lightskyblue\">"+
    "<!-- partial:partials/_navbar.html -->"+

    "<!-- partial -->"+
    "<div class=\"container-fluid page-body-wrapper\">"+
      "<!-- partial:partials/_settings-panel.html -->"+
      "<div class=\"theme-setting-wrapper\">"+

      "</div>"+

      "<!-- partial -->"+
      "<!-- partial:partials/_sidebar.html -->"+
      "<nav class=\"sidebar sidebar-offcanvas\" id=\"sidebar\">"+
        "<ul class=\"nav\">"+
          "<li class=\"nav-item\">"+
            "<a class=\"nav-link\" href=\"index.html\">"+
              "<i class=\"icon-grid menu-icon\"></i>"+
              "<span class=\"menu-title\">Dashboard</span>"+
            "</a>"+
          "</li>"+
          "<li class=\"nav-item\">"+
            "<a class=\"nav-link\" data-toggle=\"collapse\"  href=\"./add\" aria-expanded=\"false\" aria-controls=\"ui-basic\">"+
              "<i class=\"icon-layout menu-icon\"   ></i>"+
              "<span class=\"menu-title\"> Add Members</span>"+
            "</a>"+

          "</li>"+
          "<li class=\"nav-item\">"+
            "<a class=\"nav-link\" data-toggle=\"collapse\" href=\"./contribution?id\" aria-expanded=\"false\" aria-controls=\"form-elements\">"+
              "<i class=\"icon-columns menu-icon\"></i>"+
              "<span class=\"menu-title\">  Add Contribution</span>"+
            "</a>"+

          "</li>"+
          "<li class=\"nav-item\">"+
            "<a class=\"nav-link\" data-toggle=\"collapse\" href=\"#charts\" aria-expanded=\"false\" aria-controls=\"charts\">"+
              "<i class=\"icon-bar-graph menu-icon\"></i>"+
              "<span class=\"menu-title\">Savings</span>"+
            "</a>"+
          "</li>"+
          "<li class=\"nav-item\">"+
            "<a class=\"nav-link\" data-toggle=\"collapse\" href=\"#tables\" aria-expanded=\"false\" aria-controls=\"tables\">"+
              "<i class=\"icon-grid-2 menu-icon\"></i>"+
              "<span class=\"menu-title\">Loans</span>"+
            "</a>"+
          "</li>"+
          "<li class=\"nav-item\">"+
            "<a class=\"nav-link\" data-toggle=\"collapse\" href=\"#icons\" aria-expanded=\"false\" aria-controls=\"icons\">"+
              "<i class=\"icon-contract menu-icon\"></i>"+
              "<span class=\"menu-title\">Expenses</span>"+
            "</a>"+
          "</li>"+
        "</ul>"+
      "</nav>"+
      "<!-- partial -->"+
      "<div class=\"main-panel\">"+
        "<div class=\"content-wrapper\">"+
          "<div class=\"row\">"+
            "<div class=\"col-md-12 grid-margin\">"+
              "<div class=\"row\">"+
                "<div class=\"col-12 col-xl-8 mb-4 mb-xl-0\">"+
                  "<h3 class=\"font-weight-bold\">Welcome Badili Sacco</h3>"+
                  "<h6 class=\"font-weight-normal mb-0\">jipange uzeeni</h6>"+
                "</div>"+
              "</div>"+
            "</div>"+
          "</div>"+
          "<div class=\"row\">"+
            "<div class=\"col-md-6 grid-margin stretch-card\">"+
              "<div class=\"card tale-bg\">"+
                "<div class=\"card-people mt-auto\">"+
                  "<img src=\"./assets/images/dashboard/banner_8.png\" alt=\"people\">"+
                  "<div class=\"weather-info\">"+
                    "<div class=\"d-flex\">"+
                    "</div>"+
                  "</div>"+
                "</div>"+
              "</div>"+
            "</div>"+
            "<div class=\"col-md-6 grid-margin transparent\">"+
              "<div class=\"row\">"+
                "<div class=\"col-md-6 mb-4 stretch-card transparent\">"+
                  "<div class=\"card card-tale\">"+

                  "</div>"+
                "</div>"+
                "<div class=\"col-md-6 mb-4 stretch-card transparent\">"+
                  "<div class=\"card card-dark-blue\">"+

                  "</div>"+
                "</div>"+
              "</div>"+
              "<div class=\"row\">"+
                "<div class=\"col-md-6 mb-4 mb-lg-0 stretch-card transparent\">"+
                  "<div class=\"card card-light-blue\">"+

                  "</div>"+
                "</div>"+
                "<div class=\"col-md-6 stretch-card transparent\">"+
                  "<div class=\"card card-light-danger\">"+

                  "</div>"+
                "</div>"+
              "</div>"+
            "</div>"+
          "</div>"+




        "<!-- partial -->"+
      "</div>"+
      "<!-- main-panel ends -->"+
    "</div> "+
    "<!-- page-body-wrapper ends -->"+
  "</div>"+
  "<!-- container-scroller -->"+

  "<!-- plugins:js -->"+
  "<script src=\"vendors/js/vendor.bundle.base.js\"></script>"+
  "<!-- endinject -->"+
  "<!-- Plugin js for this page -->"+
  "<script src=\"./assets/vendors/chart.js/Chart.min.js\"></script>"+
  "<script src=\"./assets/vendors/datatables.net/jquery.dataTables.js\"></script>"+
  "<script src=\"./assets/vendors/datatables.net-bs4/dataTables.bootstrap4.js\"></script>"+
  "<script src=\"./assets/js/dataTables.select.min.js\"></script>"+

  "<!-- End plugin js for this page -->"+
  "<!-- inject:js -->"+
  "<script src=\"./assets/js/off-canvas.js\"></script>"+
  "<script src=\"./assets/js/hoverable-collapse.js\"></script>"+
  "<script src=\"./assets/js/template.js\"></script>"+
  "<script src=\"./assets/js/settings.js\"></script>"+
  "<script src=\"./assets/js/todolist.js\"></script>"+
  "<!-- endinject -->"+
  "<!-- Custom js for this page-->"+
  "<script src=\"./assets/js/dashboard.js\"></script>"+
  "<script src=\"./assets/js/Chart.roundedBarCharts.js\"></script>"+
  "</div>"+
  "<!-- End custom js for this page-->"+
"</body>"+

"</html>";


    }
}

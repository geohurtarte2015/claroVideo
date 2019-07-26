<%-- 
    Document   : principal.jsp
    Created on : 25/10/2016, 04:08:32 PM
    Author     : edgar.hurtarte
--%>

<%@page import="pojos.ResumeError"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
  <head>

   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Claro | Video</title>

    <!-- Charts -->
    <link rel="stylesheet" href="plantilla/bower_components/charts/code/css/highcharts.css" type="text/css"/>
    <!-- Bootstrap -->
    <link href="plantilla/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="plantilla/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="plantilla/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="plantilla/vendors/iCheck/skins/flat/green.css" rel="stylesheet">


    <!-- Custom Theme Style -->
    <link href="plantilla/build/css/custom.min.css" rel="stylesheet">
    
  </head>

  <body class="nav-md">
      
    <% 
        String nombre = (String) session.getAttribute("nombre");    
        String apellido = (String) session.getAttribute("apellido");
        
        if (session.getAttribute("nombre") == null) {
            response.sendRedirect("index.jsp");
            nombre=null;
        } 
        
        List<ResumeError> arrayIncomplete=(List<ResumeError>)request.getAttribute("arrayIncomplete");
        List<ResumeError> arrayInvalid=(List<ResumeError>)request.getAttribute("arrayInvalid");
        List<ResumeError> arrayNotExists=(List<ResumeError>)request.getAttribute("arrayNotExists");
        List<ResumeError> arrayErrorIntern=(List<ResumeError>)request.getAttribute("arrayErrorIntern");
        List<ResumeError> arrayServiceExits=(List<ResumeError>)request.getAttribute("arrayServiceExits");
        List<ResumeError> arrayUnknown=(List<ResumeError>)request.getAttribute("arrayUnknown");
        List<Object> vasGroupByCountry=(List<Object>)request.getAttribute("vasGroupByCountry");
        List<ResumeError> arraySuccess=(List<ResumeError>)request.getAttribute("arraySuccess");
        //String test = (String) request.getAttribute("prueba");
    %>
         
      
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><span>CLARO</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile">
            
              <div class="profile_info">
                  <span>Bienvenido,<%=" "+nombre+" "+apellido%></span>
                <h2></h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />
             <br />
              <br />
               <br />
                <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="principal.jsp">Ver registros</a></li>  
                      <li><a href="user.jsp">Crear usuario</a></li>
                      <li><a href="ServletGraphs">Estadisticas</a></li>  
                    </ul>
                  </li>
                </ul>
              </div>
      

            </div>
            <!-- /sidebar menu -->

         
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    Opciones
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="EndSesion"><i class="fa fa-sign-out pull-right"></i> Salir</a></li>
                  </ul>
                </li>
         
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
                            
            
              <div class="modal-footer">
                  
                  <table id="tablechart" class="highchart" border="1" data-graph-container-before="1" data-graph-type="column" width="50%">
                 <caption> Tipo de errores por Pais </caption>
                        <thead>
                            <tr>
                                <th>Error</th>
                                <%
                                for(int i=0;i<vasGroupByCountry.size();i++){ 
                                 String pais = (String)vasGroupByCountry.get(i);
                                %>
                               
                                <th> <%= pais %></th>
                                <% } %>
                            </tr>
                          
                         </thead>
                         <tbody>
                            <tr>
                                <td>Parametros Incompletos</td>
                                <%
                                for(int i=0;i<arrayIncomplete.size();i++){ 
                                 ResumeError ObjectErrorIncomplete = arrayIncomplete.get(i);
                                %>
                                <td><%= ObjectErrorIncomplete.getTotal() %></td>
                                <% } %>
                           
                            </tr>
                            <tr>
                                <td>Parametro Invalido</td>
                                <%
                                for(int i=0;i<arrayInvalid.size();i++){ 
                                 ResumeError ObjectErrorInvalid = arrayInvalid.get(i);
                                %>
                                <td><%= ObjectErrorInvalid.getTotal() %></td>
                                <% } %>
                          
                            </tr>
                            <tr>
                                <td>Ya existe una cuenta de correo</td>
                                <%
                                for(int i=0;i<arrayNotExists.size();i++){ 
                                 ResumeError ObjectNotExists= arrayNotExists.get(i);
                                %>
                                <td><%= ObjectNotExists.getTotal() %></td>
                                <% } %>
                            
                            </tr>
                            <tr>
                                <td>Error Interno</td>
                                <%
                                for(int i=0;i<arrayErrorIntern.size();i++){ 
                                 ResumeError ObjectErrorInterno= arrayErrorIntern.get(i);
                                %>
                                <td><%= ObjectErrorInterno.getTotal() %></td>
                                <% } %>
                          
                            </tr>
                            <tr>
                                <td>Servicio ya utilizado</td>
                                <%
                                for(int i=0;i<arrayServiceExits.size();i++){ 
                                 ResumeError ObjectErrorServiceExist= arrayServiceExits.get(i);
                                %>
                                <td><%= ObjectErrorServiceExist.getTotal() %></td>
                                <% } %>
                          
                            </tr>
                            <tr>
                                <td>Exitosos</td>
                                <%
                                for(int i=0;i<arraySuccess.size();i++){ 
                                 ResumeError ObjectSuccess= arraySuccess.get(i);
                                %>
                                <td><%= ObjectSuccess.getTotal() %></td>
                                <% } %>
                          
                            </tr>
                            <tr>
                                <td>Desconocidos</td>
                                <%
                                for(int i=0;i<arrayUnknown.size();i++){ 
                                 ResumeError ObjectUnknown= arrayUnknown.get(i);
                                %>
                                <td><%= ObjectUnknown.getTotal() %></td>
                                <% } %>
                          
                            </tr>
                        </tbody>
                      </table> 
                             
              </div>
       
     
          <br />




        
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Transaction Claro Video - Developer by <a href="https://www.claro.com.gt">Claro</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="plantilla/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="plantilla/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="plantilla/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="plantilla/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <script src="plantilla/vendors/iCheck/icheck.min.js"></script>


    <!-- Custom Theme Scripts -->
    <script src="plantilla/build/js/custom.min.js"></script>
    
     <script src="plantilla/bower_components/charts/code/highcharts.js"></script>
     <script src="plantilla/bower_components/charts/code/js/modules/data.js"></script>
     <script src="plantilla/bower_components/charts/code/js/modules/exporting.js"></script>
     <script src="plantilla/bower_components/charts/api/js/jquery-1.11.3.min.js"></script>
  
     <script src="plantilla/bower_components/charts/jquery.highchartTable.js"></script>
     <script src="plantilla/bower_components/charts/jquery.highchartTable-mins.js"></script>
    
     <script>
        $(document).ready(function() {
        
            $('#tablechart').highchartTable();
                        
         });
    </script>


  </body>
</html>


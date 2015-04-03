<!DOCTYPE html>
<html>
<head>
	<title>Menu de Entidades</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
       <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/morris/morris.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />
       <link href="resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
</head>
<body class="skin-blue">
       <header class="header">
            <a href="#" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
               I9 Academia
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span>Administrador <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-light-blue">
                                    <img src="resources/img/avatar5.png" class="img-circle" alt="User Image" />
                                    <p>
										Administrador
                                    </p>
                                </li>
                                <!-- Menu Body -->
                                <li class="user-body">
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Followers</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Sales</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Friends</a>
                                    </div>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">Profile</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href='/academia' class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="resources/img/avatar5.png" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>Olá, Administrador</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search..."/>
                            <span class="input-group-btn">
                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                    
                    <ul class="sidebar-menu">
                        <li class="active">
                            <a href='/academia/aluno'>
                                <i class="fa fa-dashboard"></i> <span>Alunos</span>
                            </a>
                        </li>
						<li class="active">
                            <a href='/academia/instrutor'>
                                <i class="fa fa-dashboard"></i> <span>Instrutores</span>
                            </a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Bem Vindo Administrador                        
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href='/academia/efetuaLogin#'><i class="fa fa-dashboard"></i>Home</a></li>
                        <li class="active">Blank page</li>
                    </ol>
                </section>

                <!-- Main content -->
                <<div id="includedContent">
                       
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

       <script src="resources/js/jquery-2.0.2.min.js" type="text/javascript"></script>
       <script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
       <script src="resources/js/AdminLTE/app.js" type="text/javascript"></script>
       <script src="resources/js/AdminLTE/demo.js" type="text/javascript"></script>
</body>
</html>
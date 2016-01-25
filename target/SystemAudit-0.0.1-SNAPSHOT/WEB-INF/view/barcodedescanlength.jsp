<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    

	<title>Bar Code Search Demo</title>

	<!-- QRCode CSS -->
	<link href="./resources/theme/default/css/qrcode.css" rel="stylesheet">

	<!-- Bootstrap Core CSS -->
    <link href="./resources/theme/default/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="./resources/theme/default/css/metisMenu.min.css" rel="stylesheet">

	<!-- Custom CSS -->
    <link href="./resources/theme/default/css/sb-admin-2.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="./resources/theme/default/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="./">Dee Mobile App</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
		                    <div class="panel panel-primary">
		                    	
		                        <div class="panel-heading">
		                            <div class="row">
		                                <div class="col-xs-12 text-center">
		                                    <i class="fa fa-arrows-h fa-5x"></i>
		                                </div>
		                                <div class="col-xs-12 text-right">
		                                    <div class="huge"> </div>
		                                    <div class="text-center">Update Length!</div>
		                                </div>
		                            </div>
		                        </div>
		                        <a href="">
		                            <div class="panel-footer">
		                                <span class="pull-left">Go to page</span>
		                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
		                                <div class="clearfix"></div>
		                            </div>
		                        </a>
		                    </div>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            
            <!--div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Update Length</h1>
                </div>
                <!-- /.col-lg-12 ->
            </div>
            < /.row -->
            
            <div class="row">
            	<div class="col-lg-12">
            		<div class="panel-body">
                            <!-- Button trigger modal -->
                            
                            <!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">Scan BarCode</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="panel panel-default">
						                        <div class="panel-heading">
									            	<div class="panel-heading" align="center">
														<select id="cameraId" class="form-control" style="display: inline-block;width: auto;"></select>
														<button id="play" data-toggle="tooltip" title="Play" type="button" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-play"></span></button>
														<button id="save" data-toggle="tooltip" title="Image shoot" type="button" class="btn btn-info btn-sm disabled"><span class="glyphicon glyphicon-picture"></span></button>
									                    <!--<button id="stop" data-toggle="tooltip" title="Stop" type="button" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-stop"></span></button>
									                    <button id="stopAll" data-toggle="tooltip" title="Stop streams" type="button" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-stop"></span></button>-->

													</div>
												</div>
												<div class="panel-body">
						                            <div class="row">
						                                <div class="col-lg-12">
						                                	<div id="QR-Code" class="container" style="width:100%">
																<div class="well" style="position: relative;display: inline-block;">
																	<canvas id="qr-canvas" width="320" height="240" autoplay="true"></canvas>
																	<div class="scanner-laser laser-rightBottom" style="opacity: 0.5;"></div>
																	<div class="scanner-laser laser-rightTop" style="opacity: 0.5;"></div>
																	<div class="scanner-laser laser-leftBottom" style="opacity: 0.5;"></div>
																	<div class="scanner-laser laser-leftTop" style="opacity: 0.5;"></div>
																</div>
															
																<div id="result" class="thumbnail">
																	<div class="well" style="position: relative;display: inline-block;">
											                            <img id="scanned-img" src="" width="320" height="240">
											                        </div>
																	<div class="caption">
														   				<h3>Scanned result</h3>
														  					<p id="scanned-QR"></p>
																	</div>
															    </div>
															</div>
						                                </div>
						                            </div>
						                            <!-- /.row (nested) -->
						                        </div>
											</div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary" onclick="setBarcode();">Save changes</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->
                        </div>
                        <div class="panel panel-primary">
                        <div class="panel-heading">
                            Update Length
                        </div>
                        <form:form name="scanBarcode" method="POST" onsubmit="return checkBarcode();" modelAttribute="updateLength">
                        <div class="panel-body">
                        	
                        	<button class="btn btn-danger btn-circle btn-xl" data-toggle="modal" data-target="#myModal">
                                <i class="fa fa-qrcode"></i>
                            </button>
                            
                                        
                            
								<div class="form-group has-error">
									<label class="control-label" for="inputError"><form:errors path="barCode" /></label>
								</div>
									Barcode<br />
				   					<form:input path="barCode" class="form-control" id="barCode" /> <br/>
				   				
				   				<button type="button" class="btn btn-primary" onclick="getBarcodeData();">Get Data</button><br />
				   				Item<br /><form:input path="item" class="form-control" id="item" readOnly="true" /><br />
				   				Item Description<br /><form:input class="form-control" path="itemDesc" readOnly="true" id="itemDesc" /><br />
				   				Old Length<br /><form:input path="oldLength" class="form-control" id="oldLength" readOnly="true" /><br />
				   				Length<br /><form:input type="text" class="form-control" path="length" id="length" /> <br /><br />
				   				
								
							
							
                        </div>
                        <div class="panel-footer">
                            <button type="submit" class="btn btn-success"> Update </button>
                        </div>
                        </form:form>
                    </div>
                    
				</div> 
       		</div>
    	</div>
 	</div>
 



					           
</body>
	
	
	<!-- jQuery -->
	<script src="./resources/theme/default/js/jquery.min.js"></script>
    
    <!-- Bootstrap Core JavaScript -->
    <script src="./resources/theme/default/js/bootstrap.min.js"></script>
    
    <!-- Metis Menu Plugin JavaScript -->
    <script src="./resources/theme/default/js/metisMenu.min.js"></script>
    
    

    <!-- Custom Theme JavaScript -->
    <script src="./resources/theme/default/js/sb-admin-2.js"></script>
   
    <!-- QRCODE -->
    <script type="text/javascript" src="./resources/theme/default/js/main.js"></script>
	<script type="text/javascript" src="./resources/theme/default/js/qrcodelib.js"></script>
	<script type="text/javascript" src="./resources/theme/default/js/WebCodeCam.js"></script>
    
    <script type="text/javascript">
	
		function getBarcodeData() {
			if(document.getElementById("barCode").value=="") {
				alert("Please enter barcode");
				return false;
			}
			document.scanBarcode.submit();
			
		}
		function setBarcode() {
			//alert(document.getElementById("barCode"));
			if(document.getElementById("scanned-QR").innerHTML=="") {
				alert("Please scan barcode.");
				return false;
			}
			document.getElementById("barCode").value=document.getElementById("scanned-QR").innerHTML;
			//alert("true");
			document.scanBarcode.submit();
			
		}
		
		function checkBarcode() {
			
			if(document.getElementById("barCode").value=="") { // && document.getElementById("scanned-QR").innerHTML=="") {
				alert("Please scan barcode or enter barcode.");
				return false;
			}
			if(document.getElementById("length").value=="") {
				alert("Please enter length.");
				return false;
			}
			document.scanBarcode.action="./updateLengthForBarCode";
			return true;
		}
	</script>
</html>
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

  

        <div id="page-wrapper">
            
            <div class="row">
                <div class="col-lg-12">
                    <h5 class="page-header">Scan barcode and verify Control No/Heat No</h5>
                </div>
            </div>
            
            
            <div class="row">
            	<div class="col-lg-12">
           		    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Scan Barcode
                        </div>
                       	<div class="panel-body">
                       		<div class="form-group">
								<label>Text Input</label>
									
							        <input class="form-control" type="text" id="txtBarcode" name="txtBarcode">
							        <p class="help-block">Please enter barcode to get detials.</p>
							</div>
							<button class="btn btn-danger btn btn-primary btn-lg btn-block" onClick="startVideo();">
                               	<i class="fa fa-barcode">Click to get Barcode details</i>
                           	</button>
							<video muted id="monitor" width="380" height="250"  autoplay></video>
							<canvas id="photo" height="680" style="display:none" width="480" ></canvas><!-- height="640" width="480" -->
                       		
                       	</div>
                       	<div class="panel-footer" id="resultBar">
                           	
                       	</div>
                   	</div>
				</div> 
       		</div>
    	</div>
 	</div>
 <div class="select">
   	<select id="videoSource" style="display:none"></select>
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
    
    <script type="text/javascript">
    
    var videoElement = document.querySelector('video');
    //var audioSelect = document.querySelector('select#audioSource');
    var videoSelect = document.querySelector('select#videoSource');

    navigator.getUserMedia = navigator.getUserMedia ||
      navigator.webkitGetUserMedia || navigator.mozGetUserMedia;

    function gotSources(sourceInfos) {
      for (var i = 0; i !== sourceInfos.length; ++i) {
        var sourceInfo = sourceInfos[i];
        var option = document.createElement('option');
        option.value = sourceInfo.id;
        /*if (sourceInfo.kind === 'audio') {
          option.text = sourceInfo.label || 'microphone ' +
            (audioSelect.length + 1);
          audioSelect.appendChild(option);
        } else*/
        if (sourceInfo.kind === 'video') {
          option.text = sourceInfo.label || 'camera ' + (videoSelect.length + 1);
          videoSelect.appendChild(option);
    	option.selected=true;
        } else {
          console.log('Some other kind of source: ', sourceInfo);
        }
    	
      }
      //start();
    }



    if (typeof MediaStreamTrack === 'undefined' ||
        typeof MediaStreamTrack.getSources === 'undefined') {
      alert('This browser does not support MediaStreamTrack.\n\nTry Chrome.');
    } else {
    	setLastIndexOfSelect(videoSelect);
      MediaStreamTrack.getSources(gotSources);
    }

    function successCallback(stream) {
      window.stream = stream; // make stream available to console
      videoElement.src = window.URL.createObjectURL(stream);
      videoElement.play();
      setTimeout(captureImage, 300);
      
    }

    function errorCallback(error) {
      console.log('navigator.getUserMedia error: ', error);
    }

    function start() {
    	setLastIndexOfSelect(videoSelect);
      if (!!window.stream) {
        videoElement.src = null;
        //window.stream.stop();
      }
      //var audioSource = audioSelect.value;
      var videoSource = videoSelect.value;
      var constraints = {
  
        video: {
          mandatory: {
/*    	      minWidth: 640,
    	      minHeight: 480
   	      maxWidth: 640,
    	      maxHeight: 480 */
    	  },
          optional: [{
            sourceId: videoSource
          }]
        }
      };
      
      navigator.getUserMedia(constraints, successCallback, errorCallback);
    }

    //audioSelect.onchange = start;


    function setLastIndexOfSelect(s) {
    	for ( var i = 0; i < s.options.length; i++ ) {
    		s.options[i].selected = true;
    	}
    }

    //videoSelect.onchange = start;




    var video = document.getElementById('monitor');
    var canvas = document.getElementById('photo');
    
    function startVideo() {
    	document.getElementById("resultBar").innerHTML="";
    	context=canvas.getContext('2d');
    	context.clearRect(0, 0, canvas.width, canvas.height);
    	if(document.getElementById("txtBarcode").value==""){
    		document.getElementById("txtBarcode").disabled=true;
	    	document.getElementById("monitor").style.display="block";
		    document.getElementById("photo").style.display="none";
	    	start();
    	}else{
    		var formData=new FormData();
    	    formData.append("txtBarcode", document.getElementById("txtBarcode").value);
    	    uploadToServer(formData);
    	}
    }
    
    function captureImage() {
    	
    	canvas.getContext('2d').drawImage(video, 0, 0);
    	
    	uploadCanvasAsImage();
    }
    
     		function canvasToBlob (canvas, type) {
              var byteString = atob(canvas.toDataURL().split(",")[1]),
                  ab = new ArrayBuffer(byteString.length),
                  ia = new Uint8Array(ab),
                  i;

              for (i = 0; i < byteString.length; i++) {
                  ia[i] = byteString.charCodeAt(i);
             	}

              return new Blob([ab], {
                  type: type
              });
          }

      	function dataURItoBlob(dataURI)
	        {
	            var byteString = atob(dataURI.split(',')[1]);
	
	            var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]
	
	            var ab = new ArrayBuffer(byteString.length);
	            var ia = new Uint8Array(ab);
	            for (var i = 0; i < byteString.length; i++)
	            {
	                ia[i] = byteString.charCodeAt(i);
	            }
	
	            var bb = new Blob([ab], { "type": mimeString });
	            return bb;
	        }
      	
          function uploadCanvasAsImage() {

              var data = new FormData();

              var dataUrl = canvas.toDataURL("image/jpeg");
              var blob = dataURItoBlob(dataUrl);

              var formData=new FormData();
              formData.append("blob", blob);
              formData.append("blobName", "barCode.png");
              formData.append("blobType", "image/jpeg");
              uploadToServer(formData);

          }

          
          
          function uploadToServer(formData) {
        	  //window.stream.stop();
        	 // document.getElementById("monitor").display="none";
   	    	// document.getElementById("photo").display="block";
          	if (window.XMLHttpRequest) { // Mozilla, Safari, ...
              	xhttp= new XMLHttpRequest();
                //alert("Yes. Your browser must be one among them - Mozilla, Safari, Chrome, Rockmelt, IE 8.0 or above");
              } else if (window.ActiveXObject) { // IE
                try {
              	  xhttp= new ActiveXObject("Msxml2.XMLHTTP");
                  //alert("Yes. Your browser must be IE");
                } 
                catch (e) {
                  try {
                  	xhttp= new ActiveXObject("Microsoft.XMLHTTP");
                    //alert("Yes. Your browser must be IE");
                  } 
                  catch (e) {}
                }
              }
           	if (!xhttp) {
                alert("Your browser is not supported AJAX!");
                return false;
              }
          	xhttp.onreadystatechange = function() {
          	   if (xhttp.readyState == 4 && xhttp.status == 200) {
          	     var respTxt = xhttp.responseText;
          	     if(respTxt=="error") {
          	    	 setTimeout(captureImage, 300);
          	     } else if(respTxt=="Exception"){
          	    	window.stream.stop();
         	    	alert("Error in process. Please try again.");
          	     } else {
          	    	document.getElementById("resultBar").innerHTML=respTxt;
          	    	if(document.getElementById("txtBarcode").value==""){
          	    	 document.getElementById("monitor").style.display="none";
          	    	 document.getElementById("photo").style.display="block";
          	    	 document.getElementById("photo").style.width="180px";
          	    //	 alert("barcode : " + respTxt);
          	    	 document.getElementById("resultBar").innerHTML=respTxt;
          	    	 window.stream.stop();
          	    	}
          	     }
          	    document.getElementById("txtBarcode").disabled=false;
 	    		document.getElementById("txtBarcode").value="";
          	   }
          	}
          	xhttp.open("POST", "<%=request.getContextPath()%>/test", true);
          	//xhttp.setRequestHeader("Content-type", "multipart/form-data");
          	xhttp.send(formData);
          }
	
	</script>
	Context path : <%=request.getContextPath()%>
	Local Address : <%=request.getLocalAddr()%>
	Local Name : <%=request.getLocalName()%>
	Local Port : <%=request.getLocalPort()%>
	Local Remote Address : <%=request.getRemoteAddr()%>
	Local Request URI : <%=request.getRequestURI()%>
	Get Path Info : <%=request.getPathInfo()%>
	
</html>

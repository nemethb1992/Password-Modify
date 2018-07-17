<%@include  file="/views/Header.jsp"%> 
    <div class="container-fluid colored-fluid">
        <img class='float-left align-top position-absolute .d-none .d-sm-block' style="height: 100px; margin: 30px;" src='${pageContext.request.contextPath}/public/imgs/pm_logo_mini_white.svg'>
        <div class="container d-flex px-sm-0  h-100">
            <div class="row justify-content-center mx-auto align-self-center w-100">
                 <div class="col-12 col-sm-12 col-md-8 col-lg-6 p-4 h-50 col-xl-6 blue MyContainer">
					<div class="form-group">
 						<label for="text" class="h3">Tartom�nyi felhaszn�l� friss�t�s</label>
					</div>
					
       				<form id='LR_form' method='post' action='${pageContext.request.contextPath}/Dashboard_c'>
                    <div class="form-group">
 						 <label for="usr">Tartom�nyi felhaszn�l�n�v:</label>
  						<input type="text" class="form-control h5" id="usr">
					</div>
					<div class="form-group">
 						<label for="pwd">R�gi jelsz�:</label>
  						<input type="password" class="form-control h5" id="pwd-old">
					</div>
					<div class="form-group">
 						<label for="pwd">�j jelsz�:</label>
  						<input type="password" class="form-control h5" id="pwd-new1">
					</div>
					<div class="form-group">
 						<label for="pwd">�j jelsz� ism�t:</label>
  						<input type="password" class="form-control h5" id="pwd-new2">
					</div>
					<button type="button" id="save_btn" class="btn  w-100">Ment�s</button>
					</form>
                </div>
            </div>
        </div>
    </div>
<%@include  file="/views/Footer.jsp"%>
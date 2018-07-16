<%@include  file="/views/Header.jsp"%> 
    <div class="container-fluid colored-fluid">
        <img class='float-left align-top position-absolute .d-none .d-sm-block' style="height: 100px; margin: 30px;" src='${pageContext.request.contextPath}/public/imgs/pm_logo_mini_white.svg'>
        <div class="container d-flex px-sm-0  h-100">
            <div class="row justify-content-center mx-auto align-self-center w-100">
                 <div class="col-12 col-sm-12 col-md-8 col-lg-6 p-5 h-50 col-xl-6 blue MyContainer">
					<div class="form-group">
 						<label for="text class="h1"">Tartományi felhasználó frissítés</label>
					</div>
                    <div class="form-group">
 						 <label for="usr">Tartományi felhasználónév:</label>
  						<input type="text" class="form-control" id="usr">
					</div>
					<div class="form-group">
 						<label for="pwd">Régi jelszó:</label>
  						<input type="password" class="form-control" id="pwd">
					</div>
					<div class="form-group">
 						<label for="pwd">Új jelszó:</label>
  						<input type="password" class="form-control" id="pwd">
					</div>
					<div class="form-group">
 						<label for="pwd">Új jelszó ismét:</label>
  						<input type="password" class="form-control" id="pwd">
					</div>
					<button type="button" class="btn  w-50">Mentés</button>
                </div>
            </div>
        </div>
    </div>
<%@include  file="/views/Footer.jsp"%>
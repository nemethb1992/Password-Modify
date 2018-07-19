<%@include  file="/views/Header.jsp"%> 
    <div class="container-fluid colored-fluid">
        <img class='float-left align-top position-absolute .d-none .d-sm-block' style="height: 100px; margin: 30px;" src='${pageContext.request.contextPath}/public/imgs/pm_logo_mini_white.svg'>
        <div class="container d-flex px-sm-0  h-100">
            <div class="row justify-content-center mx-auto align-self-center w-100">
                 <div class="col-12 col-sm-12 col-md-8 col-lg-6 p-4 h-50 col-xl-6 blue MyContainer">
					<div class="form-group">
 						<label for="text" id="HeaderLabel" class="  h3 mb-2">Tartományi jelszó frissítés</label>
					</div>
					
       				<form id='LR_form' method='post' action='${pageContext.request.contextPath}/Dashboard_c'>
                    <div class="form-group">
 						 <label for="usr">Tartományi felhasználónév:</label>
  						<input type="text" class="form-control h5" value="balazs.nemeth" id="usr">
					</div>
					<div class="form-group">
 						<label for="pwd">Régi jelszó:</label>
  						<input type="password" class="form-control h5" value="hxx8ka3HgB8Wy" id="pwd-old">
					</div>
					<div class="form-group">
 						<label for="pwd">Új jelszó:</label>
  						<input type="password" class="form-control h5" value="Testjelszo1992"  id="pwd-new1">
					</div>
					<div class="form-group">
 						<label for="pwd">Új jelszó ismét:</label>
  						<input type="password" class="form-control h5" value="Testjelszo1992"  id="pwd-new2">
					</div>
					<button type="button" id="save_btn" class="btn btn-dark w-50">Mentés</button>
					<Label id="info_label" class="float-right text-center pt-0 pt-sm-3 w-50"></Label>
					</form>
                </div>
            </div>
        </div>
    </div>
<%@include  file="/views/Footer.jsp"%>
$(document).ready(function(){
    var loader = document.getElementById("loader");
    loader.style.display="block";
    
    var modalLogIn = document.getElementById('myModalLogIn');
    var modalSingIn = document.getElementById('myModalSingIn');

    var loginButton = document.getElementById("loginButton");
    var singinButton = document.getElementById("singinButton");
    
    
    loginButton.onclick = function() {
        modalLogIn.style.display = "block";
    }
    
    singinButton.onclick = function() {
        modalSingIn.style.display = "block";
    }
    // Get the <span> element that closes the modal
    var closeLogin = document.getElementById("loginClose");
    var closeSingin = document.getElementById("singinClose");

    // When the user clicks on <span> (x), close the modal
    closeLogin.onclick = function() {
        modalLogIn.style.display = "none";
    }

    closeSingin.onclick = function() {
        modalSingIn.style.display = "none";
    }
    
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modalLogIn ) {
            modalLogIn.style.display = "none";
        }
    }
    
    //fill category select
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/ProjekatUES/api/categories",
        contentType:"application/json",
        success: function(data){
			for(var i = 0; i<data.length; i++){
                var category = data[i];
                $("#categoryToSubscribe").append(
                    '<option id="option'+category.id+'" value="'+category.id+'">'+category.name+'</option>'
                );
            }
        },
        error: function(request, options, error) {
            alert("error");
        },
        complete: function(){
            loader.style.display="none";
        }
    });
});

    function checkUserName() {
        var username = arguments[0];
        /*$.ajax({
            method : "post",
            url : "http://localhost:8080/ProjekatUES/api/users/checkUsername",
            data : {"username":"username"},
            success : function(){
                alert("Nema!");
            },
            error : function(){
                alert("error");
            },
            complete: function(){
                
            }
        });*/
    };

    function checkPass(){
        var match = $('#passwordCrud').val() == $('#passwordCrudRepeat').val();
        var textInputRepeat = document.getElementById("passwordCrudRepeat");
        if(match=="false"){
            $("#userSubmitUpdate").attr("disabled","disabled");
            textInputRepeat.style.border = "2px solid red";
        }/*else{
            $("#userSubmitUpdate").attr("disabled","false");
            textInputRepeat.style.border = 2px solid white;
        }*/
    };

    function crudUser(){
        var loader = document.getElementById("loader");
        loader.style.display="block";
        var modalSingIn = document.getElementById('myModalSingIn');
        var firstName = $("#firstNameCrud").val();
        var lastName = $("#lastNameCrud").val();
        var userName = $("#usernameCrud").val();
        var password = $("#passwordCrud").val();
        var role = $('input[name=typeOfUser]:checked').val();
        var category = $('#categoryToSubscribe').find(":selected").val();
        var categoryInt = null;
        if(category!="all"){
            categoryInt = category;
        }
        var jsonProduct = JSON.stringify({
            "firstName":firstName,
            "lastName":lastName,
            "userName":userName,
            "password":password,
            "roleInt":role,
            "categoryInt":categoryInt
        });
    
        $.ajax({
            method:"POST",
            url:"http://localhost:8080/ProjekatUES/api/users/add",
            contentType:"application/json",
            dataType:"application/json",
            data:jsonProduct,
            success: function(data){
                var user = data;
                var categoryOfUserSubscribe = "";
                var roleOfUser = "";

                if(user.category==null){
                    categoryOfUserSubscribe = '<td>All categories</td>';
                }else{
                    categoryOfUserSubscribe = '<td>'+user.category.name+'</td>';
                }

                if(user.uloga == 0){
                   roleOfUser = '<td>Admin</td>';
                }else{
                   roleOfUser = '<td>Subscriber</td>';
                }

                $('#usersTable').append(
                    '<tr id="row'+user.id+'">'+
                        '<td></td>'+
                        '<td>'+user.userFirstName+'</td>'+
                        '<td>'+user.userLastName+'</td>'+
                        '<td>'+user.userUserName+'</td>'+
                        categoryOfUserSubscribe+
                        roleOfUser+
                        '<td><a class="linkButtonGreen"  href="#" onclick="return theGreenPressedFunction('+user.id+');" >Change password </a></td>'+
                        '<td><a class="linkButtonYellow" id="yellowButton" href="#"  onclick="return theYellowPressedFunction('+user.id+');">Update</a></td>'+
                        '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return theRedPressedFunction('+user.id+');" >Delete</a></td>'+
                    '<tr>'
                );
            },
            error: function(request, option, error){
                console.log();
            },
            complete: function(){
                loader.style.display="none";
                modalSingIn.style.display="none";
            }
        });
    };

   
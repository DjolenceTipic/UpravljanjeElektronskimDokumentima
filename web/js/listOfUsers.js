var listOfUsers = [];
var serialNumber=1;

$(document).ready(function(){
    var loader = document.getElementById("loader");
    loader.style.display="block";
    
    // Get the modal
    var modal = document.getElementById('myModalPassword');
    var modalUpdate = document.getElementById('myModalUpdate');

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
        modalUpdate.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal || event.target == modalUpdate) {
            modal.style.display = "none";
            modalUpdate.style.direction = "none";
        }
    }
    
    //Fill categories for user
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/ProjekatUES/api/categories",
        contentType:"application/json",
        success: function(data){
			for(var i = 0; i<data.length; i++){
                var category = data[i];
                $("#categoryForSubscribe").append(
                    '<option id="option'+category.id+'" value="'+category.id+'">'+category.name+'</option>'
                );
            }
        },
        error: function(request, options, error) {
            alert("error");
        }
    });
    
   //Fill table with entities of Users
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/ProjekatUES/api/users",
        contentType:"application/json",
        success: function(data){
			if(data!=null){
                for(var i = 0; i< data.length; i++){
                    var user = data[i];
                    listOfUsers.push(user);
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
                            '<td>'+serialNumber+'</td>'+
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
                    serialNumber++;
                }
            }
        },
        error: function(request, options, error) {
            alert("error");
        },
        complete: function(){
            loader.style.display="none";
        }
    });
    
    //After ok is pressed, password will be changed
    $('#passwordChangeSubmit').click(function(event){
    	event.stopPropagation();
        var id = document.getElementById('idOfUser').value;
        var password = document.getElementById('passwordChange').value;
        idJson= null;
        var jsonProduct = JSON.stringify(
            {	
                "id":id,
                "password":password
            });
        
        $.ajax({
            method:"put",
            url : "http://localhost:8080/ProjekatUES/api/users/changePassword",
            data: jsonProduct,
            dataType : "json",
            contentType: "application/json",
            success : function(data) {
                alert(data);
            },
            error : function(request, options, error) {
                console.trace();
                request.toString();
                error.toString();
            }
        });
    }); 
    
    $('#userSubmitUpdate').click(function(event){
    	event.stopPropagation();
        var firstName = document.getElementById('firstNameUpdate').value;
        var lastName = document.getElementById('lastNameUpdate').value;
        var userName = document.getElementById('usernameUpdate').value;
        var id = document.getElementById('idUpdate').value;
        var role = $('#roleOfUpdatedUser').find(":selected").val();
        var category = $('#categoryForSubscribe').find(":selected").val();
        var catID = null;
        if (category === parseInt(category, 10)){
            catID = category;
        }
        var rowid = "row"+id;
        var jsonProduct = JSON.stringify(
            {	
                "id":id,
                "firstName":firstName,
                "lastName":lastName,
                "username":userName,
                "ulogaInt":role,
                "categoryInt": catID
            });
        
        $.ajax({
            method:"put",
            url : "http://localhost:8080/ProjekatUES/api/users/updateUser",
            data: jsonProduct,
            dataType : "json",
            contentType: "application/json",
            success : function(data) {
                var row = document.getElementById(rowid);
                row.parentNode.removeChild(row);
                var user = data;
                var categoryOfUserSubscribe = "";
                var roleOfUser = "";

                if(user.categoryInt==null){
                    categoryOfUserSubscribe = '<td>All categories</td>';
                }else{
                    categoryOfUserSubscribe = '<td>'+user.category.name+'</td>';
                }

                if(user.ulogaInt == 0){
                   roleOfUser = '<td>Admin</td>';
                }else{
                   roleOfUser = '<td>Subscriber</td>';
                }

                $('#usersTable').append(
                    '<tr id="row'+user.id+'">'+
                        '<td>'+serialNumber+'</td>'+
                        '<td>'+user.firstName+'</td>'+
                        '<td>'+user.lastName+'</td>'+
                        '<td>'+user.username+'</td>'+
                        categoryOfUserSubscribe+
                        roleOfUser+
                        '<td><a class="linkButtonGreen"  href="#" onclick="return theGreenPressedFunction('+user.id+');" >Change password </a></td>'+
                        '<td><a class="linkButtonYellow" id="yellowButton" href="#"  onclick="return theYellowPressedFunction('+user.id+');">Update</a></td>'+
                        '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return theRedPressedFunction('+user.id+');" >Delete</a></td>'+
                    '<tr>'
                );
                serialNumber++;
            },
            error : function(request, options, error) {
                console.trace();
            },
            complete : function(){
                modalUpdate.style.display = "none";
            }
        });
    }); 

});

    //Function after Delete button is presed
    function theRedPressedFunction () {
        var idLookFor = arguments[0];
        var rowid = "row"+idLookFor;

        var jsonUserDTO2 = JSON.stringify({
           "id":idLookFor
        });

        $.ajax({
            method:"put",
            url : "http://localhost:8080/ProjekatUES/api/users/delete",
            data: jsonUserDTO2,
            dataType : "json",
            contentType: "application/json",
            success : function(data) {
                var row = document.getElementById(rowid);
                row.parentNode.removeChild(row);
            },
            error : function(request, options, error) {
                console.trace()
            }
        });
    };

    //Function after Update button is presed
    function theYellowPressedFunction () {
        var idLookFor = arguments[0];
        var userLookedFor=null;
        var rowid = "row"+idLookFor;
        for(var i = 0; i<listOfUsers.length;i++){
            var user = listOfUsers[i];
            if(user.id==idLookFor){
                userLookedFor=user;
            }
        }
        var modal = document.getElementById('myModalUpdate');
        modal.style.display = "block";
        $("#idUpdate").val(idLookFor);
        $("#firstNameUpdate").val(userLookedFor.userFirstName);
        $("#lastNameUpdate").val(userLookedFor.userLastName);
        $("#usernameUpdate").val(userLookedFor.userUserName);
        if(userLookedFor.uloga==0){
            $("#optionAdmin").attr("selected","selected");
        }else{
            $("#oprionSubscriber").attr("selected","selected");
        }
        if(userLookedFor.category===null){
            $("optionAll").attr("selected", "seleted");
        }else{
            var option = document.getElementById("option"+userLookedFor.category.id+"");
            option.setAttribute('selected', true);
        }
    };

    //Function after ChangePassword button is pressed
    function theGreenPressedFunction () {
        var idLookFor = arguments[0];
        $("#idOfUser").val(idLookFor);
        var modal = document.getElementById('myModalPassword');
        modal.style.display = "block";
    }
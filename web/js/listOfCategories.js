var listOfCategories = [];
var serialNumber=1;
$(document).ready(function(){
    var loader = document.getElementById("loader");
    loader.style.display="block";
    
    // Get the modal
    var modal = document.getElementById('myModal');
    var modalUpdate = document.getElementById("myModalUpdate");
    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal 
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    
    //Fill table with Categories
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/ProjekatUES/api/categories",
        contentType:"application/json",
        success: function(data){
			if(data!=null){
                for(var i =0; i<data.length;i++){
                    var category = data[i];
                    listOfCategories.push(category);
                    $('#categoriesTable').append(
                        '<tr id="row'+category.id+'">'+
                            '<td>'+serialNumber+'</td>'+
                            '<td>'+category.name+'</td>'+
                            '<td><a class="linkButtonGreen"  href="searchPage.html?lanID='+category.id+'">Search </a></td>'+
                            '<td><a class="linkButtonYellow" id="yellowButton" href="#"  onclick="return theYellowPressedFunction('+category.id+');">Update</a></td>'+
                            '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return redPressedFunction('+category.id+');" >Delete</a></td>'+
                        '<tr>'
                    );
                    serialNumber++;
                }
            }
        },
        error: function(request, options, error) {
            alert("Error");
        },
        complete: function(){
            loader.style.display="none";
        }
    });
    
    //After ok is pressed, language will be added
    $('#categorytSubmit').click(function(event){
        loader.style.display="block";
    	event.stopPropagation();
        var naziv = document.getElementById('name').value;
        idJson = null;
        var jsonProduct = JSON.stringify(
            {	
                "id" : idJson,
                "name" : naziv,
                "deleted" : false
            });
        
        $.ajax({
            method:"post",
            url : "http://localhost:8080/ProjekatUES/api/categories/add",
            data: jsonProduct,
            dataType : "json",
            contentType: "application/json",
            success : function(data) {
                var category = data;
                listOfCategories.push(category);
                $('#categoriesTable').append(
                    '<tr id="row'+category.id+'">'+
                        '<td>'+serialNumber+'</td>'+
                        '<td>'+category.name+'</td>'+
                        '<td><a class="linkButtonGreen"  href="searchPage.html?lanID='+category.id+'">Search </a></td>'+
                        '<td><a class="linkButtonYellow" id="yellowButton" href="#"  onclick="return theYellowPressedFunction('+category.id+');">Update</a></td>'+
                        '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return redPressedFunction('+category.id+');" >Delete</a></td>'+
                    '<tr>'
                );
                serialNumber++;
            },
            error : function(request, options, error) {
                console.trace();
                request.toString();
                error.toString();
            },
            complete: function(){
                loader.style.display="none";
                modal.style.display = "none";
            }
        });
    }); 
    
    $('#categorytSubmitUpdate').click(function(event){
        loader.style.display="block";
    	event.stopPropagation();
        var naziv = document.getElementById('nameUpdate').value;
        var id = document.getElementById('idUpdate').value;
        var rowid = "row"+id;
        var jsonProduct = JSON.stringify(
            {	
                "id":id,
                "name":naziv,
                "deleted": false
            });
        
        $.ajax({
            method:"put",
            url : "http://localhost:8080/ProjekatUES/api/categories/update",
            data: jsonProduct,
            dataType : "json",
            contentType: "application/json",
            success : function(data) {
                var row = document.getElementById(rowid);
                row.parentNode.removeChild(row);
                var category = data;
                $('#categoriesTable').append(
                        '<tr id="row'+category.id+'">'+
                            '<td>'+serialNumber+'</td>'+
                            '<td>'+category.name+'</td>'+
                            '<td><a class="linkButtonGreen"  href="searchPage.html?lanID='+category.id+'">Search </a></td>'+
                            '<td><a class="linkButtonYellow" id="yellowButton" href="#"  onclick="return theYellowPressedFunction('+category.id+');">Update</a></td>'+
                            '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return redPressedFunction('+category.id+');" >Delete</a></td>'+
                        '<tr>'
                    );
                    serialNumber++;
            },
            error : function(request, options, error) {
                console.trace();
                request.toString();
                error.toString();
            },
            complete: function(){
                loader.style.display = "none";
                modalUpdate.style.display = "none";
            }
        });
    }); 
    
});

    //Function after Delete button is presed
    function redPressedFunction () {
        var loader = document.getElementById("loader");
        loader.style.display="block";
        var idLookFor = arguments[0];
        var categoryLookedFor = null;
        var rowid = "row"+idLookFor;
        for(var i = 0; i<listOfCategories.length;i++){
            var category = listOfCategories[i];
            if(category.id==idLookFor){
                categoryLookedFor=category;
            }
        }
        var jsonCategory = JSON.stringify({
            "id" : idLookFor,
            "name" : categoryLookedFor.name,
            "deleted" : true
        });

        $.ajax({
            method:"put",
            url : "http://localhost:8080/ProjekatUES/api/categories/update",
            data: jsonCategory,
            dataType : "json",
            contentType: "application/json",
            success : function(data) {
                var row = document.getElementById(rowid);
                row.parentNode.removeChild(row);
            },
            error : function(request, options, error) {
                console.trace()
            },
            complete: function(){
                loader.style.display="none";
            }
        });
    };

    //Function after Update button is presed
    function theYellowPressedFunction () {
        var idLookFor = arguments[0];
        var categoryLookedFor=null;
        var rowid = "row"+idLookFor;
        for(var i = 0; i<listOfCategories.length;i++){
            var category = listOfCategories[i];
            if(category.id==idLookFor){
                categoryLookedFor=category;
            }
        }
        var modal = document.getElementById('myModalUpdate');
        modal.style.display = "block";
        $("#idUpdate").val(idLookFor);
        $("#nameUpdate").val(categoryLookedFor.name);

    };
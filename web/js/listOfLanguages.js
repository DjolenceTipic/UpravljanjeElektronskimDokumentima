var listOfLanguages = [];
var serialNumber=1;
$(document).ready(function(){
    var loader = document.getElementById("loader");
    loader.style.display="block";
    // Get the modal
    var modal = document.getElementById('myModal');
    var modalUpdate = document.getElementById('myModalUpdate');

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
        modalUpdate.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal || event.target == modalUpdate) {
            modal.style.display = "none";
            modalUpdate.style.direction = "none";
        }
    }
    
    //Fill table with entities of Languages
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/ProjekatUES/api/languages",
        contentType:"application/json",
        success: function(data){
			if(data!=null){
                for(var i =0; i<data.length;i++){
                    var language = data[i];
                    listOfLanguages.push(language);
                    $('#languageTable').append(
                        '<tr id="row'+language.language_id+'">'+
                            '<td>'+serialNumber+'</td>'+
                            '<td>'+language.languageName+'</td>'+
                            '<td><a class="linkButtonGreen"  href="searchPage.html?lanID='+language.language_id+'">Search </a></td>'+
                            '<td><a class="linkButtonYellow" id="yellowButton" href="#"  onclick="return theYellowPressedFunction('+language.language_id+');">Update</a></td>'+
                            '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return theRedPressedFunction('+language.language_id+');" >Delete</a></td>'+
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
    $('#languageSubmit').click(function(event){
        loader.style.display="block";
    	event.stopPropagation();
        var naziv = document.getElementById('name').value;
        idJson= null;
        var jsonProduct = JSON.stringify(
            {	
                "language_id":idJson,
                "languageName":naziv,
                "deleted": false
            });
        
        $.ajax({
            method:"post",
            url : "http://localhost:8080/ProjekatUES/api/languages/add",
            data: jsonProduct,
            dataType : "json",
            contentType: "application/json",
            success : function(data) {
                $('#languageTable').append(
                        '<tr id="row'+data.language_id+'">'+
                            '<td>'+serialNumber+'</td>'+
                            '<td>'+data.languageName+'</td>'+
                            '<td><a class="linkButtonGreen"  href="searchPage.html?id='+data.language_id+'">Search </a></td>'+
                            '<td><a class="linkButtonYellow" id="yellowButton" href="updateMovie.html?id='+data.language_id+'">Update</a></td>'+
                            '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return theRedPressedFunction('+data.language_id+');" >Delete</a></td>'+
                        '<tr>'
                    );
                modal.style.display = "none";
            },
            error : function(request, options, error) {
                console.trace();
                request.toString();
                error.toString();
            },
            complete: function(){
                loader.style.display="none";
            }
        });
    }); 
    
    $('#languageSubmitUpdate').click(function(event){
        loader.style.display="block";
    	event.stopPropagation();
        var naziv = document.getElementById('nameUpdate').value;
        var id = document.getElementById('idUpdate').value;
        var rowid = "row"+id;
        var jsonProduct = JSON.stringify(
            {	
                "language_id":id,
                "languageName":naziv,
                "deleted": false
            });
        
        $.ajax({
            method:"put",
            url : "http://localhost:8080/ProjekatUES/api/languages/update",
            data: jsonProduct,
            dataType : "json",
            contentType: "application/json",
            success : function(data) {
                var row = document.getElementById(rowid);
                row.parentNode.removeChild(row);
                $('#languageTable').append(
                        '<tr id="row'+data.language_id+'">'+
                            '<td>'+serialNumber+'</td>'+
                            '<td>'+data.languageName+'</td>'+
                            '<td><a class="linkButtonGreen"  href="searchPage.html?id='+data.language_id+'">Search </a></td>'+
                            '<td><a class="linkButtonYellow" id="yellowButton" href="updateMovie.html?id='+data.language_id+'">Update</a></td>'+
                            '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return theRedPressedFunction('+data.language_id+');" >Delete</a></td>'+
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
    function theRedPressedFunction () {
        var loader = document.getElementById("loader");
        loader.style.display="block";
        var idLookFor = arguments[0];
        var languageLookedFor=null;
        var rowid = "row"+idLookFor;
        for(var i = 0; i<listOfLanguages.length;i++){
            var langauge = listOfLanguages[i];
            if(langauge.language_id==idLookFor){
                languageLookedFor=langauge;
            }
        }
        var jsonCategory = JSON.stringify({
            "language_id":idLookFor,
            "languageName": languageLookedFor.languageName,
            "deleted" : true
        });


        $.ajax({
            method:"put",
            url : "http://localhost:8080/ProjekatUES/api/languages/update",
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

        return false;
    };

    //Function after Update button is presed
    function theYellowPressedFunction () {
        var idLookFor = arguments[0];
        var languageLookedFor=null;
        var rowid = "row"+idLookFor;
        for(var i = 0; i<listOfLanguages.length;i++){
            var langauge = listOfLanguages[i];
            if(langauge.language_id==idLookFor){
                languageLookedFor=langauge;
            }
        }
        
        var modal = document.getElementById('myModalUpdate');
        modal.style.display = "block";
        $("#idUpdate").val(idLookFor);
        $("#nameUpdate").val(languageLookedFor.languageName);

    };
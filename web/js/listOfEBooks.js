var keywordsString = "";
var serialNumber=1;
var listOfEbooks = [];

$(document).ready(function () {
    var loader = document.getElementById("loader");
    loader.style.display="block";
    //Fill category select
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/ProjekatUES/api/categories",
        contentType:"application/json",
        success: function(data){
			if(data!=null){
                for(var i =0; i<data.length;i++){
                    var category = data[i];
                    $("#categorySelectForEBook").append(
                        '<option id="option'+category.id+'" value="'+category.id+'">'+category.name+'</option>'
                    );
                }
            }
        },
        error: function(request, options, error) {
            alert(error);
        }
    });
    
    //Fill language select
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/ProjekatUES/api/languages",
        contentType:"application/json",
        success: function(data){
			if(data!=null){
                for(var i =0; i<data.length;i++){
                    var language = data[i];
                    $("#languageSelectForEBook").append(
                        '<option id="option'+language.language_id+'" value="'+language.language_id+'">'+language.languageName+'</option>'
                    );
                }
            }
        },
        error: function(request, options, error) {
            alert(error);
        }
    });
    
    //Fill tables with eBooks
    $.ajax({
        method : "get",
        url : "http://localhost:8080/ProjekatUES/api/ebooks/",
        contentType : "application/json",
        success: function(data){
            if(data!=null){
                for(var i = 0; i<data.length; i++){
                    var ebook = data[i];
                    $("#eBookTable").append(
                        '<tr id="row'+ebook.id+'">'+
                            '<td>'+serialNumber+'</td>'+
                            '<td>'+ebook.eBookTitle+'</td>'+
                            '<td>'+ebook.eBookAuthor+'</td>'+
                            '<td>'+ebook.eBookKeywords+'</td>'+
                            '<td>'+ebook.eBookPublicationYear+'</td>'+
                            '<td>'+ebook.language.languageName+'</td>'+
                            '<td>'+ebook.category.name+'</td>'+
                            '<td>'+ebook.user.userUserName+'</td>'+
                            '<td><a class="linkButtonYellow" id="yellowButton" href="#"  onclick="return theYellowPressedFunction('+ebook.id+');">Update</a></td>'+
                            '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return theRedPressedFunction('+ebook.id+');" >Delete</a></td>'+
                        '<tr>'
                    );
                    serialNumber++;
                }
            }
        },
        error: function(error){
            
        },
        complete: function(){
                loader.style.display="none";
        }
    });
    
    // Get the modal
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");
    var buttonUpload = document.getElementById("uploadSubmit");
    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on the button, open the modal 
    btn.onclick = function () {
        modal.style.display = "block";
    };

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    };
    
    //Upload file
    buttonUpload.onclick = function () {
        var table = document.getElementById("notVisible");
        var formData = new FormData($('#eBookCrud')[0]);
        formData.append('tax_file', $('input[type=file]')[0].files[0]);
        var loader = document.getElementById("loader");
        loader.style.display="block";
        $.ajax({
            method: "POST",
            url: "Upload",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                var entity = JSON.parse(data);
                $("#titleOfEBook").val(entity.title);
                $("#authorOfEBook").val(entity.author);
                $("#fileName").val(entity.fileName);
                table.style.display = "block";
            },
            error: function (error) {
                alert("nije uspeo upload");
            },
            complete: function(){
                loader.style.display="none";
            }
        });
    };
    
    //Add keyword
    var addKeywordButton = document.getElementById("addKeyword");
    addKeywordButton.onclick = function (){
        var keyword = $("#keywordsOfEBook").val();
        alert(keyword);
        var keywordList = document.getElementById("keywordsForList");
        if(keywordList.innerHTML===""){
            keywordList.innerHTML=keyword;
            keywordsString = keyword;
            $("#keywordsOfEBook").val("");
        }else{
            keywordList.innerHTML+=", "+keyword;
            keywordsString+=", "+keyword;
             $("#keywordsOfEBook").val("");
        }
    }
    
    var buttonSubmitEBook = document.getElementById("addSubmit");
    buttonSubmitEBook.onclick = function(){
        alert(keywordsString);
        var title = $("#titleOfEBook").val();
        var author = $("#authorOfEBook").val();
        var fileName = $("#fileName").val();
        var languageID = $('#languageSelectForEBook').find(":selected").val();
        var categoryID = $('#categorySelectForEBook').find(":selected").val();
        var year = $("#publicationYearOfEBook").val();
        
        var jsonProduct = JSON.stringify({
            "fileName":fileName,
            "author":author,
            "title":title,
            "keywords":keywordsString,
            "languageID":languageID,
            "categoryID":categoryID,
            "publicationYear":year+"",
            "userID":"1"
        });
        
        var loader = document.getElementById("loader");
        loader.style.display="block";
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/ProjekatUES/api/ebooks/add",
            data: jsonProduct,
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                var ebook = data;
                $("#eBookTable").append(
                    '<tr id="row'+ebook.id+'">'+
                        '<td>'+serialNumber+'</td>'+
                        '<td>'+ebook.eBookTitle+'</td>'+
                        '<td>'+ebook.eBookAuthor+'</td>'+
                        '<td>'+ebook.eBookKeywords+'</td>'+
                        '<td>'+ebook.eBookPublicationYear+'</td>'+
                        '<td>'+ebook.language.languageName+'</td>'+
                        '<td>'+ebook.category.name+'</td>'+
                        '<td>'+ebook.user.userUserName+'</td>'+
                        '<td><a class="linkButtonYellow" id="yellowButton" href="#"  onclick="return theYellowPressedFunction('+ebook.id+');">Update</a></td>'+
                        '<td><a class="linkButtonRed" id="redButton" href="#" onclick="return theRedPressedFunction('+ebook.id+');" >Delete</a></td>'+
                    '<tr>'
                );
                serialNumber++;
            },
            error: function (error) {
                alert("promasio");
            },
            complete: function(){
                loader.style.display="none";
            }
        });
    }
});

//Function after Delete button is presed
    function theRedPressedFunction () {
        /*var idLookFor = arguments[0];
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

        return false;*/
    };

    //Function after Update button is presed
    function theYellowPressedFunction () {
        /*var idLookFor = arguments[0];
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
        alert(userLookedFor.uloga);
        if(userLookedFor.uloga==0){
            $("#optionAdmin").attr("selected","selected");
        }else{
            $("#oprionSubscriber").attr("selected","selected");
        }
        alert(userLookedFor.category.id);
        if(userLookedFor.category.id==null){
            $("optionAll").attr("selected", "seleted");
        }else{
            $("#option"+userLookedFor.category.id+"").attr("selected", "seleted");
        }*/
    };
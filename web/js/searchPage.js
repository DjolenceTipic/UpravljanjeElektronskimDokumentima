$(document).ready(function(){
    var buttonSearch = document.getElementById("buttonSearch");
    
            var author = null;
            var authorST = null;
            var authorSC = null;
            var title = null;
            var titleST = null;
            var titleSC = null;
            var content = null;
            var contentST = null;
            var contentSC = null;
            var language = null;
            var languageST = null;
            var languageSC = null;
            var keyword = null;
            var keywordST = null;
            var keywordSC = null;
    
    buttonSearch.onclick = function(){
        var loader = document.getElementById("loader");
        loader.style.display="block";
        
        if(document.getElementById('authorCB').checked){
            author = $("#authorForSearch").val();
            authorST = $("#authorST").find(":selected").val();
            authorSC = $("#authorSC").find(":selected").val();
        }
        if(document.getElementById('titleCB').checked){
            title = $("#titleForSearch").val();
            titleST = $("#titleST").find(":selected").val();
            titleSC = $("#titleSC").find(":selected").val();
        }
        if(document.getElementById('contentCB').checked){
            content = $("#contentForSearch").val;
            contentST = $("#contentST").find(":selected").val();
            contentSC = $("#contentSC").find(":selected").val();
        }
        if(document.getElementById('languageCB').checked){
            language = $("#languageForSearch").val;
            languageST = $("#languageST").find(":selected").val();
            languageSC = $("#languageSC").find(":selected").val();
        }
        if(document.getElementById('keywordCB').checked){
            keyword = $("#keywordForSearch").val;
            keywordST = $("#keywordST").find(":selected").val();
            keywordSC = $("#keywordSC").find(":selected").val();
        }
        
        var jsonProduct = JSON.stringify({
            "text":content,
            "textSearchType":contentST,
            "textSearchCondition":contentSC,
            "author":author,
            "auhtorSearchType":authorST,
            "authorSearchCondition":authorSC,
            "keywords":keyword,
            "keywordsSearchType":keywordST,
            "keywordsSearchCondition":keywordSC,
            "title":title,
            "titleSearchType":titleST,
            "titleSearchCondition":titleSC,
            "language":language,
            "languageSearchType":languageST,
            "languageSearchCondition":languageSC
        });
        
        var eBookSearchResult = $('#eBookSearchResult');
        $.ajax({
            method : "post",
            url : "http://localhost:8080/ProjekatUES/api/search",
            data : jsonProduct,
            dataType : "json",
            contentType : "aplication/json",
            success: function(data){
                alert(data.length);
                for(var i=0; i<data.length; i++) {
				var eBook = data[i];
				eBookSearchResult.append(
                    '<div class="oneProduct">'+
                        '<div class="detailsOfBook">'+
                            '<p id="priceDet">Book title: '+eBook.eBookTitle+'</p>'+ 
                        '</div>'+
                        '<div id="detailsOfBook">'+
                            '<p id="priceDet">Book author: '+eBook.eBookAuthor+'</p>'+
                        '</div>'+
                        '<div id="detailsOfBook">'+
                            '<p id="priceDet">Book keywords: '+eBook.eBookKeywords+'</p>'+
                        '</div>'+
                        '<div id="detailsOfBook">'+
                            '<p id="priceDet">Book publication year: '+eBook.eBookPublicationYear+'</p>'+
                        '</div>'+
                        '<div id="detailsOfBook">'+
                            '<p id="priceDet">Language of book: '+eBook.language.languageName+'</p>'+
                        '</div>'+
                        '<div id="detailsOfBook">'+
                            '<p id="priceDet">Category of book: '+eBook.category.name+'</p>'+
                        '</div>'+
                        '<div class="detail">'+
                            '<p id ="detText">komponenta.opis<a href="#">Detaljnije...</a>'+
                        '</p>'+
                        '</div>'+

                        '<div id="btnDetail">'+
                        '<a href="#"><p> DOWNLOAD </p></a>'+
                        '</div>'+
				    '</div>');
                }
            },
            error: function(error){
                alert("promasio");
            },
            complete: function(){
                loader.style.display="none";
            }
        });
    }
    
});
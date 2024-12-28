$(document).ready(function(){
    $("#commodity").click(function(){
        document.getElementById("commodity-content").removeAttribute("hidden");
        document.getElementById("category-content").setAttribute("hidden", "hidden");
    });

    $("#category").click(function(){
        document.getElementById("category-content").removeAttribute("hidden");
        document.getElementById("commodity-content").setAttribute("hidden", "hidden");
    });
});
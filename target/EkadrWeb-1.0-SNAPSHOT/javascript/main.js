var label = document.getElementById("wrapper");
var uploadbtn = document.getElementById("upload");

function checkfile() {
    var name = document.getElementById('upload'); 
     
    if(name.files.length == 0){
        
        label.setAttribute("data-text","Select logo");
    }
    else{
        if(name.files[0].size >= 1000000){
            alert("File can not be large than 1mb");
            name.remove();
            var imageclear = document.createElement("input");
            imageclear.id ="upload";
            imageclear.setAttribute("onchange","checkfile()")
            imageclear.type = 'file';
            imageclear.class ='field';
            imageclear.accept = 'image/*';
            label.append(imageclear);
            label.setAttribute("data-text","Select logo");
        }
        else{
            label.setAttribute("data-text","Logo selected");           
        }
    }
  };



  //Mobile
  var hamburgerbtn = document.getElementById('hamburgerbtn');
  var topbar = document.getElementById('topbar');
  hamburgerbtn.onclick = function(){
        if(hamburgerbtn.value == "close"){
            topbar.style.zIndex = "5";
            topbar.style.transform = "translateY(0%)";
            hamburgerbtn.setAttribute("value","open");
        } 
        else if(hamburgerbtn.value == "open"){
                topbar.style.transition = "transform .3s";
                topbar.style.transform = "translateY(-100%)";
                topbar.style.zIndex = "-2";
                hamburgerbtn.setAttribute("value","close");
          } 
  };
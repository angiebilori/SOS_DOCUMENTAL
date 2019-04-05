function processFiles(files) {

    var file = files[0];
    var reader = new FileReader();

    reader.onload = function (e) {
        // Cuando éste evento se dispara, los datos están ya disponibles.
        // Se trata de copiarlos a una área <div> en la página.
        //var output = document.getElementById("frame");
        //mm.data = e.target.result;
       // var ruta=e.target.result;
        
        mm.src = e.target.result; //Obtiene la url del archivo y la envia al src 
        var nombreDoc = document.getElementById('files').files[0].name;
        var ext =nombreDoc.split('.')[1];
        //ext =ext.split('/')[0]; 
        extt.value = ("."+ext);
    }
    reader.readAsDataURL(file);
}




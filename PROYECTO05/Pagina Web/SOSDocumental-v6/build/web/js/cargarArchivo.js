function processFiles(files) {

    var file = files[0];
    var reader = new FileReader();

    reader.onload = function (e) {
        // Cuando éste evento se dispara, los datos están ya disponibles.
        // Se trata de copiarlos a una área <div> en la página.
        //var output = document.getElementById("frame");
        //mm.data = e.target.result;
        // var ruta=e.target.result;

        var nombreDoc = document.getElementById('files').files[0].name;
        var ext = "";

        //Condicional para obtener la extencion del archivo
        if (nombreDoc.lastIndexOf(".") > 0) {
            ext = nombreDoc.substring(nombreDoc.lastIndexOf(".") + 1, nombreDoc.length);
            documentoTipo.value = ("." + ext);
            tipoDocumento.value = ("." + ext);

        }
                    
        //var ext = nombreDoc.split('.')[1];
        //ext =ext.split('/')[0]; 

        //Archivos que se puede mostrar
        var extVisibles = ["pdf", "PDF", "jpeg", "JPEG", "jpg", "JPG", "png", "PNG", "txt", "TXT", "gif", "GIF", "tiff", "TIFF", "tif", "TIF", "raw", "RAW", "bmp", "BMP"];

        //Recorrer array de los archivos que pueden ser visibles
        for (var i = 0; i <= extVisibles.length; i++) {

            //Identifica si el archivo se puede mostrar
            if (ext == extVisibles[i]) {
                //mm.src = e.target.result; //Obtiene la url del archivo y la envia al src 
                break;
            } else {
               // mm.src = "";
            }
        }
    }
    reader.readAsDataURL(file);
}




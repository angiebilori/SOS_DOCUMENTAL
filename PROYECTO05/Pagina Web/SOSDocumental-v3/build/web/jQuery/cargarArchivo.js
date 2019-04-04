function processFiles(files) {

    var file = files[0];
    var reader = new FileReader();

    reader.onload = function (e) {
        // Cuando éste evento se dispara, los datos están ya disponibles.
        // Se trata de copiarlos a una área <div> en la página.
        //var output = document.getElementById("frame");
        //mm.data = e.target.result;
        mm.src = e.target.result; //Obtiene la url del archivo y la envia al src
    }
    reader.readAsDataURL(file);
}

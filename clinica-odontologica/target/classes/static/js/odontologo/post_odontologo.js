window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva paciente
    const formulario = document.querySelector('#add_new_odontologo');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
       //creamos un JSON que tendrá los datos de la nueva película
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        console.log("forma data a guardar " + formData);

        //invocamos utilizando la función fetch la API pacientes con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = '/odontologos';

        const formDataJson =  JSON.stringify(formData);

        console.log("Datos como Json " + formDataJson);

        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: formDataJson
        }

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: formDataJson,
        })
            .then(response => {

                alert(response.status)
                if(response.ok){
                    console.log("Respuesta ok")
                    let successAlert = '<div class="alert alert-success alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong></strong> Odontólogo creado </div>'
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    response.json();
                    //window.location.href = "http://localhost:8080/";
                }else if(response.status === 400){
                    alert("Error - No se pudo actualizar la persona")
                }
            })
            .catch(error => {
                    alert(error)
                    //Si hay algun error se muestra un mensaje diciendo que la paciente
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otra paciente
                     })
    });


    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#dni').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/pacienteList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});
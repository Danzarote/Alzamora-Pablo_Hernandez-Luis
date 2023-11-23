window.addEventListener('load', function () {
    (function(){

        //con fetch invocamos a la API de pacientes con el método GET
        //nos devolverá un JSON con una colección de pacientes
        const url = '/pacientes';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                //recorremos la colección de pacientes del JSON
                for(paciente of data){
                    //por cada paciente armaremos una fila de la tabla
                    //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la paciente
                    var table = document.getElementById("pacienteTable");
                    var pacienteRow =table.insertRow();
                    let tr_id = 'tr_' + paciente.id;
                    pacienteRow.id = tr_id;

                    //por cada paciente creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                    //dicho boton invocara a la funcion de java script deleteByKey que se encargará
                    //de llamar a la API para eliminar una paciente
                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                        ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                        '&times' +
                        '</button>';

                    //por cada paciente creamos un boton que muestra el id y que al hacerle clic invocará
                    //a la función de java script findBy que se encargará de buscar la paciente que queremos
                    //modificar y mostrar los datos de la misma en un formulario.
                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                        ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                        paciente.id +
                        '</button>';

                    //armamos cada columna de la fila
                    //como primer columna pondremos el boton modificar
                    //luego los datos de la paciente
                    //como ultima columna el boton eliminar
                    pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                        '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_nombre\">' + paciente.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                        '<td class=\"td_nombre\">' + paciente.fechaIngreso + '</td>' +
                        '<td>' + deleteButton + '</td>';

                };

            })
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/pacienteList.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })


})

window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva paciente
    const formulario = document.querySelector('#add_new_paciente');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        //creamos un JSON que tendrá los datos de la nueva película
        const formData = {
            "nombre": document.querySelector('#nombre').value,
            "apellido": document.querySelector('#apellido').value,
            "dni": document.querySelector('#dni').value,
            "fechaIngreso": document.querySelector('#fechaIngreso').value
        };
        //invocamos utilizando la función fetch la API pacientes con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = '/pacientes/registrar';

        const formDataJson =  JSON.stringify(formData);

        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: formDataJson
        }
        console.log(JSON.stringify(formData));

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: formDataJson,
        })
            .then(response => response.json())
            .then(data => {
                //Si no hay ningun error se muestra un mensaje diciendo que la paciente
                //se agrego bien

                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Paciente agregado </strong></div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";


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
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#fechaIngreso').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/pacienteList.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
    })();
});


window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la paciente
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let pacienteId = document.querySelector('#id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una paciente nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,

        };

        //invocamos utilizando la función fetch la API pacientes con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/pacientes/actualizar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
            .then(response => {
                alert(response.status)
                if(response.ok){
                    console.log("Respuesta ok")
                    let successAlert = '<div class="alert alert-success alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong></strong> Paciente actualizado </div>'
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    response.json();
                    window.location.href = "http://localhost:8081/";
                }else if(response.status === 400){
                    alert("Error - No se pudo actualizar el paciente")
                }

            })
            .catch(error => {
                console.log(error);
                alert("Error - No se pudo actualizar el paciente")
            });

    })
})

//Es la funcion que se invoca cuando se hace click sobre el id de una paciente del listado
//se encarga de llenar el formulario con los datos de la paciente
//que se desea modificar
function findBy(id) {
    const url = '/pacientes'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('#id').value = paciente.id;
            document.querySelector('#nombre').value = paciente.nombre;
            document.querySelector('#apellido').value = paciente.apellido;
            document.querySelector('#dni').value = paciente.dni;
            document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
            //el formulario por default esta oculto y al editar se habilita
            document.querySelector('#div_paciente_updating').style.display = "block";
        }).catch(error => {
        alert("Error: " + error);
    })
}

function deleteBy(id)
{
    //con fetch invocamos a la API de pacientes con el método DELETE
    //pasandole el id en la URL
    console.log("eliminando Paciente " + id)
    const url = '/pacientes/eliminar/'+ id;
    const settings = {
        method: 'DELETE'
    }
    fetch(url,settings)
        .then(response =>{
            response.json();
            //borrar la fila de la paciente eliminada
            let row_id = `#tr_${id}`;
            document.querySelector(row_id).remove();
        }).catch(error =>alert("Error al borrar"));




}

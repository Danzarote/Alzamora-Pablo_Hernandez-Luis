window.addEventListener("load", function () {
  const form = document.forms[0];
   const urlPacientes = "http://localhost:8080/pacientes/listar";
   const pacienteCreado = document.querySelector(".pacientes");

  form.addEventListener("submit", function (event) {
    event.preventDefault();
    consultarPacientes();
  })

    function consultarPacientes() {
      const settings = {
        method: "GET",
      };
      console.log("consultando pacientes");
      fetch(urlPacientes, settings)
        .then((response) => response.json())
        .then((paciente) => {
          console.log("Estos son todos los pacientes");
          console.log(paciente);
          renderizarPacientes(paciente)

        })

        .catch((err) => {
          console.warn("Promesa rechazada");
          console.log(err);
          if (err.status == 400) {
            console.warn("id inválido");
            alert("id inválido");
          } else if (err.status == 401) {
            console.warn("Requiere Autorización");
            alert("Requiere Autorización");
          } else if (err.status == 404) {
            console.warn("paciente inexistente");
            alert("paciente inexistente");
          } else {
            console.error("Error del servidor");
            alert("Error del servidor");
          }
        });
    }

    function eliminarPaciente(idPaciente) {
      const urlEliminarPaciente = `http://localhost:8080/pacientes/eliminar/${idPaciente}`;
      const settings = {
        method: "DELETE",
      };
  
      fetch(urlEliminarPaciente, settings)
        .then((response) => response.json())
        .then(() => {
          console.log(`Paciente con ID ${idPaciente} eliminado correctamente`);
          consultarPacientes();
        })
        .catch((err) => {
          console.error("Error al eliminar el paciente");
          console.error(err);
          alert("Error al eliminar el paciente");
        });
    }

    function renderizarPacientes(paciente) {
      console.log(pacienteCreado);
      pacienteCreado.innerHTML = "";

      const listaPacientes = document.createElement("ul");

      paciente.forEach((paciente) => {
        const elementoPaciente = document.createElement("li");
        elementoPaciente.classList.add('skeleton-container');
        elementoPaciente.innerHTML = `
            <ul>
              <li>ID: ${paciente.id} </li>
              <li>Nombre: ${paciente.nombre} </li>
              <li>Apellido: ${paciente.apellido} </li>
              <li>DNI: ${paciente.dni} </li>
              <li>Fecha de Ingreso: ${paciente.fechaIngreso} </li>
            </ul>
            <ul>
                <li>Calle: ${paciente.domicilioSalidaDto.calle}</li>
                <li>Número: ${paciente.domicilioSalidaDto.numero}</li>
                <li>Localidad: ${paciente.domicilioSalidaDto.localidad}</li>
                <li>Provincia: ${paciente.domicilioSalidaDto.provincia}</li>
            </ul>
        <button
              type="button"
              id="eliminarPaciente"
            >
              Eliminar
            </button>
            `;
        const botonEliminar = elementoPaciente.querySelector("#eliminarPaciente");
        botonEliminar.addEventListener("click", function() {
        eliminarPaciente(paciente.id);
});
        listaPacientes.appendChild(elementoPaciente);
    });

    pacienteCreado.appendChild(listaPacientes);
}



  })
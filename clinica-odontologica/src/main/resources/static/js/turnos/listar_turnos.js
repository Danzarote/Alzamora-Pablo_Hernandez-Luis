window.addEventListener("load", function () {
  const form = document.forms[0];
  const urlTurnos = "http://localhost:8080/turnos/listar";
  const turnoCreado = document.querySelector(".turnos");

  form.addEventListener("submit", function (event) {
    event.preventDefault();
    consultarTurnos();
  });

  function consultarTurnos() {
    const settings = {
      method: "GET",
    };
    console.log("Consultando turnos");
    fetch(urlTurnos, settings)
      .then((response) => response.json())
      .then((turno) => {
        console.log("Estos son todos los turnos creados");
        console.log(turno);
        renderizarTurnos(turno);
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
          console.warn("Turno inexistente");
          alert("Turno inexistente");
        } else {
          console.error("Error del servidor");
          alert("Error del servidor");
        }
      });
  }

  function eliminarTurno(idTurno) {
    const urlEliminarTurno = `http://localhost:8080/turnos/eliminar/${idTurno}`;
    const settings = {
      method: "DELETE",
    };

    fetch(urlEliminarTurno, settings)
      .then((response) => response.json())
      .then(() => {
        console.log(`Turno con ID ${idTurno} eliminado correctamente`);
        consultarTurnos();
      })
      .catch((err) => {
        console.error("Error al eliminar el turno");
        console.error(err);
        alert("Error al eliminar el turno");
      });
  }

  function renderizarTurnos(turno) {
    console.log(turnoCreado);
    turnoCreado.innerHTML = "";

    const listaTurnos = document.createElement("ul");

    turno.forEach((turno) => {
      const fechaYhora = new Date(turno.fechaYHora);
      const dia = fechaYhora.getDate();
      const mes = fechaYhora.getMonth() + 1;
      const anio = fechaYhora.getFullYear();
      const horas = fechaYhora.getHours();
      const minutos = fechaYhora.getMinutes();
      const fechaFormateadaDiaMesAnio = `${dia}/${mes}/${anio}`;
      const fechaFormateadaHorasMinutos =
        minutos < 10 ? `${horas}:0${minutos}` : `${horas}:${minutos}`;

      const elementoTurno = document.createElement("li");
      elementoTurno.classList.add("skeleton-container");
      elementoTurno.innerHTML = `
        
            <ul>
              <li>ID Odontólogo: ${turno.idOdontologo}</li>
              <li>Nombre: ${turno.nombreOdontologo}</li>
              <li>Apellido: ${turno.apellidoOdontologo}</li>
            </ul>
            <ul>
              <li>ID Turno: ${turno.id} </li>
              <li>Fecha: ${fechaFormateadaDiaMesAnio}</li>
              <li>Hora: ${fechaFormateadaHorasMinutos} Hrs</li>
            </ul>
            <ul>
              <li>ID Paciente: ${turno.idPaciente}</li>
              <li>Nombre: ${turno.nombrePaciente}</li>
              <li>Apellido: ${turno.apellidoPaciente}</li>
            </ul>
            <button
              type="button"
              id="eliminarTurno"
            >
              Eliminar
            </button>
        `;
        const botonEliminar = elementoTurno.querySelector("#eliminarTurno");
        botonEliminar.addEventListener("click", function() {
        eliminarTurno(turno.id);
});
      listaTurnos.appendChild(elementoTurno);
    });

    turnoCreado.appendChild(listaTurnos);
  }
});
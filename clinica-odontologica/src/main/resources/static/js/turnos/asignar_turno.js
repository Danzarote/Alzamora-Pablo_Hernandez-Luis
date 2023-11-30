window.addEventListener("load", function () {
    const idOdontologo = document.querySelector("#idOdontologo");
    const idPaciente = document.querySelector("#idPaciente");
    const fechaYHora = document.querySelector("#fechaYHora");
    const urlAgregar = "http://localhost:8080/turnos/asignar"
    const formCrearTurno = document.forms[0];
  
    formCrearTurno.addEventListener("submit", function (event) {
      event.preventDefault();
  
      const payload = {
       idPaciente: idPaciente.value,
        idOdontologo: idOdontologo.value,
        fechaYHora: fechaYHora.value,
        }
  
      console.log("Payload:", payload);
  
      const settings = {
        method: "POST",
        body: JSON.stringify(payload),
        headers: {
          "Content-Type": "application/json",
        },
      };
      console.log("Enviando solicitud...");
      
      fetch(urlAgregar, settings)
        .then((response) => response.json())
        .then((turno) => {
          console.log("Respuesta del servidor:", turno);
       ;
          window.location.href="./listar_turnos.html"
  
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
  
  
        });
    });
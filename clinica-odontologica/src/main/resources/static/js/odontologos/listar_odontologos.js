window.addEventListener("load", function () {
  const form = document.forms[0];
   const urlOdontologos = "http://localhost:8080/odontologos/listar";
   const odontologoCreado = document.querySelector(".odontologos");

  form.addEventListener("submit", function (event) {
    event.preventDefault();
    consultarOdontologos();
  })

    function consultarOdontologos() {
      const settings = {
        method: "GET",
      };
      console.log("consultando odontólogos");
      fetch(urlOdontologos, settings)
        .then((response) => response.json())
        .then((odontologo) => {
          console.log("Estos son todos los odontólogos");
          console.log(odontologo);
          renderizarOdontologos(odontologo)

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
            console.warn("odontologo inexistente");
            alert("odontologo inexistente");
          } else {
            console.error("Error del servidor");
            alert("Error del servidor");
          }
        });
    }

    function eliminarOdontologo(idOdontologo) {
      const urlEliminarOdontologo = `http://localhost:8080/odontologos/eliminar/${idOdontologo}`;
      const settings = {
        method: "DELETE",
      };
  
      fetch(urlEliminarOdontologo, settings)
        .then((response) => response.json())
        .then(() => {
          console.log(`Odontólogo con ID ${idOdontologo} eliminado correctamente`);
          consultarOdontologos();
        })
        .catch((err) => {
          console.error("Error al eliminar el odontólogo");
          console.error(err);
          alert("Error al eliminar el odontólogo");
        });
    }
    function renderizarOdontologos(odontologo) {
      console.log(odontologoCreado);
      odontologoCreado.innerHTML = "";

      const listaOdontologos = document.createElement("ul");

      odontologo.forEach((odontologo) => {
        const elementoOdontologo = document.createElement("li")
        elementoOdontologo.classList.add('skeleton-container')
        elementoOdontologo.innerHTML = `
            <ul>
              <li>ID: ${odontologo.id}</li>
              <li>Nombre: ${odontologo.nombre}</li>
              <li>Apellido: ${odontologo.apellido}</li>
              <li>Número de Matrícula: ${odontologo.matricula}</li>
            </ul>
            <button
              type="button"
              id="eliminarOdontologo"
            >
              Eliminar
            </button>
        `;
        const botonEliminar = elementoOdontologo.querySelector("#eliminarOdontologo");
        botonEliminar.addEventListener("click", function() {
        eliminarOdontologo(odontologo.id);
});
        listaOdontologos.appendChild(elementoOdontologo);
      });

      odontologoCreado.appendChild(listaOdontologos);
    }



  })
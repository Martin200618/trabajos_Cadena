// Asegúrate de tener la URL base de la API correcta
const API_URL = "http://localhost:8085/api/v1/artistas/";

// Función para registrar un artista
async function registerArtista(nombre, pais) {
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ nombre: nombre, pais: pais }) // Datos del artista en formato JSON
    });

    let data;
    try {
      data = await response.json();
    } catch (e) {
      data = await response.text(); // Manejar texto como respuesta
    }

    if (response.ok) {
      alert("Artista registrado exitosamente.");
      getAllArtistas(); // Actualizar la tabla después de registrar
    } else {
      console.error("Error en el registro:", data);
      alert("Error al registrar el artista: " + data);
    }
  } catch (error) {
    console.error("Error en la solicitud de registro:", error);
    alert("Error en la solicitud.");
  }
}

// Función para obtener todos los artistas y mostrarlos en la tabla
async function getAllArtistas() {
  try {
    const response = await fetch(API_URL);
    const data = await response.json();

    if (response.ok) {
      const artistasTableBody = document.querySelector("#artistasTable tbody");
      artistasTableBody.innerHTML = ""; // Limpiar tabla antes de agregar nuevos datos

      data.forEach((artista, index) => {
        console.log("Artista recibido:", artista); // Depuración
        const row = document.createElement("tr");

        // Columna de índice
        const indexCell = document.createElement("td");
        indexCell.textContent = index + 1;
        row.appendChild(indexCell);

        // Columna de nombre
        const nameCell = document.createElement("td");
        nameCell.textContent = artista.nombre;
        row.appendChild(nameCell);

        // Columna de país
        const countryCell = document.createElement("td");
        countryCell.textContent = artista.pais;
        row.appendChild(countryCell);

        // Columna de acciones
        const actionsCell = document.createElement("td");

        // Botón de editar
        const editButton = document.createElement("button");
        editButton.className = "btn btn-warning btn-sm me-2";
        editButton.textContent = "Editar";
        editButton.onclick = () => fillFormForEdit(artista);

        // Botón de eliminar
        const deleteButton = document.createElement("button");
        deleteButton.className = "btn btn-danger btn-sm";
        deleteButton.textContent = "Eliminar";
        deleteButton.onclick = () => deleteArtista(artista.Artistas_id); // Garantizamos que usamos 'Artistas_id'

        actionsCell.appendChild(editButton);
        actionsCell.appendChild(deleteButton);
        row.appendChild(actionsCell);

        // Agregar fila a la tabla
        artistasTableBody.appendChild(row);
      });
    } else {
      console.error("Error al obtener los artistas:", data);
      alert("Error al obtener los artistas.");
    }
  } catch (error) {
    console.error("Error en la solicitud de obtener artistas:", error);
    alert("Error en la solicitud.");
  }
}

// Función para llenar el formulario con los datos del artista seleccionado
function fillFormForEdit(artista) {
  if (!artista || !artista.Artistas_id) {
    alert("El artista seleccionado no tiene un ID válido.");
    return;
  }
  document.getElementById("artistaId").value = artista.Artistas_id; // Usar 'Artistas_id'
  document.getElementById("nombreArtista").value = artista.nombre;
  document.getElementById("paisArtista").value = artista.pais;
}

// Función para actualizar un artista
async function updateArtista(artistaId, nombre, pais) {
  if (!artistaId || isNaN(artistaId)) {
    alert("ID del artista inválido.");
    return;
  }
  try {
    const response = await fetch(`${API_URL}update/${artistaId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ nombre: nombre, pais: pais }) // Enviar datos como JSON
    });

    let data;
    try {
      data = await response.json();
    } catch (e) {
      data = await response.text(); // Manejar texto como respuesta
    }

    if (response.ok) {
      alert("Artista actualizado exitosamente.");
      getAllArtistas(); // Actualizar la tabla después de la edición
    } else {
      console.error("Error al actualizar el artista:", data);
      alert("Error al actualizar el artista: " + data);
    }
  } catch (error) {
    console.error("Error en la solicitud de actualización:", error);
    alert("Error en la solicitud de actualización.");
  }
}

// Función para eliminar un artista
async function deleteArtista(artistaId) {
  if (!artistaId || isNaN(artistaId)) {
    alert("ID del artista inválido.");
    return;
  }
  try {
    const response = await fetch(`${API_URL}delete/${artistaId}`, {
      method: "DELETE"
    });

    let data;
    try {
      data = await response.json();
    } catch (e) {
      data = await response.text(); // Manejar texto como respuesta
    }

    if (response.ok) {
      alert("Artista eliminado exitosamente.");
      getAllArtistas(); // Actualizar la tabla después de eliminar
    } else {
      console.error("Error al eliminar el artista:", data);
      alert("Error al eliminar el artista: " + data);
    }
  } catch (error) {
    console.error("Error en la solicitud de eliminación:", error);
    alert("Error en la solicitud de eliminación.");
  }
}

// Lógica para manejar el formulario de registro/actualización
document.getElementById("artistaForm").addEventListener("submit", async (event) => {
  event.preventDefault();
  const artistaId = document.getElementById("artistaId").value;
  const nombre = document.getElementById("nombreArtista").value;
  const pais = document.getElementById("paisArtista").value;

  if (artistaId) {
    await updateArtista(artistaId, nombre, pais); // Actualiza el artista
  } else {
    await registerArtista(nombre, pais); // Registra un nuevo artista
  }

  document.getElementById("artistaForm").reset(); // Limpiar formulario
});

// Cargar artistas al cargar la página
window.onload = getAllArtistas;
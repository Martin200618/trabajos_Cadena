const API_URL = "http://localhost:8085/api/v1/proyects";
const projectForm = document.getElementById("projectForm");
const projectTable = document.getElementById("projectTable");
const submitButton = document.querySelector("#projectForm button[type='submit']");
let requestCount = 0;
const MAX_REQUESTS = 10; // Límite de solicitudes por minuto

async function fetchProjects() {
  try {
    const response = await fetch(`${API_URL}/`);
    if (response.ok) {
      const projects = await response.json();
      renderProjects(projects);
    } else {
      alert("Error al obtener proyectos");
    }
  } catch (error) {
    console.error("Error al obtener proyectos:", error);
  }
}

// **✅ Validaciones y limitaciones en el registro**
projectForm.addEventListener("submit", async (e) => {
  e.preventDefault();

  // Bloquear múltiples clics en el botón
  submitButton.disabled = true;
  setTimeout(() => {
    submitButton.disabled = false;
  }, 5000);

  // Rate limiting básico en el frontend
  requestCount++;
  if (requestCount > MAX_REQUESTS) {
    alert("Demasiadas solicitudes, por favor espera.");
    return;
  }

  const projectId = document.getElementById("projectId").value.trim();
  const name = document.getElementById("projectName").value.trim();
  const imageUrl = document.getElementById("projectImage").value.trim();
  const description = document.getElementById("projectDescription").value.trim();

  if (!name || name.length > 100 || !imageUrl || !description) {
    alert("Datos inválidos. Asegúrate de que el nombre tenga menos de 100 caracteres y los demás campos sean correctos.");
    return;
  }

  const project = { name: name, imagenBase64: imageUrl, descripcion: description };
  const method = projectId ? "PUT" : "POST";
  const url = projectId ? `${API_URL}/update/${projectId}` : `${API_URL}`;

  try {
    const response = await fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(project),
    });

    if (response.status === 403 || response.status === 429) {
      alert("Límite de proyectos alcanzado o demasiadas solicitudes. Intenta más tarde.");
    } else if (response.ok) {
      alert(projectId ? "Proyecto actualizado con éxito" : "Proyecto registrado con éxito");
      fetchProjects();
      projectForm.reset();
      document.getElementById("projectId").value = "";
    }
  } catch (error) {
    console.error("Error al guardar el proyecto:", error);
  }
});

function renderProjects(projects) {
  projectTable.innerHTML = "";
  projects.forEach((project) => {
    const row = `
      <tr>
        <td>${project.nombre}</td>
        <td><img src="${project.imagenBase64}" alt="${project.nombre}" class="preview-img"></td>
        <td>${project.descripcion}</td>
        <td>
          <button class="btn btn-warning btn-sm" onclick="editProject(${project.proyectId})">Editar</button>
          <button class="btn btn-danger btn-sm" onclick="deleteProject(${project.proyectId})">Eliminar</button>
        </td>
      </tr>
    `;
    projectTable.innerHTML += row;
  });
}

async function editProject(proyectId) {
  try {
    const response = await fetch(`${API_URL}/get/${proyectId}`);
    if (response.ok) {
      const project = await response.json();
      document.getElementById("projectId").value = project.proyectId;
      document.getElementById("projectName").value = project.nombre;
      document.getElementById("projectImage").value = project.imagenBase64;
      document.getElementById("projectDescription").value = project.descripcion;
    } else {
      alert("Proyecto no encontrado");
    }
  } catch (error) {
    console.error("Error al obtener el proyecto:", error);
  }
}

async function deleteProject(proyectId) {
  await fetch(`${API_URL}/delete/${proyectId}`, { method: "DELETE" });
  fetchProjects();
}

//inicializar el formulario y la tabla
fetchProjects();

// limpiar el formulario al cargar la página
function limpiarFormulario() {
  document.getElementById("projectId").value = "";
  document.getElementById("projectName").value = "";
  document.getElementById("projectImage").value = "";
  document.getElementById("projectDescription").value = "";
  submitButton.disabled = false; // Habilitar el botón de envío
}
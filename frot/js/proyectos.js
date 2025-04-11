const API_URL = "http://localhost:8085/api/v1/proyects";
const projectForm = document.getElementById("projectForm");
const projectTable = document.getElementById("projectTable");

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

// Agregar o actualizar un proyecto
projectForm.addEventListener("submit", async (e) => {
  e.preventDefault();

  const proyectId = document.getElementById("projectId").value;
  const name = document.getElementById("projectName").value.trim();
  const imageUrl = document.getElementById("projectImage").value.trim();
  const description = document.getElementById("projectDescription").value.trim();

  if (!name || !imageUrl || !description) {
    alert("Todos los campos son obligatorios");
    return;
  }

  const project = { name, imagenBase64: imageUrl, descripcion: description };

  const method = proyectId ? "POST" : "POST";
  const url = proyectId ? `${API_URL}/update/${proyectId}` : API_URL;

  try {
    const response = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(project),
    });

    if (response.ok) {
      alert(proyectId ? "Proyecto actualizado con éxito" : "Proyecto registrado con éxito");
      fetchProjects();
      projectForm.reset();
      document.getElementById("projectId").value = ""; // Limpiar campo oculto
    } else {
      const error = await response.text();
      alert("Error: " + error);
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

fetchProjects();
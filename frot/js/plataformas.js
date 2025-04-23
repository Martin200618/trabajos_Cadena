const API_URL = 'http://localhost:8080/api/v1/plataforms';
const platformForm = document.getElementById('plataformaForm');
const platformTable = document.getElementById('plataformatable');
const submitButton = document.querySelector("#plataformaForm button[type='submit']");
let requestCount = 0;
const MAX_REQUESTS = 10; // Límite de solicitudes por minuto

// Simulación de datos de reseñas
async function fetchPlatforms() {
    try {
        const response = await fetch(`${API_URL}/`);
        if (response.ok) {
            const platforms = await response.json();
            renderPlatforms(platforms);
        } else {
            alert("Error al obtener plataformas");
        }
    } catch (error) {
        console.error("Error al obtener plataformas:", error);
    }
}

// **✅ Validaciones y limitaciones en el registro**
platformForm.addEventListener("submit", async (e) => {
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

    const platformId = document.getElementById("plataformaId").value.trim();
    const name = document.getElementById("plataformaNombre").value.trim();
    const imageUrl = document.getElementById("plataformaImagen").value.trim();
    const description = document.getElementById("plataformaDescripcion").value.trim();

    if (
        !name || 
        name.length > 100 || 
        !imageUrl || 
        !description
    ) {
        alert("Datos inválidos. Asegúrate de que el nombre tenga menos de 100 caracteres y los demás campos sean correctos.");
        return;
    }

    const platform = {
        name: name,
        imagenBase64: imageUrl,
        descripcion: description
    };

    try {
        let response;
        if (platformId) {
            // Actualizar plataforma existente
            response = await fetch(`${API_URL}/${platformId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(platform)
            });
        } else {
            // Crear nueva plataforma
            response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(platform)
            });
        }

        if (response.ok) {
            alert("Plataforma guardada exitosamente");
            platformForm.reset();
            fetchPlatforms();
        } else {
            alert("Error al guardar la plataforma");
        }
    }
    catch (error) {
        console.error("Error al guardar la plataforma:", error);
    }
});

// **✅ Renderizar plataformas en la tabla**
function renderPlatforms(platforms) {
    platformTable.innerHTML = ""; // Limpiar la tabla antes de renderizar

    platforms.forEach((platform) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${platform.id}</td>
            <td>${platform.name}</td>
            <td><img src="${platform.imagenBase64}" alt="${platform.name}" width="50" height="50"></td>
            <td>${platform.descripcion}</td>
            <td>
                <button class="edit-button" data-id="${platform.id}">Editar</button>
                <button class="delete-button" data-id="${platform.id}">Eliminar</button>
            </td>
        `;
        platformTable.appendChild(row);
    });

    // Agregar eventos a los botones de editar y eliminar
    const editButtons = document.querySelectorAll(".edit-button");
    editButtons.forEach((button) => {
        button.addEventListener("click", (e) => {
            const platformId = e.target.getAttribute("data-id");
            editPlatform(platformId);
        });
    });

    const deleteButtons = document.querySelectorAll(".delete-button");
    deleteButtons.forEach((button) => {
        button.addEventListener("click", (e) => {
            const platformId = e.target.getAttribute("data-id");
            deletePlatform(platformId);
        });
    });
}

// **✅ Editar plataforma**
async function editPlatform(platformId) {
    try {
        const response = await fetch(`${API_URL}/${platformId}`);
        if (response.ok) {
            const platform = await response.json();
            document.getElementById("plataformaId").value = platform.id;
            document.getElementById("plataformaNombre").value = platform.name;
            document.getElementById("plataformaImagen").value = platform.imagenBase64;
            document.getElementById("plataformaDescripcion").value = platform.descripcion;
        } else {
            alert("Error al obtener la plataforma");
        }
    } catch (error) {
        console.error("Error al obtener la plataforma:", error);
    }
}

// **✅ Eliminar plataforma**
async function deletePlatform(platformId) {
    if (confirm("¿Estás seguro de que deseas eliminar esta plataforma?")) {
        try {
            const response = await fetch(`${API_URL}/${platformId}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert("Plataforma eliminada exitosamente");
                fetchPlatforms();
            } else {
                alert("Error al eliminar la plataforma");
            }
        } catch (error) {
            console.error("Error al eliminar la plataforma:", error);
        }
    }
}

// Inicializar la tabla de plataformas al cargar la página
fetchPlatforms();

// **✅ Limpiar el formulario al cargar la página**
function limpiarFormulario() {
    document.getElementById("plataformaId").value = "";
    document.getElementById("plataformaNombre").value = "";
    document.getElementById("plataformaImagen").value = "";
    document.getElementById("plataformaDescripcion").value = "";
    submitButton.disabled = false; // Habilitar el botón de envío
}
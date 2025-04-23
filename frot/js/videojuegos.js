const apiUrl = "https://localhost:8080/api/v1/videogames"; // URL de la API
const videojuegosForm = document.getElementById("videojuegoForm");
const videojuegosTable = document.getElementById("videojuegosTable");
const submitButton = document.querySelector("#videojuegoForm button[type='submit']");
const MAX_REQUESTS = 5; // Máximo de solicitudes permitidas
let requestCount = 0; // Contador de solicitudes


// Simulamos los datos de los videojuegos (esto se podría reemplazar con datos reales de la base de datos)
/*const videojuegos = [
{id: 1, titulo: "The Witcher 3", categoria: "RPG", precio: 49.99},
{id: 2, titulo: "FIFA 21", categoria: "Deportes", precio: 59.99},
{id: 3, titulo: "Minecraft", categoria: "Aventura", precio: 29.99},
{id: 4, titulo: "Call of Duty", categoria: "Shooter", precio: 59.99},
{id: 5, titulo: "Among Us", categoria: "Multijugador", precio: 19.99},
{id: 6, titulo: "Final Fantasy VII", categoria: "RPG", precio: 39.99}
];*/

// Función para renderizar los videojuegos en la tabla|
async function fetchVideojuegos() {
    try {
        const response = await fetch(apiUrl);
        if (response.ok) {
            const videojuegos = await response.json();
            renderVideojuegos(videojuegos);
        } else {
            alert("Error al obtener los videojuegos");
        }
    } catch (error) {
        console.error("Error al obtener los videojuegos:", error);
    }
}

//validaciones y limitaciones en el registro
vadeojuegosForm.addEventListener("submit", async (e) => {
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

    const videojuegoId = document.getElementById("videojuegoId").value.trim();
    const titulo = document.getElementById("titulo").value.trim();
    const categoria = document.getElementById("categoria").value.trim();
    const precio = parseFloat(document.getElementById("precio").value.trim());

    if (!titulo || titulo.length > 100 || !categoria || !precio) {
        alert("Datos inválidos. Asegúrate de que el título tenga menos de 100 caracteres y los demás campos sean correctos.");
        return;
    }

    const videojuego = { titulo: titulo, categoria: categoria, precio: precio };
    const method = videojuegoId ? "PUT" : "POST";
    const url = videojuegoId ? `${apiUrl}/update/${videojuegoId}` : apiUrl;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(videojuego),
        });

        if (response.status === 403 || response.status === 429) {
            alert("Error al procesar la solicitud");
            return;
        }

        if (response.ok) {
            alert("Videojuego guardado correctamente");
            fetchVideojuegos(); // Recargar la tabla con los datos actualizados
        } else {
            alert("Error al guardar el videojuego");
        }
    } catch (error) {
        console.error("Error al guardar el videojuego:", error);
    }
});

// Función para renderizar los videojuegos en la tabla
function renderVideojuegos(videojuegos) {
    videojuegosTable.innerHTML = ""; // Limpiar contenido previo

    videojuegos.forEach(videojuego => {
        videojuegosTable.innerHTML += 
        `
            <tr>
                <td>${videojuego.videojuegoId}</td>
                <td>${videojuego.titulo}</td>
                <td>${videojuego.categoria}</td>
                <td>$${videojuego.precio}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editar(${videojuego.videojuegoId})">Editar</button>
                    <button class="btn btn-danger btn-sm" onclick="eliminar(${videojuego.videojuegoId})">Eliminar</button>
                </td>
            </tr>
        `;
    });
}

// Función para editar un videojuego
async function editVideopjuego(videojuegoId) {
    try {
        const response = await fetch(`${apiUrl}/get/${videojuegoId}`);
        if (response.ok) {
            const videojuego = await response.json();
            document.getElementById("videojuegoId").value = videojuego.videojuegoId;
            document.getElementById("titulo").value = videojuego.titulo;
            document.getElementById("categoria").value = videojuego.categoria;
            document.getElementById("precio").value = videojuego.precio;
        } else {
            alert("Error al obtener el videojuego");
        }
    } catch (error) {
        console.error("Error al obtener el videojuego:", error);
    }
}

// Función para eliminar un videojuego
async function deleteVideojuego(videojuegoId) {
    try {
        const response = await fetch(`${apiUrl}/delete/${videojuegoId}`, { method: "DELETE" });
        if (response.ok) {
            alert("Videojuego eliminado correctamente");
            fetchVideojuegos(); // Recargar la tabla con los datos actualizados
        } else {
            alert("Error al eliminar el videojuego");
        }
    } catch (error) {
        console.error("Error al eliminar el videojuego:", error);
    }
}

// Inicializar la tabla al cargar la página
fetchVideojuegos();

// Función para limpiar el formulario
function limpiarFormulario() {
    document.getElementById("videojuegoId").value = "";
    document.getElementById("titulo").value = "";
    document.getElementById("categoria").value = "";
    document.getElementById("precio").value = "";
    submitButton.disabled = false; // Habilitar el botón de envío
}
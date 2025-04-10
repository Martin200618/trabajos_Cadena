function cargarProyectos() {
    fetch("http://localhost:8080/api/proyectos")
        .then(response => response.json())
        .then(data => mostrarProyectos(data))
        .catch(error => {
            console.error("Error al obtener los proyectos:", error);
            document.getElementById("listaProyectos").innerHTML = "<p>Error al cargar los proyectos.</p>";
        });
}

function mostrarProyectos(proyectos) {
    const contenedor = document.getElementById("listaProyectos");
    contenedor.innerHTML = "<h2>Proyectos Registrados:</h2>";

    if (proyectos.length === 0) {
        contenedor.innerHTML += "<p>No hay proyectos registrados.</p>";
        return;
    }

    const lista = document.createElement("ul");

    proyectos.forEach(proyecto => {
        const item = document.createElement("li");
        item.innerHTML = `
            <strong>${proyecto.nombre}</strong><br>
            ${proyecto.descripcion}<br>
            Inicio: ${proyecto.fechaInicio} | Fin: ${proyecto.fechaFin}<br>
            Usuario ID: ${proyecto.usuarioId}
        `;
        lista.appendChild(item);
    });

    contenedor.appendChild(lista);
}

// Manejar envío del formulario
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("nuevoProyectoForm");
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        guardarProyecto();
    });
});

function guardarProyecto() {
    const proyecto = {
        nombre: document.getElementById("nombreProyecto").value,
        descripcion: document.getElementById("descripcionProyecto").value,
        fecha_inicio: document.getElementById("fechaInicio").value,
        fecha_fin: document.getElementById("fechaFin").value,
        usuario_id: parseInt(document.getElementById("usuarioId").value)
    };

    fetch("http://localhost:8080/api/proyectos", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(proyecto)
    })
    .then(response => {
        if (response.ok) {
            alert("Proyecto guardado exitosamente");
            cargarProyectos();
            document.getElementById("nuevoProyectoForm").reset();
        } else {
            alert("Error al guardar proyecto");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Error al conectar con el servidor");
    });
}
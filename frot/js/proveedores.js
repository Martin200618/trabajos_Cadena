const API_URL = 'http://localhost:8080/api/v1/suppliers';
const proveedoresTable = document.getElementById('proveedoresTable');
const proveedorForm = document.getElementById('proveedorForm');
const submitButton = document.querySelector("#proveedorForm button[type='submit']");
let requestCount = 0;
const MAX_REQUESTS = 10; // Límite de solicitudes por minuto

// Simulación de datos de proveedores
async function fetchProveedores() {
    try {
        const response = await fetch(`${API_URL}/`);
        if (response.ok) {
            const proveedores = await response.json();
            renderProveedores(proveedores);
        } else {
            alert("Error al obtener proveedores");
        }
    } catch (error) {
        console.error("Error al obtener proveedores:", error);
    }
}

// **✅ Validaciones y limitaciones en el registro**
proveedorForm.addEventListener("submit", async (e) => {
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

    const proveedorId = document.getElementById("proveedorId").value.trim();
    const nombre = document.getElementById("proveedorNombre").value.trim();
    const imagenUrl = document.getElementById("proveedorImagen").value.trim();
    const descripcion = document.getElementById("proveedorDescripcion").value.trim();

    if (
        !nombre || 
        nombre.length > 100 || 
        !imagenUrl || 
        !descripcion
    ) {
        alert("Datos inválidos. Asegúrate de que el nombre tenga menos de 100 caracteres y los demás campos sean correctos.");
        return;
    }
    const proveedor = {
        nombre: nombre,
        imagenBase64: imagenUrl,
        descripcion: descripcion
    };
    try {
        let response;
        if (proveedorId) {
            // Actualizar proveedor existente
            response = await fetch(`${API_URL}/${proveedorId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(proveedor)
            });
        } else {
            // Crear nuevo proveedor
            response = await fetch(`${API_URL}/`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(proveedor)
            });
        }

        if (response.ok) {
            alert("Proveedor guardado exitosamente");
            fetchProveedores();
        } else {
            alert("Error al guardar el proveedor");
        }
    } catch (error) {
        console.error("Error al guardar el proveedor:", error);
    }
});

// **✅ Renderizar proveedores en la tabla**
function renderProveedores(proveedores) {
    proveedoresTable.innerHTML = ''; // Limpiar la tabla antes de renderizar
    proveedores.forEach(proveedor => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${proveedor.id}</td>
            <td>${proveedor.nombre}</td>
            <td><img src="${proveedor.imagenBase64}" alt="${proveedor.nombre}" width="50"></td>
            <td>${proveedor.descripcion}</td>
            <td>
                <button class="edit-button" data-id="${proveedor.id}">Editar</button>
                <button class="delete-button" data-id="${proveedor.id}">Eliminar</button>
            </td>
        `;
        proveedoresTable.appendChild(row);
    });
}

// **✅ Editar proveedor**
async function editProveedor(e) {
    const proveedorId = e.target.dataset.id;
    const response = await fetch(`${API_URL}/${proveedorId}`);
    if (response.ok) {
        const proveedor = await response.json();
        document.getElementById("proveedorId").value = proveedor.id;
        document.getElementById("proveedorNombre").value = proveedor.nombre;
        document.getElementById("proveedorImagen").value = proveedor.imagenBase64;
        document.getElementById("proveedorDescripcion").value = proveedor.descripcion;
    } else {
        alert("Error al obtener el proveedor");
    }
}

// **✅ Eliminar proveedor**
async function deleteProveedor(e) {
    const proveedorId = e.target.dataset.id;
    if (confirm("¿Estás seguro de que deseas eliminar este proveedor?")) {
        const response = await fetch(`${API_URL}/${proveedorId}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            alert("Proveedor eliminado exitosamente");
            fetchProveedores();
        } else {
            alert("Error al eliminar el proveedor");
        }
    }
}

// Inicializar la tabla de proveedores
fetchProveedores();

//  limpiar formulario
function limpiarFormulario(){
    document.getElementById("proveedorId").value = "";
    document.getElementById("proveedorNombre").value = "";
    document.getElementById("proveedorImagen").value = "";
    document.getElementById("proveedorDescripcion").value = "";
    submitButton.disabled = false; // Habilitar el botón después de limpiar
}
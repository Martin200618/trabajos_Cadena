const ApiUrl = "https://localhost:8080/api/v1/sales"; // URL de la API
const ventasForm = document.getElementById("ventaForm");
const ventasTable = document.getElementById("ventaTable");
const submitButton = document.querySelector("#ventaForm button[type='submit']");
const MAX_REQUESTS = 5; // Máximo de solicitudes permitidas
let requestCount = 0; // Contador de solicitudes

//funcion para renderizar las ventas en la tabla
async function fetchVentas() {
    try {
        const response = await fetch(ApiUrl);
        if (response.ok) {
            const ventas = await response.json();
            renderVentas(ventas);
        } else {
            alert("Error al obtener las ventas");
        }
    } catch (error) {
        console.error("Error al obtener las ventas:", error);
    }
}

//validaciones y limitaciones en el registro
ventasForm.addEventListener("submit", async (e) => {
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
    const ventaId = document.getElementById("ventaId").value.trim();
    const productoId = document.getElementById("producto").value.trim();
    const cantidad = parseInt(document.getElementById("cantidadVenta").value.trim());
    const precioTotal = parseFloat(document.getElementById("precioTotal").value.trim());
    const fecha = document.getElementById("fechaVenta").value.trim();
    if (!productoId || !cantidad || !precioTotal || !fecha) {
        alert("Datos inválidos. Asegúrate de que todos los campos sean correctos.");
        return;
    }
    const venta = {
        ventaId: ventaId,
        productoId: productoId,
        cantidad: cantidad,
        precioTotal: precioTotal,
        fecha: fecha
    };
    const method = ventaId ? "PUT" : "POST";
    const url = ventaId ? `${ApiUrl}/update/${ventaId}` : ApiUrl;
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(venta)
        });
        if (response.status === 403 || response.status === 429) {
            alert("Error al procesar la solicitud");
            return;
        }
        if (response.ok) {
            const data = await response.json();
            if (ventaId) {
                alert("Venta actualizada con éxito");
            } else {
                alert("Venta creada con éxito");
            }
        }
    } catch (error) {
        console.error("Error al procesar la venta:", error);
        alert("Error al procesar la venta");
    } finally {
        // Limpiar el formulario
        ventasForm.reset();
        submitButton.disabled = false;
        requestCount = 0; // Reiniciar el contador de solicitudes
        fetchVentas(); // Recargar la tabla con los datos actualizados
    }
});

// Función para renderizar las ventas en la tabla
function renderVentas(ventas) {
    ventasTable.innerHTML = ""; // Limpiar contenido previo
    ventas.forEach(venta => {
        const producto = productos.find(p => p.ventaId === venta.productoId);
        ventasTable.innerHTML += 
        `
            <tr>
                <td>${venta.ventaId}</td>
                <td>${producto.nombre}</td>
                <td>${venta.cantidad}</td>
                <td>$${venta.precioTotal}</td>
                <td>${venta.fecha}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editarVenta(${venta.ventaId})">Editar</button>
                    <button class="btn btn-danger btn-sm" onclick="eliminarVenta(${venta.ventaId})">Eliminar</button>
                </td>
            </tr>
        `;
    });
}

// Función para editar una venta
async function editarVenta(ventaId) {
    try {
        const response = await fetch(`${ApiUrl}/get/${ventaId}`);
        if (response.ok) {
            const venta = await response.json();
            document.getElementById("ventaId").value = venta.ventaId;
            document.getElementById("producto").value = venta.productoId;
            document.getElementById("cantidadVenta").value = venta.cantidad;
            document.getElementById("precioTotal").value = venta.precioTotal;
            document.getElementById("fechaVenta").value = venta.fecha;
        } else {
            alert("Error al obtener la venta");
        }
    } catch (error) {
        console.error("Error al obtener la venta:", error);
    }
}

// Función para eliminar una venta
async function eliminarVenta(ventaId) {
    try {
        const response = await fetch(`${ApiUrl}/delete/${ventaId}`, { method: "DELETE" });
        if (response.ok) {
            alert("Venta eliminada correctamente");
            fetchVentas(); // Recargar la tabla con los datos actualizados
        } else {
            alert("Error al eliminar la venta");
        }
    } catch (error) {
        console.error("Error al eliminar la venta:", error);
    }
}

// Inicializar la tabla de ventas al cargar la página|
fetchVentas();

//funcion para limpiar el formulario
function limpiarFormulario() {
    document.getElementById("ventaId").value = "";
    document.getElementById("producto").value = "";
    document.getElementById("cantidadVenta").value = "";
    document.getElementById("precioTotal").value = "";
    document.getElementById("fechaVenta").value = "";
    submitButton.disabled = false;
}
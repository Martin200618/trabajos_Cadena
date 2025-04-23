const API_URL = 'http://localhost:8080/api/v1/detailSale';
const ventasForm = document.getElementById('ventaForm');
const ventasTable = document.getElementById('ventaTable');
const submitButton = document.querySelector("#ventaForm button[type='submit']");
const MAX_REQUESTS = 5; // Máximo de solicitudes permitidas
let requestCount = 0; // Contador de solicitudes

// Función para renderizar las ventas en la tabla
async function fetchVentas() {
    try {
        const response = await fetch(API_URL);
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
    const detalleVentaId = document.getElementById("detalleVentaId").value.trim();
    const ventaId = document.getElementById("ventaId").value.trim();
    const productoId = document.getElementById("producto").value.trim();
    const cantidad = parseInt(document.getElementById("cantidadVenta").value.trim());
    const precioTotal = parseFloat(document.getElementById("precioTotal").value.trim());
    const fecha = document.getElementById("fechaVenta").value.trim();
    if(!dentalleVentaId || !ventaId || !productoId || !cantidad || !precioTotal || !fecha) {
        alert("Datos inválidos. Asegúrate de que todos los campos sean correctos.");
        return;
    }
    const detalleVenta = {
        detalleVentaId: detalleVentaId,
        ventaId: ventaId,
        productoId: productoId,
        cantidad: cantidad,
        precioTotal: precioTotal,
        fecha: fecha
    };
    const method = detalleVentaId ? "PUT" : "POST";
    const url = detalleVentaId ? `${API_URL}/update/${detalleVentaId}` : API_URL;
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(detalleVenta)
        });
        if (response.ok) {
            alert("Detalle de venta guardado correctamente");
            fetchVentas();
        } else {
            alert("Error al guardar el detalle de venta");
        }
    } catch (error) {
        console.error("Error al guardar el detalle de venta:", error);
    }
});

// Función para renderizar las ventas en la tabla
function renderVentas(ventas) {
    ventasTable.innerHTML = '';
    ventas.forEach(venta => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${venta.detalleVentaId}</td>
            <td>${venta.ventaId}</td>
            <td>${venta.productoId}</td>
            <td>${venta.cantidad}</td>
            <td>${venta.precioTotal}</td>
            <td>${venta.fecha}</td>
        `;
        ventasTable.appendChild(row);
    });
}

// Función para editar una venta
async function editVenta(detalleVentaId) {
    try {
        const response = await fetch(`${API_URL}/${detalleVentaId}`);
        if (response.ok) {
            const venta = await response.json();
            document.getElementById("detalleVentaId").value = venta.detalleVentaId;
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
async function deleteVenta(detalleVentaId) {
    try {
        const response = await fetch(`${API_URL}/delete/${detalleVentaId}`, { method: "DELETE" });
        if (response.ok) {
            alert("Detalle de venta eliminado correctamente");
            fetchVentas();
        } else {
            alert("Error al eliminar el detalle de venta");
        }
    } catch (error) {
        console.error("Error al eliminar el detalle de venta:", error);
    }
}

// Inicializar la tabla al cargar la página
fetchVentas();

//funciones para limpiar el formulario
function limpiarFormulario() {
    document.getElementById("detalleVentaId").value = '';
    document.getElementById("ventaId").value = '';
    document.getElementById("producto").value = '';
    document.getElementById("cantidadVenta").value = '';
    document.getElementById("precioTotal").value = '';
    document.getElementById("fechaVenta").value = '';
    submitButton.disabled = false; // Habilitar el botón de envío
}
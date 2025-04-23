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
    const ventaId = document.getElementById("ventaId").value.trim();
    const productoId = document.getElementById("producto").value.trim();
    const cantidad = parseInt(document.getElementById("cantidadVenta").value.trim());
    const precioTotal = parseFloat(document.getElementById("precioTotal").value.trim());
    const fecha = document.getElementById("fechaVenta").value.trim();
    if(!)
});
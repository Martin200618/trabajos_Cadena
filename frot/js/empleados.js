const API_URL = 'http://localhost:8080/api/v1/employees';
const empleadosForm = document.getElementById('empleadosForm');
const empleadosTable = document.getElementById('empleadosTable');
const submitButton = document.querySelector("#empleadosForm button[type='submit']");
const MAX_REQUESTS = 5; // Máximo de solicitudes permitidas
let requestCount = 0; // Contador de solicitudes

// Función para renderizar los empleados en la tabla
async function fetchEmpleados() {
    try {
        const response = await fetch(API_URL);
        if (response.ok) {
            const empleados = await response.json();
            renderEmpleados(empleados);
        } else {
            alert('Error al obtener los empleados');
        }
    } catch (error) {
        console.error('Error al obtener los empleados:', error);
    }
}

// validaciones y limitaciones en el registro
empleadosForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    // Bloquear múltiples clics en el botón
    submitButton.disabled = true;
    setTimeout(() => {
        submitButton.disabled = false;
    }, 5000);
    // Rate limiting básico en el frontend
    requestCount++;
    if (requestCount > MAX_REQUESTS) {
        alert('Demasiadas solicitudes, por favor espera.');
        return;
    }
    const empleadoId = document.getElementById('empleadoId').value.trim();
    const nombre = document.getElementById('nombre').value.trim();
    const apellido = document.getElementById('apellido').value.trim();
    const email = document.getElementById('email').value.trim();
    const telefono = document.getElementById('telefono').value.trim();
    const fechaContratacion = document.getElementById('fechaContratacion').value.trim();
    if (!nombre || !apellido || !email || !telefono || !fechaContratacion) {
        alert('Datos inválidos. Asegúrate de que todos los campos sean correctos.');
        return;
    }
    const empleado = {
        empleadoId: empleadoId,
        nombre: nombre,
        apellido: apellido,
        email: email,
        telefono: telefono,
        fechaContratacion: fechaContratacion
    };
    const method = empleadoId ? 'PUT' : 'POST';
    const url = empleadoId ? `${API_URL}/update/${empleadoId}` : API_URL;
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(empleado)
        });
        if (response.ok) {
            alert('Empleado guardado correctamente');
            empleadosForm.reset();
            fetchEmpleados();
        } else {
            alert('Error al guardar el empleado');
        }
    } catch (error) {
        console.error('Error al guardar el empleado:', error);
    }
});

// Función para renderizar los empleados en la tabla
function renderEmpleados(empleados) {
    empleadosTable.innerHTML = '';
    empleados.forEach((empleado) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${empleado.empleadoId}</td>
            <td>${empleado.nombre}</td>
            <td>${empleado.apellido}</td>
            <td>${empleado.email}</td>
            <td>${empleado.telefono}</td>
            <td>${empleado.fechaContratacion}</td>
            <td>
                <button class="btn btn-primary" onclick="editEmpleado('${empleado.empleadoId}')">Editar</button>
                <button class="btn btn-danger" onclick="deleteEmpleado('${empleado.empleadoId}')">Eliminar</button>
            </td>
        `;
        empleadosTable.appendChild(row);
    });
}

// Función para editar un empleado
async function editEmpleado(empleadoId) {
    try {
        const response = await fetch(`${API_URL}/${empleadoId}`);
        if (response.ok) {
            const empleado = await response.json();
            document.getElementById('empleadoId').value = empleado.empleadoId;
            document.getElementById('nombre').value = empleado.nombre;
            document.getElementById('apellido').value = empleado.apellido;
            document.getElementById('email').value = empleado.email;
            document.getElementById('telefono').value = empleado.telefono;
            document.getElementById('fechaContratacion').value = empleado.fechaContratacion;
        } else {
            alert('Error al obtener el empleado');
        }
    } catch (error) {
        console.error('Error al obtener el empleado:', error);
    }
}

// Función para eliminar un empleado
async function deleteEmpleado(empleadoId) {
    if (confirm('¿Estás seguro de que deseas eliminar este empleado?')) {
        try {
            const response = await fetch(`${API_URL}/delete/${empleadoId}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert('Empleado eliminado correctamente');
                fetchEmpleados();
            } else {
                alert('Error al eliminar el empleado');
            }
        } catch (error) {
            console.error('Error al eliminar el empleado:', error);
        }
    }
}

// Inicializar la tabla de empleados al cargar la página
fetchEmpleados();

// Función para limpiar el formulario
function limpiarFormulario() {
    document.getElementById('empleadoId').value = '';
    document.getElementById('nombre').value = '';
    document.getElementById('apellido').value = '';
    document.getElementById('email').value = '';
    document.getElementById('telefono').value = '';
    document.getElementById('fechaContratacion').value = '';
    submitButton.disabled = false;
}
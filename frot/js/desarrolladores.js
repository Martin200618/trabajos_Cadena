const API_URL = "https://localhost:8080/api/v1/developers"; // URL de la API
const developersForm = document.getElementById("developerForm");
const developersTable = document.getElementById("developerTable");
const submitButton = document.querySelector("#developerForm button[type='submit']");
const MAX_REQUESTS = 5; // Máximo de solicitudes permitidas
let requestCount = 0; // Contador de solicitudes

// Función para renderizar los desarrolladores en la tabla
async function fetchDevelopers() {
    try {
        const response = await fetch(API_URL);
        if (response.ok) {
            const developers = await response.json();
            renderDevelopers(developers);
        } else {
            alert("Error al obtener los desarrolladores");
        }
    } catch (error) {
        console.error("Error al obtener los desarrolladores:", error);
    }
}

// Función para renderizar los desarrolladores en la tabla
function renderDevelopers(developers) {
    developersTable.innerHTML = ""; // Limpiar la tabla antes de renderizar
    developers.forEach((developer) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${developer.developerId}</td>
            <td>${developer.name}</td>
            <td>${developer.email}</td>
            <td>${developer.phone}</td>
            <td>${developer.salary}</td>
            <td>${developer.hireDate}</td>
            <td><button class="edit-button" data-id="${developer.developerId}">Editar</button></td>
        `;
        developersTable.appendChild(row);
    });
}

// Función para manejar el evento de envío del formulario
developersForm.addEventListener("submit", async (e) => {
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
    const developerId = document.getElementById("developerId").value.trim();
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const salary = parseFloat(document.getElementById("salary").value.trim());
    const hireDate = document.getElementById("hireDate").value.trim();
    if (!name || !email || !phone || !salary || !hireDate) {
        alert("Datos inválidos. Asegúrate de que todos los campos sean correctos.");
        return;
    }
    const developer = {
        developerId: developerId,
        name: name,
        email: email,
        phone: phone,
        salary: salary,
        hireDate: hireDate
    };
    const method = developerId ? "PUT" : "POST";
    const url = developerId ? `${API_URL}/update/${developerId}` : API_URL;
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(developer)
        });
        if (response.status === 403 || response.status === 429) {
            alert("Error al procesar la solicitud");
            return;
        }
        if (response.ok) {
            alert("Desarrollador guardado correctamente");
            fetchDevelopers(); // Volver a cargar los desarrolladores
            developersForm.reset(); // Limpiar el formulario
        } else {
            alert("Error al guardar el desarrollador");
        }
    } catch (error) {
        console.error("Error al guardar el desarrollador:", error);
    }
});

// funcion para renderizar los desarrolladores en la tabla
async function fetchDevelopers() {
    try {
        const response = await fetch(API_URL);
        if (response.ok) {
            const developers = await response.json();
            renderDevelopers(developers);
        } else {
            alert("Error al obtener los desarrolladores");
        }
    } catch (error) {
        console.error("Error al obtener los desarrolladores:", error);
    }
}

// funcion para editar un desarrollador
developersTable.addEventListener("click", (e) => {
    if (e.target.classList.contains("edit-button")) {
        const developerId = e.target.dataset.id;
        fetch(`${API_URL}/${developerId}`)
            .then((response) => response.json())
            .then((developer) => {
                document.getElementById("developerId").value = developer.developerId;
                document.getElementById("name").value = developer.name;
                document.getElementById("email").value = developer.email;
                document.getElementById("phone").value = developer.phone;
                document.getElementById("salary").value = developer.salary;
                document.getElementById("hireDate").value = developer.hireDate;
            })
            .catch((error) => console.error("Error al obtener el desarrollador:", error));
    }
});

//funcion para eliminar un desarrollador
developersTable.addEventListener("click", (e) => {
    if (e.target.classList.contains("delete-button")) {
        const developerId = e.target.dataset.id;
        if (confirm("¿Estás seguro de que deseas eliminar este desarrollador?")) {
            fetch(`${API_URL}/delete/${developerId}`, {
                method: "DELETE"
            })
                .then((response) => {
                    if (response.ok) {
                        alert("Desarrollador eliminado correctamente");
                        fetchDevelopers(); // Volver a cargar los desarrolladores
                    } else {
                        alert("Error al eliminar el desarrollador");
                    }
                })
                .catch((error) => console.error("Error al eliminar el desarrollador:", error));
        }
    }
});

// Inicializar la tabla de desarrolladores al cargar la página
fetchDevelopers();

// Función para limpiar el formulario
function limpiarFormulario() {
    document.getElementById("developerId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("salary").value = "";
    document.getElementById("hireDate").value = "";
    submitButton.disabled = false; // Habilitar el botón de envío
}
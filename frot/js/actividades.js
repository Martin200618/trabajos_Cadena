const API_URL = "http://localhost:8080/api/v1/activities";

// Obtener todas las actividades
async function fetchActivities() {
    const response = await fetch(API_URL);
    const activities = await response.json();
    renderActivities(activities);
}

// Renderizar actividades en la tabla
function renderActivities(activities) {
    const tableBody = document.getElementById("activityTable");
    tableBody.innerHTML = "";
    activities.forEach(activity => {
        const row = `
            <tr>
                <td>${activity.userId}</td>
                <td>${activity.accion}</td>
                <td>${new Date(activity.fecha).toLocaleString()}</td>
                <td>
                    <button class="btn btn-warning" onclick="editActivity(${activity.activity_id})">Editar</button>
                    <button class="btn btn-danger" onclick="deleteActivity(${activity.activity_id})">Eliminar</button>
                </td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}

// Agregar nueva actividad
async function addActivity(event) {
    event.preventDefault();
    const userId = document.getElementById("userId").value;
    const accion = document.getElementById("accion").value;
    const fecha = document.getElementById("fecha").value;

    const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ userId, accion, fecha })
    });

    if (response.ok) {
        fetchActivities();
        document.getElementById("activityForm").reset();
    }
}

// Eliminar actividad
async function deleteActivity(activityId) {
    await fetch(`${API_URL}/${activityId}`, { method: "DELETE" });
    fetchActivities();
}

document.getElementById("activityForm").addEventListener("submit", addActivity);
fetchActivities();
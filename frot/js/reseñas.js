const API_URL = "https://localhost:8080/api/v1/reviews";
const reviewForm = document.getElementById("resenaForm");
const reviewTable = document.getElementById("resenatable");
const submitButton = document.querySelector("#resenaForm button[type='submit']");
let requestCount = 0;
const MAX_REQUESTS = 10; // Límite de solicitudes por minuto

// Simulación de datos de reseñas
async function fetchReviews() {
    try {
        const response = await fetch(`${API_URL}/`);
        if (response.ok) {
            const reviews = await response.json();
            renderReviews(reviews);
        } else {
            alert("Error al obtener reseñas");
        }
    }
    catch (error) {
        console.error("Error al obtener reseñas:", error);
    }
}

// **✅ Validaciones y limitaciones en el registro**
reviewForm.addEventListener("submit", async (e) => {
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

    const reviewId = document.getElementById("resenaId").value.trim();
    const name = document.getElementById("resenaNombre").value.trim();
    const imageUrl = document.getElementById("resenaImagen").value.trim();
    const description = document.getElementById("resenaDescripcion").value.trim();

    if (
        !name || 
        name.length > 100 || 
        !imageUrl || 
        !description
    ) {
        alert("Datos inválidos. Asegúrate de que el nombre tenga menos de 100 caracteres y los demás campos sean correctos.");
        return;
    }

    const review = {
        name: name,
        imagenBase64: imageUrl,
        descripcion: description
    };
    
    const method = reviewId ? "PUT" : "POST";
    const url = reviewId ? `${API_URL}/update/${reviewId}` : `${API_URL}`;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(review),
        });

        if (response.status === 403 || response.status === 429) {
            alert("Error de autorización o demasiadas solicitudes.");
            return;
        }

        if (response.ok) {
            alert("Reseña guardada correctamente");
            reviewForm.reset();
            fetchReviews();
        } else {
            alert("Error al guardar la reseña");
        }
    }
    catch (error) {
        console.error("Error al guardar la reseña:", error);
    }
});

// **✅ Renderizar reseñas en la tabla**
function renderReviews(reviews) {
    reviewTable.innerHTML = ""; // Limpiar la tabla antes de renderizar

    reviews.forEach((review) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${review.id}</td>
            <td>${review.name}</td>
            <td><img src="${review.imagenBase64}" alt="Imagen" width="50"></td>
            <td>${review.descripcion}</td>
            <td>
                <button class="edit-button" data-id="${review.id}">Editar</button>
                <button class="delete-button" data-id="${review.id}">Eliminar</button>
            </td>
        `;
        reviewTable.appendChild(row);
    });

    // Agregar eventos a los botones de editar y eliminar
    document.querySelectorAll(".edit-button").forEach((button) => {
        button.addEventListener("click", editReview);
    });

    document.querySelectorAll(".delete-button").forEach((button) => {
        button.addEventListener("click", deleteReview);
    });
}

// **✅ Editar reseña**
async function editReview(e) {
    const reviewId = e.target.dataset.id;
    try {
        const response = await fetch(`${API_URL}/get/${reviewId}`);
        if (response.ok) {
            const review = await response.json();
            document.getElementById("resenaId").value = review.id;
            document.getElementById("resenaNombre").value = review.name;
            document.getElementById("resenaImagen").value = review.imagenBase64;
            document.getElementById("resenaDescripcion").value = review.descripcion;
        } else {
            alert("Reseña no encontrada");
        }
    } catch (error) {
        console.error("Error al obtener la reseña:", error);
    }
}

// **✅ Eliminar reseña**
async function deleteReview(e) {
    const reviewId = e.target.dataset.id;
    if (confirm("¿Estás seguro de que deseas eliminar esta reseña?")) {
        try {
            const response = await fetch(`${API_URL}/delete/${reviewId}`, { method: "DELETE" });
            if (response.ok) {
                alert("Reseña eliminada correctamente");
                fetchReviews();
            } else {
                alert("Error al eliminar la reseña");
            }
        } catch (error) {
            console.error("Error al eliminar la reseña:", error);
        }
    }
}
// **✅ Inicializar la tabla de reseñas al cargar la página**
fetchReviews();

// **✅ Limpiar el formulario al cargar la página**
function limpiarFormulario() {
    document.getElementById("resenaId").value = "";
    document.getElementById("resenaNombre").value = "";
    document.getElementById("resenaImagen").value = "";
    document.getElementById("resenaDescripcion").value = "";
    submitButton.disabled = false; // Habilitar el botón de envío
}
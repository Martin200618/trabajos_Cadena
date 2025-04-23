const API_URL = "https://localhost:8080/api/v1/category"; // URL de la API
const categoriesForm = document.getElementById("categoryForm");
const categoriesTable = document.getElementById("categoryTable");
const submitButton = document.querySelector("#categoryForm button[type='submit']");
const MAX_REQUESTS = 5; // Máximo de solicitudes permitidas
let requestCount = 0; // Contador de solicitudes

// Función para renderizar las categorías en la tabla
async function fetchCategories() {
    try {
        const response = await fetch(API_URL);
        if (response.ok) {
            const categories = await response.json();
            renderCategories(categories);
        } else {
            alert("Error al obtener las categorías");
        }
    } catch (error) {
        console.error("Error al obtener las categorías:", error);
    }
}

// Función para renderizar las categorías en la tabla
function renderCategories(categories) {
    categoriesTable.innerHTML = ""; // Limpiar la tabla
    categories.forEach((category) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${category.categoryId}</td>
            <td>${category.categoryName}</td>
            <td>${category.description}</td>
            <td>
                <button class="edit" data-id="${category.categoryId}">Editar</button>
                <button class="delete" data-id="${category.categoryId}">Eliminar</button>
            </td>
        `;
        categoriesTable.appendChild(row);
    });
}

//funcion para editar y categorias
categoriesTable.addEventListener("click", async (e) => {
    try{
        const response = await fetch(`${API_URL}/get/${categoryId}`);
        if (response.ok) {
            const category = await response.json();
            document.getElementById("categoryId").value = category.categoryId;
            document.getElementById("categoryName").value = category.categoryName;
            document.getElementById("description").value = category.description;
        } else {
            alert("Error al obtener la categoría");
        }
    }
    catch (error) {
        console.error("Error al obtener la categoría:", error);
    }
});

//funcion para eliminar categorias
async function deleteCategory(categoryId) {
    try {
        const response = await fetch(`${API_URL}/delete/${categoryId}`, { method: "DELETE" });
        if (response.ok) {
            alert("Categoría eliminada correctamente");
            fetchCategories(); // Recargar la tabla con los datos actualizados
        }
        else {
            alert("Error al eliminar la categoría");
        }
    }
    catch (error) {
        console.error("Error al eliminar la categoría:", error);
    }
}

//Inicializa la tabla de categorías
fetchCategories();

// funcion para limpiar el formulario
function clearForm() {
    document.getElementById("categoryId").value = "";
    document.getElementById("categoryName").value = "";
    document.getElementById("description").value = "";
    submitButton.disable = false; // Habilitar el botón de enviar
}
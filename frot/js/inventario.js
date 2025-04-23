const API_URL = 'http://localhost:8080/api/v1/inventory';
const inventoryForm = document.getElementById('inventoryForm');
const inventoryTable = document.getElementById('inventoryTable');
const submitButton = document.querySelector('#inventoryForm button[type="submit"]');
const MAX_REQUESTS = 5; // Maximum allowed requests
let requestCount = 0; // Request counter

// Funcion para renderizar el inventario en la tabla
async function fetchInventory() {
    try {
        const response = await fetch(API_URL);
        if (response.ok) {
            const inventory = await response.json();
            renderInventory(inventory);
        } else {
            alert('Error al obtener el inventario');
        }
    } catch (error) {
        console.error('Error al obtener el inventario:', error);
    }
}

// Validaciones y limitaciones en el registro
inventoryForm.addEventListener('submit', async (e) => {
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
    const productId = document.getElementById('productId').value.trim();
    const productName = document.getElementById('productName').value.trim();
    const quantity = parseInt(document.getElementById('quantity').value.trim());
    const price = parseFloat(document.getElementById('price').value.trim());
    const expirationDate = document.getElementById('expirationDate').value.trim();
    if (!productId || !productName || !quantity || !price || !expirationDate) {
        alert('Datos inválidos. Asegúrate de que todos los campos sean correctos.');
        return;
    }
    const inventoryItem = {
        productId: productId,
        productName: productName,
        quantity: quantity,
        price: price,
        expirationDate: expirationDate
    };
    const method = productId ? 'PUT' : 'POST';
    const url = productId ? `${API_URL}/update/${productId}` : API_URL;
    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(inventoryItem)
        });
        if (response.ok) {
            alert('Inventario guardado correctamente');
            fetchInventory();
        } else {
            alert('Error al guardar el inventario');
        }
    } catch (error) {
        console.error('Error al guardar el inventario:', error);
    }
});

//Funcion para renderizar el inventario en la tabla
function renderInventory(inventory) {
    inventoryTable.innerHTML = '';
    inventory.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.productId}</td>
            <td>${item.productName}</td>
            <td>${item.quantity}</td>
            <td>${item.price}</td>
            <td>${item.expirationDate}</td>
            <td>
                <button onclick="editInventoryItem('${item.productId}')">Editar</button>
                <button onclick="deleteInventoryItem('${item.productId}')">Eliminar</button>
            </td>
        `;
        inventoryTable.appendChild(row);
    });
}

//Funcion para editar el inventario
async function editInventoryItem(productId) {
    try {
        const response = await fetch(`${API_URL}/get/${productId}`);
        if (response.ok) {
            const inventoryItem = await response.json();
            document.getElementById('productId').value = inventoryItem.productId;
            document.getElementById('productName').value = inventoryItem.productName;
            document.getElementById('quantity').value = inventoryItem.quantity;
            document.getElementById('price').value = inventoryItem.price;
            document.getElementById('expirationDate').value = inventoryItem.expirationDate;
        } else {
            alert('Error al obtener el producto');
        }
    } catch (error) {
        console.error('Error al obtener el producto:', error);
    }
}

//Funcion para eliminar el inventario
async function deleteInventoryItem(productId) {
    if (confirm('¿Estás seguro de que deseas eliminar este producto?')) {
        try {
            const response = await fetch(`${API_URL}/delete/${productId}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert('Producto eliminado correctamente');
                fetchInventory();
            } else {
                alert('Error al eliminar el producto');
            }
        } catch (error) {
            console.error('Error al eliminar el producto:', error);
        }
    }
}

// Inicializar la tabla de inventario al cargar la página
fetchInventory();

// Función para limpiar el formulario
function clearForm() {
    document.getElementById('productId').value = '';
    document.getElementById('productName').value = '';
    document.getElementById('quantity').value = '';
    document.getElementById('price').value = '';
    document.getElementById('expirationDate').value = '';
    submitButton.disabled = false; // Habilitar el botón de envío
}
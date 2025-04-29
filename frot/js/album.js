const API_URL = 'http://127.0.0.1:8080/api/v1/albumes'; // URL del backend

// Obtener y mostrar álbumes
async function fetchAlbums() {
  try {
    const response = await fetch(API_URL);
    if (!response.ok) throw new Error('Error al obtener los álbumes');
    const albums = await response.json();

    const albumContainer = document.getElementById('albumList');
    albumContainer.innerHTML = ''; // Limpiar contenido previo

    albums.forEach(album => {
      const albumItem = `
        <div class="card mb-3 me-3" style="width: 18rem;">
          <div class="card-body">
            <h5 class="card-title">${album.title}</h5>
            <p class="card-text">Artista: ${album.artist}</p>
            <p class="card-text">Año: ${album.year}</p>
            <button class="btn btn-danger" onclick="deleteAlbum(${album.id})">Eliminar</button>
          </div>
        </div>
      `;
      albumContainer.innerHTML += albumItem;
    });
  } catch (error) {
    console.error(error);
    alert('Error al cargar los álbumes');
  }
}

// Agregar nuevo álbum
const albumForm = document.getElementById('albumForm');
albumForm.addEventListener('submit', async (e) => {
  e.preventDefault();

  const newAlbum = {
    title: document.getElementById('albumTitle').value,
    artist: document.getElementById('albumArtist').value,
    year: document.getElementById('albumYear').value,
  };

  try {
    const response = await fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newAlbum),
    });
    if (!response.ok) throw new Error('Error al guardar el álbum');
    alert('Álbum agregado exitosamente');
    albumForm.reset(); // Limpiar campos del formulario
    fetchAlbums(); // Actualizar la lista
  } catch (error) {
    console.error(error);
    alert('Error al guardar el álbum');
  }
});

// Eliminar álbum
async function deleteAlbum(id) {
  try {
    const response = await fetch(`${API_URL}/delete/${id}`, { method: 'DELETE' }); // Ruta corregida
    if (!response.ok) throw new Error('Error al eliminar el álbum');
    alert('Álbum eliminado exitosamente');
    fetchAlbums(); // Actualizar la lista
  } catch (error) {
    console.error(error);
    alert('Error al eliminar el álbum');
  }
}

// Cargar álbumes al iniciar
fetchAlbums();
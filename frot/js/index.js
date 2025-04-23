const ApiUrl = "http://localhost:8080/api/v1/videojuegos";
// Cargar los videojuegos en el index

const cargarVideojuegos = () => {
  const contenedor = document.getElementById("videojuegosDestacados");
  contenedor.innerHTML = ""; // Limpiar contenido previo

  videojuegos.forEach(juego => {
    contenedor.innerHTML += `
      <div class="col-md-3 videojuego-card">
        <div class="card">
          <img src="https://via.placeholder.com/150" class="card-img-top" alt="${juego.titulo}">
          <div class="card-body">
            <h5 class="card-title">${juego.titulo}</h5>
            <p class="card-text">${juego.categoria}</p>
            <p class="card-text"><strong>$${juego.precio}</strong></p>
          </div>
        </div>
      </div>
    `;
  });
};

// Filtrar videojuegos por nombre
const filtrarVideojuegos = () => {
  const busqueda = document.getElementById("buscador").value.toLowerCase();
  const videojuegosFiltrados = videojuegos.filter(juego => juego.titulo.toLowerCase().includes(busqueda));

  // Re-cargar los videojuegos filtrados
  const contenedor = document.getElementById("videojuegosDestacados");
  contenedor.innerHTML = ""; // Limpiar contenido previo

  videojuegosFiltrados.forEach(juego => {
    contenedor.innerHTML += `
      <div class="col-md-3 videojuego-card">
        <div class="card">
          <img src="https://via.placeholder.com/150" class="card-img-top" alt="${juego.titulo}">
          <div class="card-body">
            <h5 class="card-title">${juego.titulo}</h5>
            <p class="card-text">${juego.categoria}</p>
            <p class="card-text"><strong>$${juego.precio}</strong></p>
          </div>
        </div>
      </div>
    `;
  });
};

// Inicializar
cargarVideojuegos();
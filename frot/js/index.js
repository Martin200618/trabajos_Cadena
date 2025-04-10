function cargarDashboard() {
  // Aquí puedes cargar información general, por ejemplo proyectos existentes
    fetch("http://localhost:8080/api/proyectos")
        .then(res => res.json())
        .then(data => {
            const container = document.getElementById("dashboard");
            container.innerHTML = "<h2>Proyectos disponibles:</h2>";

            if (data.length === 0) {
                container.innerHTML += "<p>No hay proyectos registrados aún.</p>";
                return;
            }

            const list = document.createElement("ul");
            data.forEach(proyecto => {
                const item = document.createElement("li");
                item.textContent = proyecto.nombre + " - " + proyecto.descripcion;
                list.appendChild(item);
            });

            container.appendChild(list);
        })
        .catch(error => {
            console.error("Error al cargar los proyectos:", error);
            document.getElementById("dashboard").innerHTML = "<p>Error al cargar los datos.</p>";
        });
}
document.getElementById("loginForm").addEventListener("submit", async function (e) {
    e.preventDefault();
  
    const correo = document.getElementById("correo").value;
    const contrasena = document.getElementById("contrasena").value;
    const errorMensaje = document.getElementById("errorMensaje");
  
    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ correo, contrasena })
      });
  
      if (response.ok) {
        const data = await response.json();
        // Guardar token o datos de sesión si es necesario
        localStorage.setItem("usuario", JSON.stringify(data));
        window.location.href = "index.html";
      } else {
        const error = await response.json();
        errorMensaje.textContent = error.mensaje || "Credenciales incorrectas.";
      }
    } catch (error) {
      errorMensaje.textContent = "Error de conexión con el servidor.";
    }
});  
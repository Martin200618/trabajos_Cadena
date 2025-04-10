document.getElementById("formRegistro").addEventListener("submit", async function(e) {
    e.preventDefault();

    const nombre = document.getElementById("nombre").value;
    const correo = document.getElementById("correo").value;
    const contraseña = document.getElementById("contraseña").value;

    const data = {
        nombre: nombre,
        correo: correo,
        contraseña: contraseña
    };

    try {
        const response = await fetch("http://localhost:8080/api/usuarios", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();
        alert(result.mensaje || "Usuario registrado correctamente.");
    } catch (error) {
        console.error("Error al registrar usuario:", error);
        alert("Ocurrió un error al registrar el usuario.");
    }
});
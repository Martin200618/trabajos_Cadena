const API_URL = "http://localhost:8085/api/v1/user"

async function loginUser() {
  try {
    const email = document.getElementById("loginEmail").value.trim();
    const password = document.getElementById("loginPassword").value.trim();

    if (!email || !password) {
      alert("El correo y la contraseña son obligatorios");
      return;
    }

    let bodyContent = JSON.stringify({
      email: email,
      password: password
    });

    let response = await fetch(`${API_URL}/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: bodyContent
    });

    if (response.ok) {
      let data = await response.json();
      alert(data.message);
      window.location.href = "./proyectos.html";
    } else {
      let errorData = await response.json();
      alert(errorData.message);
    }
  } catch (error) {
    console.error("Error al iniciar sesión:", error);
    alert("Ocurrió un error durante el inicio de sesión: " + error.message);
  }
}

async function registerUser() {
  try {
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!name || !email || !password) {
      alert("Todos los campos son requeridos");
      return;
    }

    let bodyContent = JSON.stringify({
      name: name,
      email: email,
      password: password
    });

    let response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: bodyContent
    });

    if (response.ok) {
      let data = await response.text();
      alert(data);
    } else {
      let errorText = await response.text();
      alert(`Error: ${errorText}`);
    }
  } catch (error) {
    console.error("Error al registrar el usuario:", error);
    alert("Ocurrió un error durante el registro: " + error.message);
  }
}
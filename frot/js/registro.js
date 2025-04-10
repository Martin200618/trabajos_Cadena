const API_URL = "http://localhost:8085/api/v1/user";

document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector("#registerModal form");
  if (form) {
    form.addEventListener("submit", async (e) => {
      e.preventDefault();
      await registerUser();
    });
  }
});

async function registerUser() {
  try {
    const name = document.getElementById("registerName").value.trim();
    const email = document.getElementById("registerEmail").value.trim();
    const password = document.getElementById("registerPassword").value.trim();

    if (!name || !email || !password) {
      alert("Todos los campos son obligatorios.");
      return;
    }

    const bodyContent = JSON.stringify({
      userId: 0,
      name: name,
      email: email,
      password: password
    });

    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: bodyContent
    });

    if (response.ok) {
      const data = await response.text();
      alert(data);

      document.getElementById("registerName").value = "";
      document.getElementById("registerEmail").value = "";
      document.getElementById("registerPassword").value = "";

      const modal = bootstrap.Modal.getInstance(document.getElementById("registerModal"));
      if (modal) modal.hide();

      window.location.reload();
    } else {
      const errorText = await response.text();
      alert(`Error: ${errorText}`);
    }
  } catch (error) {
    console.error("Error al registrar el usuario", error);
    alert("Ocurrió un error durante el registro: " + error.message);
  }
}
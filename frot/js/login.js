const API_URL = "http://localhost:8085/api/v1/user"

async function loginUser(params) {
  try{
    const email = document.getElementById("Correo Electronico").value.trim();
    const password = document.getElementById("Contraseña").value.trim();


    if(
      !email||
      !password
    ){
      alert("El correo y la contraseña son obligatorios");
      return;
    }

    let bodyContent = JSON.stringify({
      "email": email,
      "password": password
    });

    let response  = await fetch(`${API_URL}/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: bodyContent
    });

    if(response.ok){
      let data = await response.json();
      alert(data.message);
      window.location.href = "/index.html"
    }else{
      let errorData = await response.json();
      alert(errorData.message);
    }
  } catch(error){
    console.error("Error al iniciar sesion:", error);
    alert("Ocurrio un error durante el inicio de sesion: "+ error.message);
  } 
}
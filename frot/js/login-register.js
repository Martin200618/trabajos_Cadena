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

async function registerUser(params) {
    try{
        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();

        if(
            !name || 
            !email || 
            !password
        ){
            alert("Todos los campos son requeridos");
            return;
        }

        let bodyContent = JSON.stringify({
            "userId": 0,
            "name": name,
            "email": email,
            "password": password
        });

        let response = await fetch(API_URL, {
            method: "POST",
            headers:{
                "Content-Type": "application/json"
            },
            body: bodyContent
        });

        if(response.ok){
            let data = await response.text();
            alert(data);
        }else{
            let errorText = await response.text();
            alert(`Error ${errorText}`)
        }
    } catch (error) {
        console.error("Error al registrar el usuario: ", error);
        alert("Ocurrio un error durante el registro: "+ error.message);
    }
}
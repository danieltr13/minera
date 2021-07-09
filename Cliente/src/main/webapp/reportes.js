/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tkn = "";


window.onload = function () {
    var btn = document.getElementById("btnBuscar");
    btn.onclick = () => {
        let e = document.getElementById("cbxReporte");
        let tipoReporte = e.value;
        console.log(tipoReporte);
        if (tipoReporte === "Materiales") {
            reportesMaterials();
        } else if (tipoReporte === "Congestiones") {
            reportesCongestiones();
        }
    };
    var btnUser = document.getElementById("btnUser");
    btnUser.onclick = () => {
        var user = document.getElementById("user");
        var password = document.getElementById("password");
        iniciarSesion(user.value, password.value);
    };
};

async function reportesMaterials() {
    if (tkn === "") {
        alert("Inicie sesión...");
    } else if (tkn === "No se encontró el usuario") {
        alert("Inicie sesión...");
    } else if (tkn !== "" && tkn !== "No se encontró el usuario") {
        try {
            let res = await fetch("http://localhost:8888/reportes/materiales", {
                method: 'POST',
                body: tkn, // data can be `string` or {object}!
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                }
            })
                    .then(res => res.json())
                    .then(reports => {
                        console.log(reports)
                        tablaMateriales(reports)
                    })


        } catch (error) {
            console.log(error);
        }
    }
}
;

async function reportesCongestiones() {
    if (tkn === "") {
        alert("Inicie sesión...");
    } else if (tkn === "No se encontró el usuario") {
        alert("Inicie sesión...");
    } else if (tkn !== "" && tkn !== "No se encontró el usuario") {
        try {
            let res = await fetch("http://localhost:8888/reportes/congestiones",{
                        method: 'POST',
                        body: tkn, // data can be `string` or {object}!
                        headers: {
                            'Content-Type': 'application/json; charset=UTF-8'
                        }
                    })
                    .then(res => res.json())
                    .then(reports => {
                        console.log(reports)
                        tablaCongestiones(reports)
                    })
        } catch (error) {
            console.log(error);
        }
    }
}
;

async function iniciarSesion(name, password) {
    try {
        let data = {name: name,
            password: password};
        console.log(JSON.parse(JSON.stringify(data)))
        let res = await fetch("http://localhost:8888/usuario/validar", {
            method: 'POST',
            body: JSON.stringify(data), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        })
                .then((res) => res.json()).then(json => {
            tkn = JSON.stringify(json);
            console.log(tkn)
        });
        ;

    } catch (error) {
        console.log(error);
    }
}
;



async function tablaCongestiones(reports) {
    let tablaEncabezado = `
    <table class="center">
    <tr>
      <td>Id</td>
      <td>Eventualidad</td>
      <td>Ubicacion</td>
      <td>Causa</td>
      <td>Matricula</td>
    </tr>
 </table>
        `;
    borrar();
    document.getElementById("materiales").innerHTML += tablaEncabezado;
    for (let i = 0; i < reports.length; i++) {
        let lista = `
    <table class="center">
    <tr>
      <td>${reports[i].id}</td>
      <td>${reports[i].eventualidad}</td>
      <td>${reports[i].ubicacion}</td>
      <td>${reports[i].causa}</td>
      <td>${reports[i].matricula}</td>
    </tr>
  </table>
          `;

        document.getElementById("materiales").innerHTML += lista;
    }
}
;

async function tablaMateriales(reports) {
    let tablaEncabezado = `
    <table class="center">
    <tr>
      <td>Id</td>
      <td>Tipo</td>
      <td>Cantidad</td>
      <td>Material</td>
    </tr>
 </table>
        `;
    borrar();
    document.getElementById("materiales").innerHTML += tablaEncabezado;
    for (let i = 0; i < reports.length; i++) {
        let lista = `
    <table class="center">
    <tr>
      <td>${reports[i].id}</td>
      <td>${reports[i].tipo}</td>
      <td>${reports[i].cantidad}</td>
      <td>${reports[i].vehiculo}</td>
    </tr>
  </table>
          `;
        document.getElementById("materiales").innerHTML += lista;
    }
}
;

function borrar() {
    var dom = document.getElementById("materiales");
    while (dom.hasChildNodes())
        dom.removeChild(dom.firstChild);
}





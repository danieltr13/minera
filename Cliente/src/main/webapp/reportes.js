/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tkn = "";

async function reportesMaterials() {
    try {
        let res = await fetch("http://localhost:8888/reportes/materiales")
                .then(res => res.json())
                .then(reports => {
                    console.log(reports)
                    tablaMateriales(reports)
                })
    } catch (error) {
        console.log(error);
    }
}
;

async function reportesCongestiones() {
    try {
        let res = await fetch("http://localhost:8888/reportes/congestiones")
                .then(res => res.json())
                .then(reports => {
                    console.log(reports)
                    tablaCongestiones(reports)
                })
    } catch (error) {
        console.log(error);
    }
}
;
async function iniciarSesion(name, password) {
    try {
        var data = {name: name,
            password: password};
        console.log(JSON.parse(JSON.stringify(data)))
        let res = await fetch("http://localhost:8888/usuario/validar", {
            method: 'POST',
            body:  JSON.stringify(data), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        })
                .then((res) => res.json()).then(json=>{
                    tkn=json;
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





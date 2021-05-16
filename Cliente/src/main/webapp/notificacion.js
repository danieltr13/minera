/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsNotify = null;
//al cargarse la página se ejecuta esta función
window.onload = function () {
    //hace la conexión al web socket
    wsNotify = new WebSocket
            ("ws://localhost:8080/Cliente/websocketendpointnotificacion");
    //la función que se ejecuta al abrirse la conexión
    wsNotify.onopen = function () {
        console.log("esperando notificación...");
    };
    //se asigna y define la función a ejecutarse cada que llegue un mensaje desde el websocket
    wsNotify.onmessage = function (evt) {
        var received_msg = evt.data;
        alert("notificación: "+received_msg);
    };
    
    wsNotify.onclose = function () {
        alert("cerrado...");
    };
};


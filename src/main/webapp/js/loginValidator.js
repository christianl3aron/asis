/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function validar()
{
    if (document.getElementById("txtUsuario").value == "")
    {
        alert("Ingrese el nombre del usuario");
        document.getElementById("txtUsuario").focus();
        return false;
    }
    if (document.getElementById("txtClave").value == "")
    {
        alert("Ingrese la clave del usuario");
        document.getElementById("txtClave").focus();
        return false;
    }
}
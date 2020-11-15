/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstanciaADMIN;

import Backend.BotConsultas.BootConsultas;
import Backend.DTBconeccion;
import Frontend.Login.Login;
import Frontend.MuroDelCliente.CrearProductos;
import Frontend.MuroDelCliente.DialogCrearProducto;
import Frontend.MuroDelCliente.EditarClienteDialog;
import Frontend.MuroDelCliente.MuroDelCliente;

/**
 *
 * @author edisonjpp
 */
public class InstanciaADMIN {
    public static DTBconeccion coneccion ;
   
    public static BootConsultas Bot;

    public static Login Login;

    public static MuroDelCliente MuroDelCliente;

    public static CrearProductos CrearProductos;
    public static DialogCrearProducto DialogCrearProducto;
    
    public static EditarClienteDialog EditarClienteDialog; 
}

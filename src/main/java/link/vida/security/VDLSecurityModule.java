/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.security;

/**
 * Tipos de Autentificación: 
 *  - Login: email- password
 *  - MAC: mac address y key
 *  - UUID : uuid y key
 * 
 * Tipos de Agrupaciones
 *  - Tenant / namespace: Cliente o inquilino del sistema
 *  - Unit : Unidad oganizacional de la empresa
 *  - App : Aplicación y/o servicio 
 * 
 * 
 * Los permisos se deben configurar por parte de la aplicación, 
 * existe un webservices para la configuración de los privilegios 
 * de los dispositivos, y el usuario y clave administrador/manager, es el que 
 * puede determinar los permiso del resto de dispositivos.
 * 
 * Cáda administrador, puede crear roles y asignarle nombres que el determine.
 * 
 * 
 * @author dcaro
 */
public class VDLSecurityModule {
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.festivo.restfulapi.Exception;

/**
 *
 * @author Sabita Silwal
 */
public class ResourceNotFoundException extends Exception {

    private String message;
    public ResourceNotFoundException(String message) {
        this.message = message;
    }
    
}

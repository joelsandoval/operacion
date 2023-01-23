/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scan.operacion.commons;

/**
 *
 * @author joel.sandoval
 */
public class RestPreconditions {

    public RestPreconditions() {
        // default implementation ignored
    }

    public static <T> T checkFound(final T resource) throws MyResourceNotFoundException {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }
        return resource;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scan.operacion.rest;

import com.scan.operacion.model.Archivos;
import com.scan.operacion.services.FileSFTPService;
import com.scan.operacion.services.FileSystemService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author joel
 */
@RestController
@RequestMapping(value = "filesystem")
@CrossOrigin
public class FileSystemREST {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemREST.class);

    @Autowired
    private Environment env;

    @Autowired
    private FileSystemService serviceFile;

    @Autowired
    private FileSFTPService serviceSftp;

    private static final String TIPOCONTENIDO = "application/octet-stream";
    private static final String TIPOATTACHMENT = "attachment; filename=\"";
    private static final String COMILLAS = "\"";

    @CrossOrigin
    @PostMapping("/upload/servicio/{cliente},{proyecto},{servicio}")
    public Archivos uploadDocsServicio(@RequestParam("file") MultipartFile file,
            @PathVariable("cliente") Integer cliente, @PathVariable("proyecto") Integer proyecto,
            @PathVariable("servicio") Integer servicio) {
        String fileName = serviceFile.storeFileServicio(file, cliente, proyecto, servicio);

        String rutaFs = env.getProperty("file.upload-dir") + cliente.toString() + "/" + proyecto.toString() + "/" + servicio.toString() + "/";

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/filesystem/download/servicio/")
                .path(cliente.toString() + ",")
                .path(proyecto.toString() + ",")
                .path(servicio.toString() + ",")
                .path(fileName)
                .toUriString();
        Date fecha = new Date();

        UserRepresentation userRepresentation = new UserRepresentation();

        Archivos nuevo = new Archivos();
        nuevo.setProyecto(proyecto);
        nuevo.setArchivo(fileName);
        nuevo.setEstatus(1);
        nuevo.setTipo(1);
        nuevo.setDescripcion(fileName);
        nuevo.setAutor(0);
        nuevo.setFecha(fecha);
        nuevo.setExtension(file.getContentType());
        nuevo.setRuta(fileDownloadUri);
        nuevo.setRutaFs(rutaFs);
        nuevo.setFechaCarga(fecha);
        nuevo.setActivo(1);
        nuevo.setSize(BigInteger.valueOf(file.getSize()));
        nuevo.setHost(1);
        return nuevo;
    }

    @GetMapping("/download/servicio/{cliente},{proyecto},{servicio},{fileName:.+}")
    public ResponseEntity<Resource> downloadDgira(@PathVariable Integer cliente, @PathVariable Integer proyecto,
            @PathVariable Integer servicio, @PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource        
        Resource resource = serviceFile.loadFileAsResource(fileName, cliente, proyecto, servicio);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            LOGGER.info("Could not determine file type: descarga dgira.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = TIPOCONTENIDO;
        }

        LOGGER.info("Está descargando un oficio");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, TIPOATTACHMENT + resource.getFilename() + COMILLAS)
                .body(resource);
    }

    @PostMapping("/archivo/borra")
    public void borraArchivo(@RequestBody Archivos archivo) {
        String archi = archivo.getRutaFs() + archivo.getArchivo();
        LOGGER.info(archi);
        serviceFile.deleteFile(archi, archivo.getId(), archivo.getTipo());
    }

    @CrossOrigin
    @PostMapping("/upload-ftp/servicio/{cliente},{proyecto},{servicio}")
    public Archivos uploadFtpDocsServicio(@RequestParam("file") MultipartFile file,
            @PathVariable("cliente") Integer cliente, @PathVariable("proyecto") Integer proyecto,
            @PathVariable("servicio") Integer servicio) {

        String fileName = serviceFile.cargaDocumentoFTP(file, cliente, proyecto, servicio);

        String rutaFs = "/proyectos" + cliente.toString() + "/" + proyecto.toString() + "/" + servicio.toString() + "/";

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/filesystem/download-ftp/servicio/")
                .path(cliente.toString() + ",")
                .path(proyecto.toString() + ",")
                .path(servicio.toString() + ",")
                .path(fileName)
                .toUriString();

        Date fecha = new Date();
        Archivos nuevo = new Archivos();
        nuevo.setProyecto(proyecto);
        nuevo.setArchivo(fileName);
        nuevo.setEstatus(1);
        nuevo.setTipo(1);
        nuevo.setDescripcion(fileName);
        nuevo.setAutor(0);
        nuevo.setFecha(fecha);
        nuevo.setExtension(file.getContentType());
        nuevo.setRuta(fileDownloadUri);
        nuevo.setRutaFs(rutaFs);
        nuevo.setFechaCarga(fecha);
        nuevo.setActivo(1);
        nuevo.setSize(BigInteger.valueOf(file.getSize()));
        nuevo.setHost(2);
        return nuevo;
    }

    @GetMapping("/download-ftp/servicio/{cliente},{proyecto},{servicio},{fileName:.+}")
    public void downloadFtpServicio(@PathVariable Integer cliente, @PathVariable Integer proyecto,
            @PathVariable Integer servicio, @PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource        
        // Resource resource = serviceFile.loadFileAsResource(fileName, cliente, proyecto, servicio);

        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            LOGGER.info("Could not determine file type: descarga dgira.");
//        }
        // Fallback to the default content type if type could not be determined
//        if (contentType == null) {
//            contentType = TIPOCONTENIDO;
//        }
        LOGGER.info("Está descargando un oficio");
        serviceFile.descargaDocumentoFTP(fileName, cliente, proyecto, servicio);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, TIPOATTACHMENT + resource.getFilename() + COMILLAS)
//                .body(resource);
    }

    @PostMapping("imagen")
    @Produces({"image/png"})
    public Response getImage(@RequestParam("ruta") String ruta) {

        BufferedImage image = null;
        byte[] imageData = null;
        try {
            //System.out.println("chemin courant : " + ClassLoader.getSystemClassLoader().
            //      getResource(".").getPath());
            image = ImageIO.read(new File(ruta));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            imageData = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("image : " + image);
        LOGGER.info("Image data : " + imageData);
        return Response.ok(new ByteArrayInputStream(imageData)).build();
    }

    @PostMapping("/upload/proyecto/{cliente},{proyecto}")
    public Archivos uploadDocsProyecto(@RequestParam("file") MultipartFile file, @PathVariable("cliente") Integer cliente, @PathVariable("proyecto") Integer proyecto) {

        String fileName = serviceFile.storeFileProyecto(file, cliente, proyecto);
        String rutaFs = env.getProperty("file.upload-dir") + cliente.toString() + "/" + proyecto.toString() + "/";

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/filesystem/download/proyecto/")
                .path(cliente.toString() + ",")
                .path(proyecto.toString() + ",")
                .path(fileName)
                .toUriString();
        Date fecha = new Date();
        Archivos nuevo = new Archivos();
        nuevo.setProyecto(proyecto);
        nuevo.setArchivo(fileName);
        nuevo.setEstatus(1);
        nuevo.setTipo(1);
        nuevo.setDescripcion(fileName);
        nuevo.setAutor(0);
        nuevo.setFecha(fecha);
        nuevo.setExtension(file.getContentType());
        nuevo.setRuta(fileDownloadUri);
        nuevo.setRutaFs(rutaFs);
        nuevo.setFechaCarga(fecha);
        nuevo.setActivo(1);
        nuevo.setSize(BigInteger.valueOf(file.getSize()));
        nuevo.setHost(1);
        return nuevo;
    }

    @GetMapping("/download/proyecto/{cliente},{proyecto},{fileName:.+}")
    public ResponseEntity<Resource> downloadProyecto(@PathVariable Integer cliente, @PathVariable Integer proyecto, @PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource        
        Resource resource = serviceFile.loadFileProyecto(fileName, cliente, proyecto);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            LOGGER.info("Could not determine file type: descarga dgira.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = TIPOCONTENIDO;
        }

        LOGGER.info("Está descargando un oficio");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, TIPOATTACHMENT + resource.getFilename() + COMILLAS)
                .body(resource);
    }

    @GetMapping(path = "/user")
    public KeycloakPrincipal getUserInfo() {

        final KeycloakPrincipal user = (KeycloakPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        
        return user;
    }

}

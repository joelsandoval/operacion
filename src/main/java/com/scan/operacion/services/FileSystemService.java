/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.services;

import com.scan.operacion.commons.FileStorageException;
import com.scan.operacion.commons.MyFileNotFoundException;
import com.scan.operacion.dao.ArchivosRepository;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Normalizer;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Joel
 */
@Service
public class FileSystemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemService.class);
    private static final String MENSAJEENCONTRADO = "File not found";
    private static final String MENSAJEDIRECTORIO = "Could not create the directory where the uploaded files will be stored.";
    private static final String MENSAJERUTA = "Sorry! Filename contains invalid path sequence";
    private static final String MENSAJESTORE = "Could not store file. ";
    private static final String MENSAJETRY = "Please try again!";
    private static final String MENSAJEMALFORMED = "Malformed ";

    private final String remoteHost = "ftp.indocumentado.com.mx";
    private final String username = "u716614094.lugh";
    private final String password = "Ezequiel2517";
    private final Integer port = 21;

    @Autowired
    private Environment env;

    @Autowired
    private ArchivosRepository repoA;

    public String storeFileServicio(MultipartFile file, Integer cliente, Integer proyecto, Integer servicio) {

        String fileName = armaFileName(file.getOriginalFilename());
        String ruta = env.getProperty("file.upload-dir") + cliente.toString() + "/" + proyecto.toString() + "/" + servicio.toString() + "/";
        Path fileStorageFinal = Paths.get(ruta).toAbsolutePath().normalize();
        LOGGER.info(ruta);

        try {
            Files.createDirectories(fileStorageFinal);
            LOGGER.info("Se crea el directorio {}", ruta);
        } catch (IOException ex) {
            throw new FileStorageException(MENSAJEDIRECTORIO, ex);
        }

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException(MENSAJERUTA + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)            
            Path targetLocation = fileStorageFinal.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Archivo creado con éxito {}", fileName);
            return fileName;

        } catch (IOException ex) {
            throw new FileStorageException(MENSAJESTORE + fileName + MENSAJETRY, ex);
        }
    }

    public String armaFileName(String s) {
        s = StringUtils.cleanPath(s);
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll(" ", "_");
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    public Resource loadFileAsResource(String fileName, Integer cliente, Integer proyecto, Integer servicio) {

        try {
            String rutaP = env.getProperty("file.upload-dir") + cliente.toString() + "/" + proyecto.toString() + "/" + servicio.toString() + "/";
            LOGGER.info("rutaP: {}", rutaP);
            Path fileStorageFinal = Paths.get(rutaP).toAbsolutePath().normalize();
            Path filePath = fileStorageFinal.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                //serviceAud.registraDescarga(filePath.toString(), 0, bitacora);
                return resource;
            } else {
                throw new MyFileNotFoundException(MENSAJEENCONTRADO + rutaP);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException(MENSAJEENCONTRADO + fileName, ex);
        }
    }

    public void deleteFile(String name, Integer aidi, Integer tipo) {
        File archivo = new File(name);
        if (archivo.delete()) {
            LOGGER.info("Se borró el archivo: " + archivo.getName());
            repoA.deleteById(aidi);
            LOGGER.info("Se borró el archivo en la base de datos: " + archivo.getName());
//                if(tipo >= 200 && tipo < 300) {
//                    repoOFA.delFlujoArchivo(aidi);
//                    LOGGER.info("se borró de la tabla oficios_flujo: " + archivo.getName());
//                }
        } else {
            LOGGER.info("Deleted the file: " + archivo.getName());
        }

    }
    
    
    
    
    public String cargaDocumentoFTP(MultipartFile file, Integer cliente, Integer proyecto, Integer servicio) {

        FTPClient ftpClient = new FTPClient();
        String respuesta = "";
        try {

            ftpClient.connect(remoteHost, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            Boolean ok = makeDirectories(ftpClient, "proyectos/" + cliente + "/" + proyecto + "/" + servicio);

            if (ok) {
                
                String remoteFile = armaFileName(file.getOriginalFilename());
                InputStream inputStream = file.getInputStream();
                System.out.println("Start uploading file");
                
                OutputStream outputStream = ftpClient.storeFileStream(remoteFile);
                byte[] bytesIn = new byte[4096];
                int read = 0;

                while ((read = inputStream.read(bytesIn)) != -1) {
                    outputStream.write(bytesIn, 0, read);
                }
                
                inputStream.close();
                outputStream.close();

                boolean completed = ftpClient.completePendingCommand();
                if (completed) {
                    respuesta = remoteFile;
                    LOGGER.info("The second file is uploaded successfully.");
                }
            }

        } catch (IOException ex) {
            LOGGER.error("Error durante la carga: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                LOGGER.error("Error al desconectar: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                return respuesta;
            }
        }
    }

    /**
     * Creates a nested directory structure on a FTP server
     *
     * @param ftpClient an instance of org.apache.commons.net.ftp.FTPClient
     * class.
     * @param dirPath Path of the directory, i.e /projects/java/ftp/demo
     * @return true if the directory was created successfully, false otherwise
     * @throws IOException if any error occurred during client-server
     * communication
     */
    public static boolean makeDirectories(FTPClient ftpClient, String dirPath) throws IOException {
        LOGGER.info("La ruta que se pasa {}", dirPath);
        String[] pathElements = dirPath.split("/");
        LOGGER.info("Algo pasó {}", pathElements.length);

        if (pathElements.length > 0) {
            for (String singleDir : pathElements) {
                LOGGER.info(singleDir);

                boolean existed = ftpClient.changeWorkingDirectory(singleDir);
                if (!existed) {
                    boolean created = ftpClient.makeDirectory(singleDir);
                    if (created) {
                        System.out.println("CREATED directory: " + singleDir);
                        ftpClient.changeWorkingDirectory(singleDir);
                    } else {
                        System.out.println("COULD NOT create directory: " + singleDir);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    
    public void descargaDocumentoFTP(String fileName, Integer cliente, Integer proyecto, Integer servicio) {
        
        FTPClient ftpClient = new FTPClient();
        
        try {
            ftpClient.connect(remoteHost, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            String rutaP = "proyectos/" + cliente.toString() + "/" + proyecto.toString() + "/" + servicio.toString() + "/";
            String remoteFile = rutaP + fileName;
            LOGGER.info("rutaP: {}", rutaP);
            
            File downloadFile = new File("C:/descargas/" + fileName);

            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream.write(bytesArray, 0, bytesRead);
            }
 
            Boolean success = ftpClient.completePendingCommand();
            
            if (success) {
                LOGGER.info("File {} has been downloaded successfully", fileName);
            }
            outputStream.close();
            inputStream.close();
 
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    

    public String storeFileProyecto(MultipartFile file, Integer cliente, Integer proyecto) {

        String fileName = armaFileName(file.getOriginalFilename());
        String ruta = env.getProperty("file.upload-dir") + cliente.toString() + "/" + proyecto.toString() + "/";
        Path fileStorageFinal = Paths.get(ruta).toAbsolutePath().normalize();
        LOGGER.info(ruta);

        try {
            Files.createDirectories(fileStorageFinal);
            LOGGER.info("Se crea el directorio {}", ruta);
        } catch (IOException ex) {
            throw new FileStorageException(MENSAJEDIRECTORIO, ex);
        }

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException(MENSAJERUTA + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)            
            Path targetLocation = fileStorageFinal.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Archivo creado con éxito {}", fileName);
            return fileName;

        } catch (IOException ex) {
            throw new FileStorageException(MENSAJESTORE + fileName + MENSAJETRY, ex);
        }
    }
    
    public Resource loadFileProyecto(String fileName, Integer cliente, Integer proyecto) {

        try {
            String rutaP = env.getProperty("file.upload-dir") + cliente.toString() + "/" + proyecto.toString() + "/";
            LOGGER.info("rutaP: {}", rutaP);
            Path fileStorageFinal = Paths.get(rutaP).toAbsolutePath().normalize();
            Path filePath = fileStorageFinal.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                //serviceAud.registraDescarga(filePath.toString(), 0, bitacora);
                return resource;
            } else {
                throw new MyFileNotFoundException(MENSAJEENCONTRADO + rutaP);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException(MENSAJEENCONTRADO + fileName, ex);
        }
    }
    
}

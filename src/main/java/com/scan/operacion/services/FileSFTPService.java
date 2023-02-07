/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scan.operacion.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Joel
 */
@Service
public class FileSFTPService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSFTPService.class);

    private String remoteHost = "ftp.indocumentado.com.mx";
    private String username = "u716614094.lugh";
    private String password = "Ezequiel2517";
    private Integer port = 21;

    public void cargaDocumento() {

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(remoteHost, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File("/home/scan/desarrollo/proyectos/1/13/33/Dell_-_Sitio_Oficial_de_Laptops,_Netbooks_y_Computadoras.pdf");

            Boolean ok = makeDirectories(ftpClient, "proyectos/1/13/33");

            if (ok) {
                String firstRemoteFile = "Dell_-_Sitio_Oficial_de_Laptops,_Netbooks_y_Computadoras.pdf";
                InputStream inputStream = new FileInputStream(firstLocalFile);

                System.out.println("Start uploading first file");
                boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
                inputStream.close();
                if (done) {
                    System.out.println("The first file is uploaded successfully.");
                }

                // APPROACH #2: uploads second file using an OutputStream
                File secondLocalFile = new File("/home/scan/desarrollo/proyectos/1/13/33/Ampliacion_plazo.pdf");
                String secondRemoteFile = "Ampliacion_plazo.pdf";
                inputStream = new FileInputStream(secondLocalFile);

                System.out.println("Start uploading second file");
                OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
                byte[] bytesIn = new byte[4096];
                int read = 0;

                while ((read = inputStream.read(bytesIn)) != -1) {
                    outputStream.write(bytesIn, 0, read);
                }
                inputStream.close();
                outputStream.close();

                boolean completed = ftpClient.completePendingCommand();
                if (completed) {
                    System.out.println("The second file is uploaded successfully.");
                }
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
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

}

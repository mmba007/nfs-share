package com.nfsshare.nfsshare;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class NfsService {


    public Boolean connectToSharedFolder(String serverName, String sharedFolder) {
        // Replace these with your shared folder details
        String sharedFolderPath = "\\\\" + serverName + "\\" + sharedFolder; // Replace with the path to your shared folder
//        String username = "your_username";
//        String password = "your_password";

        // Provide the credentials if required
        // You may skip this part if you are already authenticated to access the shared folder
//        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, username, password);

//        Use this in pom.xml:
//            <dependency>
//                <groupId>org.codelibs</groupId>
//                <artifactId>jcifs-ng</artifactId>
//                <version>2.0.9</version>
//            </dependency>

        // Connect to the shared folder
        Path sharedPath = Paths.get(sharedFolderPath);

        if (!Files.exists(sharedPath)) {
            System.out.println("Shared folder does not exist.");
            return false;
        }
        System.out.println("Connected to NFS successfully.");

        try (Stream<Path> paths = Files.walk(sharedPath)) {
            paths.filter(Files::isRegularFile)
                    .forEach(file -> {
                        // Process each file in the shared folder
                        System.out.println("Found file: " + file);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

//    public static Boolean connectToNFSFolder(String nfsServerIp, String nfsShareFolder) {
//        // Replace these with your shared folder details
//        String sharedFolderPath = "smb://" + nfsServerIp + "/" + nfsShareFolder; // Replace with the path to your shared folder
////        String username = "your_username";
////        String password = "your_password";
//
////        Use this in pom.xml:
////            <dependency>
////                <groupId>org.codelibs</groupId>
////                <artifactId>jcifs-ng</artifactId>
////                <version>2.0.9</version>
////            </dependency>
//        // SmbFile smbFile;
//        // Connect to the NFS share folder
//        smbFile = new SmbFile(nfsSharePath, username, password);
//
//
//        if (!smbFile.exists()) {
//            logger.error("NFS share folder does not exist.");
//            return false;
//        }
//        return true;
//    }

    public static boolean readNFSOrShared(String pathToFolder) {
        // Replace this with the path to your shared folder or NFS share
        String sharedFolderPath = "smb://server_name/shared_folder_or_nfs_share";
        try {
            URL url = new URL(sharedFolderPath);
            URLConnection conn = url.openConnection();

            // Read from the connection
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}

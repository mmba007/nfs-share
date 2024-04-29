package com.nfsshare.nfsshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nfs/files")
public class NfsController {

    @Autowired
    private NfsService nfsService;

    @GetMapping("/connect")
    public ResponseEntity<String> connectToNFS(@RequestParam String serverName, @RequestParam String sharedFolder) {
        boolean isConnected = nfsService.connectToSharedFolder(serverName, sharedFolder);
        return isConnected ? ResponseEntity.ok("Connected to NFS successfully.") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to connect to NFS.");
    }
}

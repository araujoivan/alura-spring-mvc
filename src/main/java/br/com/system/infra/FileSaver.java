/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.infra;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author eder
 */

@Component
public class FileSaver {
    
    @Autowired
    private HttpServletRequest request;
    
    public String write(String baseFolder, MultipartFile file) {
                
        String path = "";
        
        try {
            
            final String realPath = request.getServletContext().getRealPath(File.separator.concat(baseFolder));
            
            if(!new File(realPath).exists())
            {
                new File(realPath).mkdir();
            }
            
            path = realPath.concat(File.separator).concat(file.getOriginalFilename());
            
            file.transferTo(new File(path));
            
        } catch (IllegalStateException | IOException ex) {
            throw new RuntimeException(ex);
        }
        
        return path;
    }
    
}

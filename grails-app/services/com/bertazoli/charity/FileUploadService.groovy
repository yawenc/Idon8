package com.bertazoli.charity

import grails.transaction.Transactional
import liquibase.util.MD5Util
import org.springframework.web.multipart.MultipartFile

@Transactional
class FileUploadService {


    def serviceMethod() {

    }

    def String saveFile(MultipartFile file) {
        String md5sum = MD5Util.computeMD5(file.inputStream)
        String fileName = md5sum+"."+file.getContentType().replaceAll("image/","")

        File uploadDirectory = new File("web-app/upload")
        if (!uploadDirectory.exists()) {
            println("Creating upload directory")
            if (uploadDirectory.mkdirs()) {
                println("SUCCESS")
            } else {
                println("FAILED")
            }
        }

        if (uploadDirectory.exists()) {
            File tempFile = new File("${uploadDirectory.getAbsolutePath()}/${fileName}")
            if (!tempFile.exists()) {
                file.transferTo(new File("${uploadDirectory.getAbsolutePath()}/${fileName}"))
            }
            return fileName
        }
        return null
    }

    def getImage(String fileName) {
        File image = new File("web-app/upload/"+fileName)
        if (image.exists()) {
            println(image.length())
            return image
        }

        return "not found"
    }
}

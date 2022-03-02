package az.ekadr.service;

import lombok.SneakyThrows;

import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;

public class FileUploadService {

    @SneakyThrows
    public static Blob uploadFile(Part file){
        InputStream inputStream = file.getInputStream();
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        Blob blob = new SerialBlob(buffer);
        return blob;
    }

}

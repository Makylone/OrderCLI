package com.Makylone.OrderCLI.Utils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.Makylone.OrderCLI.Models.MetaData;

public class PathManager {
    public static void moveFile(Path filePath) {
        try{    
            System.out.println("File: " + filePath.getFileName() + " is a directory? " + Files.isDirectory(filePath));
            boolean isDirectoryAYear = DateParser.isDirectoryAYear(filePath);
            boolean isDirectoryAMonth = DateParser.isDirectoryAMonth(filePath);
            if(isDirectoryAYear || isDirectoryAMonth){
                return;
            }
            Path container = PathManager.getDestinationPath(filePath);
            System.out.println(container.getFileName());
            Files.createDirectories(container);
            Path target = container.resolve(filePath.getFileName());
            PathManager.mergeOrMove(filePath, target);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void mergeOrMove(Path source, Path dest) throws IOException {
        if(!Files.isDirectory(source)){
            Files.move(source, dest, StandardCopyOption.REPLACE_EXISTING);
            return;
        }
        if(Files.exists(dest)){
            try( DirectoryStream<Path> files = Files.newDirectoryStream(source)){
                for(Path file : files){
                    mergeOrMove(file, dest.resolve(file.getFileName()));
                }
                Files.delete(source);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Files.move(source, dest, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    private static Path getDestinationPath(Path source) throws IOException{
        MetaData metadata = ExactInfoFile.getData(source.toAbsolutePath());
        int year = metadata.year();
        String month = metadata.month();
        Path yearDirectoryPath = source.getParent().resolve(Integer.toString(year));
        System.out.println("yearDirectoryPath: " +yearDirectoryPath);
        Path monThDirectoryPath = yearDirectoryPath.resolve(month);
        System.out.println("monThDirectoryPath: " +monThDirectoryPath);
        return monThDirectoryPath;
    }
}

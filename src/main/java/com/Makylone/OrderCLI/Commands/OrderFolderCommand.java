package com.Makylone.OrderCLI.Commands;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.Makylone.OrderCLI.Utils.PathManager;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "order", description = "Reorder the directory")
public class OrderFolderCommand implements Runnable{

    @Parameters(index = "0", defaultValue = "~/Downloads", description = "The path of the directory to reorder")
    private String path;

    @Override
    public void run() {
        System.out.println(path);
        Path directory = Paths.get(path);
        try( DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            if(files != null){
                for(Path file : files){
                    PathManager.moveFile(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }

}
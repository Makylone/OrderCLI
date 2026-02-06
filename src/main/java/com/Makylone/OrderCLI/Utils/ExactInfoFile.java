package com.Makylone.OrderCLI.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.Makylone.OrderCLI.Models.MetaData;

public class ExactInfoFile {
    public static MetaData getData(Path path) throws IOException{
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        Instant instantYear = attributes.lastModifiedTime().toInstant();
        int yearOfCreation = LocalDateTime.ofInstant(instantYear, ZoneId.of("Europe/Paris")).getYear();
        String monthOfCreation = LocalDateTime.ofInstant(instantYear, ZoneId.of("Europe/Paris")).getMonth().toString();
        return new MetaData(yearOfCreation, monthOfCreation);
    }
}

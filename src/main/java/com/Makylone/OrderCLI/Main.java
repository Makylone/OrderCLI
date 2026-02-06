package com.Makylone.OrderCLI;

import com.Makylone.OrderCLI.Commands.OrderFolderCommand;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
       int exitCode = new CommandLine(new OrderFolderCommand()).execute(args);
       System.exit(exitCode);
    }
}

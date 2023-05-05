package io;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * The type User io.
 */
public class UserIO {

    /**
     * The Scanner.
     */
    Scanner scanner;

    /**
     * Instantiates a new User io.
     */
    public UserIO() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    /**
     * Instantiates a new User io.
     *
     * @param scanner the scanner
     */
    public UserIO(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Read line string.
     *
     * @return the string
     */
    public String readLine(){
        return scanner.nextLine();
    }

    /**
     * Print command text.
     *
     * @param str the str
     */
    public void printCommandText(String str){
        System.out.println(str);
    }

    /**
     * Print command error.
     *
     * @param str the str
     */
    public void printCommandError(String str){
        System.err.println(str);
    }

    /**
     * Print preamble.
     */
    public void printPreamble(){
        System.out.println(">");
    }
}

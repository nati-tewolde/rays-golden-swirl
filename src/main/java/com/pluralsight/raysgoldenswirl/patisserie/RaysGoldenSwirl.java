package com.pluralsight.raysgoldenswirl.patisserie;

import java.util.Scanner;

public class RaysGoldenSwirl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface();
        ui.startProgram(scanner);

        scanner.close();
    }
}

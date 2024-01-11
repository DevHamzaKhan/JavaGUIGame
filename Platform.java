/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Basic Platform creation with all attributes and variable to check if active platform.
*/

public class Platform {
    int x; int y; int width; int height; int type; int active;

    public Platform(int x, int y, int width, int height, int active) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.active = active;
    }
}

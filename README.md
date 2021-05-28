# CISC 191 (Spring 2021, Group A) Final Project [Tic-tac-toe]

## Starting the game
Run the server class once to host a game. The host will be waiting until a client joins.
Once a server is running, add as many pairs of clients as desired. 
The game will start once a host is paired with a client.

## Prerequisites

1. Maven
2. Git
3. JDK 8

## Building

mvn clean install

## Running

java -jar Server/target/Server-1.0.0.jar  
java -jar Client/target/Client-1.0.0.jar

## Common Module

Shared classes between the client and server modules.

## Server Module

The server application that handles multiple clients.

## Client Module

The client application used to connect to the server.

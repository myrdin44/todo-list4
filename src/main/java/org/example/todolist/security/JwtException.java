package org.example.todolist.security;

public class JwtException extends RuntimeException{
    public JwtException(String message){
        super(message);
    }
}

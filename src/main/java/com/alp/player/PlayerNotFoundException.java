package com.alp.player;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such player!")
public class PlayerNotFoundException extends RuntimeException {

    PlayerNotFoundException(String id) { super("Did not find player:["+id+"]"); }
}

package com.codehub.resource;

import com.codehub.exceptions.BadEntityException;
import com.codehub.representation.UserRepresentation;
import org.restlet.resource.Post;

public interface UserSignInResource {

    @Post("json")
    public UserRepresentation add(UserRepresentation userReprIn)

            throws BadEntityException;
}

package com.codehub.security;

import org.restlet.Application;
import org.restlet.data.ChallengeScheme;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Verifier;

public class Shield {


   private Application application;

   public Shield(Application application){
       this.application = application;
   }

   public ChallengeAuthenticator createApiGuard() {

       ChallengeAuthenticator apiGuard = new ChallengeAuthenticator(
               application.getContext(), ChallengeScheme.HTTP_BASIC, "realm");

       // Verifier: check authentication
       // Enroler: to check authorization (roles)
       Verifier verifier = new CustomVerifier();
       apiGuard.setVerifier(verifier);

       return  apiGuard;

   }


}

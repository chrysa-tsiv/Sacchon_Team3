package com.codehub.security;

import static java.lang.Integer.parseInt;

public enum CustomRole {

    ROLE_NA("n/a"),
    ROLE_ADMIN("admin"),
    ROLE_DOCTOR("doctor"),
    ROLE_PATIENT("patient");

    private final String roleName;

    CustomRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static CustomRole getRoleValue(String roleParameter) {
        for (CustomRole role : CustomRole.values()) {
            if (parseInt(roleParameter)==role.ordinal())
                return role;
        }
        return ROLE_NA;
    }

}

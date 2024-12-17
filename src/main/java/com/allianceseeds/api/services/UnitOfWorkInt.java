package com.allianceseeds.api.services;

public interface UnitOfWorkInt<T> {


    static class UnitActions {
        public static String INSERT = "INSERT";
        public static String DELETE = "DELETE";
        public static String UPDATE = "UPDATE";
    }


}

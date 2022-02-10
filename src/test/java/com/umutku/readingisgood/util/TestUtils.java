package com.umutku.readingisgood.util;

public class TestUtils {
    public static String createURLWithPort(String uri, int port) {
        return "http://localhost:" + port + uri;
    }
}

package com.itbird.design.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by itbird on 2022/3/30
 */
public class CloseUtils {
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}

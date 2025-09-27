package site.remlit.blueb.apfuckery.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StringStore {

    private static final Logger logger = LoggerFactory.getLogger(StringStore.class);

    private static final String ROOT = "data";

    /* initializer */
    static {
        Path path = Paths.get(ROOT).toAbsolutePath();

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the text stored in a text file at path.
     *
     * @param key Storage key
     *
     * @return String or null
     * */
    public static @Nullable String read(
            @NotNull String key
    ) {
        Path path = Paths.get(ROOT, key + ".txt").toAbsolutePath();
        File file = path.toFile();

        if (!file.exists() || !file.canRead())
            return null;

        try {
            return Files.readString(path);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Writes a string to a text file.
     *
     * @param key Storage key
     * @param value String value
     * */
    public static void write(
            @NotNull String key,
            @NotNull String value
    ) {
        Path path = Paths.get(ROOT, key + ".txt").toAbsolutePath();
        File file = path.toFile();

        try {
            if (!file.exists())
                Files.createFile(path);

            if (!file.canWrite())
                return;

            Files.writeString(path, value);
        } catch (IOException e) {
            logger.error("write failed: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Appends text to end of stored string.
     *
     * @param key Storage key
     * @param value String value
     * @param delimiter String to put between old and new values
     * */
    public static void append(
            @NotNull String key,
            @NotNull String value,
            @Nullable String delimiter
    ) {
        String previous = StringStore.read(key);
        StringStore.write(
                key,
                (previous != null ? previous +
                        (delimiter != null ? delimiter : "")
                        : "") + value
        );
    }

    /**
     * Determines if a key has value.
     *
     * @param key Storage key
     *
     * @return If key has value
     * */
    public static boolean exists(
            @NotNull String key
    ) {
        return StringStore.read(key) != null;
    }

}

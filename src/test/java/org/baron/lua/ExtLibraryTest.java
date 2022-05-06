package org.baron.lua;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * </p>
 *
 * @author : Baron.Fu
 * @since Created in 23:21 2022/4/22
 */
public class ExtLibraryTest {
    final ExtLibrary extLib = ExtLibrary.INSTANCE;


    @Test
    void test_char_cnt() {
        // and_rust: fn(*mut c_char) -> *mut c_char
        int ret = extLib.char_cnt("Rust FFI"); // hello from java and rust
        assertEquals(8, ret);
    }

    @Test
    void test_concat() {
        // and_rust: fn(*mut c_char) -> *mut c_char
        String ret = extLib.concat("Rust ", "FFI"); // hello from java and rust
        assertEquals("Rust FFI", ret);

        // free memory
        extLib.free_str(ret);
    }

    @Test
    void test_eval() {
        assertEquals(
                "ok",
                extLib.eval("print(\"Hello World!\") return \"ok\"")
        );

        assertEquals(
                "3",
                extLib.eval("local a = 1 + 2 return a")
        );
    }

    @Test
    void test_deliver() {
        Consumer<String> tester = str -> {
            byte[] bytes1 = str.getBytes(StandardCharsets.UTF_8);
            Pointer data1 = new Memory(bytes1.length);
            data1.write(0, bytes1, 0, bytes1.length);
            assertEquals(str, extLib.deliver(data1, bytes1.length));
        };

        tester.accept("Hello World");
        tester.accept("Hello Rust");
        tester.accept("Hello FFI");
    }

}

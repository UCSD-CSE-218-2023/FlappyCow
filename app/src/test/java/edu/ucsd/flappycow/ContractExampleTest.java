/*
 DO NOT UNCOMMENT (YET). There is an issue with the contract library and Robolectric.
 */

//package edu.ucsd.flappycow;
//
//import com.google.java.contract.Ensures;
//import com.google.java.contract.Invariant;
//import com.google.java.contract.Requires;
//import com.google.java.contract.PreconditionError;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.RobolectricTestRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import edu.ucsd.flappycow.sprites.Sprite;
//
///**
// * This test class illustrates some ways to write tests which make use of contracts.
// *
// * There is assertNotThrows. Test will fail if an (uncaught) exception is thrown.
// */
//@RunWith(RobolectricTestRunner.class)
//public class ContractExampleTest {
//    @Test
//    public void testPreconditions() {
//        Assert.assertThrows(PreconditionError.class, () -> {
//            // Given...
//            Dictionary<Integer> dict = new Dictionary<>(1);
//
//            dict.put("foo", 1);
//
//            // When...
//            dict.put("bar", 2);
//
//            // Then... PreconditionError, not enough capacity!
//        });
//    }
//
//    /*
//     * Adapted from: https://github.com/wggster/DbC/blob/master/src/main/java/edu/cs/ucsd/dbc/Dictionary.java
//     * ...which is adapted from: https://www.leadingagile.com/2018/05/design-by-contract-part-three/
//     */
//
//    @Invariant({
//            "capacity() >= count()",
//            "count() >= 0"
//    })
//    static
//    class Dictionary<TValue> {
//        private int _capacity;
//        private Map<String, TValue> _contents;
//
//        public int capacity() {
//            return _capacity;
//        }
//
//        public int count() {
//            return _contents.size();
//        }
//
//        @Requires("capacity > 0")
//        @Ensures({
//                "capacity() > 0",
//                "count() == 0"
//        })
//        public Dictionary(int capacity) {
//            this._capacity = capacity;
//            this._contents = new HashMap<>(capacity);
//        }
//
//        @Requires({
//                "key != null",
//                "key.length() > 0"
//        })
//        public TValue get(String key) {
//            return _contents.get(key);
//        }
//
//        @Requires({
//                "key != null",
//                "key.length() > 0",
//                "count() < capacity()"
//        })
//        @Ensures({
//                "get(key) == value",
//                "count() == old(count()) + 1"
//        })
//        public void put(String key, TValue value) {
//            _contents.put(key, value);
//        }
//    }
//}

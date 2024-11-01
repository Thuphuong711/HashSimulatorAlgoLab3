/**
 * Name: Le Thi Thu Phuong
 * StudentID: A01373420
 */

import java.util.function.BiFunction;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class HashSimulator {

    /**
     *This method simulates inserting items into a hash table given a list of N input strings and a size
     * to be used for the hash table.
     *
     * @param text - an array of strings, the key values to be hashed
     * @param size - an int, the size of the hash table to be used
     *
     * @return an array of 6 ints, the following results of the hash simulations:
     * results[0] is the # of collisions that occur when hashing with H1()
     * results[1] is the # of probes that occur when hashing with H1()
     * results[2] is the # of collisions that occur when hashing with H2()
     * results[3] is the # of probes that occur when hashing with H2()
     * results[4] is the # of collisions that occur when hashing with H3()
     * results[5] is the # of probes that occur when hashing with H3()
     */
    public int[] runHashSimulator (String[] text, int size){
        // int array to store the results of the hash simulators
        int[] array = new int[6];

        // three arrays to store the collisions and probes of three hash functions
        int[] resultH1 = runHashFunction(size,text,this::H1);
        int[] resultH2 = runHashFunction(size,text,this::H2);
        int[] resultH3 = runHashFunction(size,text,this::H3);

        // putting the result of each hash functions into the array
        array[0] = resultH1[0];
        array[1] = resultH1[1];
        array[2] = resultH2[0];
        array[3] = resultH2[1];
        array[4] = resultH3[0];
        array[5] = resultH3[1];

        for(int i = 0; i< array.length; i++){
            System.out.print(array[i] + " ");
        }
        return array;
    }

    /**
     * The hash function H1 is the sum of
     * these values for the letters in the string, mod HTsize.
     *
     * @param name - a name string which needs to be hashed
     * @param HTsize - an int which is the size of hash table
     * @return the hash code
     */
    public int H1 (String name, int HTsize){
        int hashValue = 0;
        int length = name.length();
        for(int i = 0; i < length; i++){
            char ch = name.charAt(i);
            int index = ch - 'A' + 1;
            hashValue += index;
        }
        return hashValue % HTsize;
    }

    /**
     * For the ith letter in the string (counting from 0), multiply the character
     * value (A=1, B=2, C=3) times 26^i. Add up these values, and take the result mod HTsize.
     *
     * @param name - a name string which needs to be hashed
     * @param HTsize - an int which is the size of hash table
     * @return the hash code
     */
    public int H2 (String name, int HTsize){
        long hashValue = 0;
        long index = 0;
        int length = name.length();
        int twentySix = 26;
        for(int i = 0; i < length; i++){
            char ch = name.charAt(i);
            index = ch - 'A' + 1;
            hashValue +=  index * (long)pow(twentySix,i);
        }
        return (int) (hashValue % HTsize);
    }

    /**
     * I got this built in function from
     * https://www.w3schools.com/java/ref_string_hashcode.asp#:~:text=Definition%20and%20Usage,hash%20code%20of%20a%20string.&text=where%20s%5Bi%5D%20is%20the,string%2C%20and%20%5E%20indicates%20exponentiation.
     * this H3 function uses hashCode() built-in function in Java
     * The hash code for a String object is computed like this:
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     * @param name - a name string which needs to be hashed
     * @param HTsize - an int which is the size of hash table
     * @return the hash code
     */
    public int H3 (String name, int HTsize){
        long hashValue = 0;
        hashValue += name.hashCode();
        // the hashCode() built-in function can return a number <0,
        //so I have to use abs function to make this number > 0
        return abs((int)(hashValue % HTsize));
    }

    public int[] runHashFunction(int size, String[] text, BiFunction<String, Integer, Integer> hashFunction){
        int[] result = new int[2];
        int probeH1 = 0;
        int collisionH1 = 0;
        String[] hashTableH1 = new String[size];

        for (String name : text) {
            int hashCode = hashFunction.apply(name, size);
            if (hashTableH1[hashCode] == null) {
                hashTableH1[hashCode] = name;
            } else {
                // collision and probe occur
                probeH1++;
                collisionH1++;
                int j = hashCode + 1;
                while (j < size && hashTableH1[j] != null) {
                    probeH1++;
                    j++;
                }
                if (j < size && hashTableH1[j] == null) {
                    hashTableH1[j] = name;
                }
                if (j == size) { // wrap around
                    j = 0;
                    while (hashTableH1[j] != null) {
                        probeH1++;
                        j++;
                    }
                    hashTableH1[j] = name;
                }
            }
        }
        result[0] = collisionH1;
        result[1] = probeH1;
        return result;
    }
}

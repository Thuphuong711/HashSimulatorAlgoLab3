/**
 * Name: Le Thi Thu Phuong
 * StudentID: A01373420
 */

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
        int[] resultH1 = runH1(size,text);
        int[] resultH2 = runH2(size,text);
        int[] resultH3 = runH3(size,text);

        // putting the result of each hash functions into the array
        array[0] = resultH1[0];
        array[1] = resultH1[1];
        array[2] = resultH2[0];
        array[3] = resultH2[1];
        array[4] = resultH3[0];
        array[5] = resultH3[1];

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


    /**
     * function runH1 to calculate the collisions and probes when
     * the String array of name is hashed by H1 function
     * @param size - an int, the size of the hash table to be used
     * @param text - an array of strings, the key values to be hashed
     * @return array of collision and probes
     */
    public int[] runH1(int size, String[] text){
        int[] H1Result = new int[2];
        int probeH1 = 0;
        int collisionH1 = 0;
        String[] hashTableH1 = new String[size];

        for (String name : text) {
            int hashCode = H1(name, size);
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
                    System.out.println("enter j == size ");
                    j = 0;
                    while (hashTableH1[j] != null) {
                        probeH1++;
                        j++;
                    }
                    hashTableH1[j] = name;
                }
            }
        }
        H1Result[0] = collisionH1;
        H1Result[1] = probeH1;
        return H1Result;
    }


    /**
     * function runH2 to calculate the collisions and probes when
     * the String array of name is hashed by H2 function
     * @param size - an int, the size of the hash table to be used
     * @param text - an array of strings, the key values to be hashed
     * @return array of collision and probes
     */
    public int[] runH2(int size, String[] text){
        int[] H2Result = new int[2];
        int probeH2 = 0;
        int collisionH2 = 0;
        String[] hashTableH2 = new String[size];

        for (String name : text) {
            int hashCode = H2(name, size);
            if (hashTableH2[hashCode] == null) {
                hashTableH2[hashCode] = name;
            } else {
                probeH2++;
                collisionH2++;
                int j = hashCode + 1;
                while (j < size && hashTableH2[j] != null) {
                    probeH2++;
                    j++;
                }
                if (j < size && hashTableH2[j] == null) {
                    hashTableH2[j] = name;
                }
                if (j == size) { // wrap around
                    j = 0;
                    while (hashTableH2[j] != null) {
                        probeH2++;
                        j++;
                    }
                    hashTableH2[j] = name;

                }
            }
        }
        H2Result[0] = collisionH2;
        H2Result[1] = probeH2;
        return H2Result;
    }

    /**
     * function runH3 to calculate the collisions and probes when
     * the String array of name is hashed by H3 function
     * @param size - an int, the size of the hash table to be used
     * @param text - an array of strings, the key values to be hashed
     * @return array of collision and probes
     */
    public int[] runH3(int size, String[] text){
        int[] H3Result = new int[2];
        int probeH3 = 0;
        int collisionH3 = 0;
        String[] hashTableH1 = new String[size];
        for (String name : text) {
            int hashCode = H3(name, size);
            if (hashTableH1[hashCode] == null) {
                hashTableH1[hashCode] = name;
            } else {
                probeH3++;
                collisionH3++;
                int j = hashCode + 1;
                while (j < size && hashTableH1[j] != null) {
                    probeH3++;
                    j++;
                }
                if (j < size && hashTableH1[j] == null) {
                    hashTableH1[j] = name;
                }
                if (j == size) { // wrap around
                    j = 0;
                    while (hashTableH1[j] != null) {
                        probeH3++;
                        j++;
                    }
                    hashTableH1[j] = name;
                }
            }
        }
        H3Result[0] = collisionH3;
        H3Result[1] = probeH3;
        return H3Result;
    }

}

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by silver on 13/03/2017.
 */
public class HashCollider {


    public String bruteForce(String salt, int prefixedZeros){
        String result;
        String[] output = new String[10];

        int counter=1;
        int keysFound=0;
        try {
            MessageDigest m= MessageDigest.getInstance("MD5");

            while(keysFound<10){
                String temp = salt+counter;
                //digesting to hexadecimal md5
                m.update(temp.getBytes(),0,temp.length());
                String MD5Result = DatatypeConverter.printHexBinary(m.digest());

                //check if result has the number of 0s
                if(isValidHash(MD5Result ,prefixedZeros)){
                    System.out.println("Hash is Valid: " + MD5Result);
                    //get the index of the char for the pass
                    String indexChar = getPasswordCharacter(MD5Result, prefixedZeros);

                    // check if index is a valid integer
                    try{
                        int index = Integer.parseInt(indexChar);
                        System.out.println("Processing Index: " + index);
                        //check if this key already used
                        if(isIndexValid(index , output)){
                            System.out.println("unused index ... Saving to position");
                            //getting character to put at index
                            output[index] = getPasswordCharacter(MD5Result, counter);
                            keysFound++;
                        }else{
                            //this slot already used up
                            System.out.println("Rejecting index: " + index + "...Position already filled");
                        }
                    }
                    catch (NumberFormatException e){
                        System.out.println("Rejecting index: " + indexChar + " ...Not an integer");
                    }
                }
                counter++;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        result = formatPassword(output);
        return result;
    }

    private String getPasswordCharacter(String MD5Result, int iteration) {
        int passwordCharacterIndex = iteration%32;
        return String.valueOf(MD5Result.charAt(passwordCharacterIndex));
    }

    private String formatPassword(String[] output) {
        //extracting all unwanted characters
        String result;
        result = Arrays.toString(output).replaceAll("[ ,\\]\\[]","").toLowerCase();
        return result;
    }

    private boolean isIndexValid(int index, String[] output) {
        //could be simplified into an inline statement but left as such for clarity
        if(output[index]==null){
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidHash(String md5Result, int prefixedZeros) {
        //checks if the hash has the required number of prefixed zeros
        boolean result;
        //Todo improve the regex to skip use of substring if time permits
        String fragment = md5Result.substring(0,prefixedZeros);
        result = fragment.matches("^[0]+$");
        return result;
    }
}

import java.security.MessageDigest

/**
 * Created by tomwallace on 12/7/15.
 */
class DayFourService {

    final static String PUZZLE_INPUT = "bgvyzdsv"

    // http://adventofcode.com/day/4

    /*
Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically forward-thinking little girls and boys.

To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
     */
    Integer getAdventCoinNumberFiveZeros() {
        Integer result = 1
        for(;;){ // infinite for
            String preHash = PUZZLE_INPUT + result.toString()
            String hash = generateMD5(preHash)

            if( hash.substring(0,5) == '00000' ){ //condition to break, oppossite to while
                break
            }

            ++result
        }

        return result
    }

    Integer getAdventCoinNumberSixZeros () {
        Integer result = 1
        for(;;){ // infinite for
            String preHash = PUZZLE_INPUT + result.toString()
            String hash = generateMD5(preHash)

            if( hash.substring(0,6) == '000000' ){ //condition to break, oppossite to while
                break
            }

            ++result
        }

        return result
    }

    // Taken from - https://gist.github.com/ikarius/299062
    private def generateMD5(String s) {
        MessageDigest digest = MessageDigest.getInstance("MD5")
        digest.update(s.bytes);
        new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
    }
}

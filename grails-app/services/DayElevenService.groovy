import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by tomwallace on 12/7/15.
 */
class DayElevenService {

    final static String PUZZLE_INPUT = "cqjxjnds"

    // http://adventofcode.com/day/11

    /*
Santa's previous password expired, and he needs help choosing a new one.

To help him remember his new password after the old one expires, Santa has devised a method of coming up with a password based on the previous one. Corporate policy dictates that passwords must be exactly eight lowercase letters (for security reasons), so he finds his new password by incrementing his old password string repeatedly until it is valid.

Incrementing is just like counting with numbers: xx, xy, xz, ya, yb, and so on. Increase the rightmost letter one step; if it was z, it wraps around to a, and repeat with the next letter to the left until one doesn't wrap around.

Unfortunately for Santa, a new Security-Elf recently started, and he has imposed some additional password requirements:

Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.

     */
    String createNewPassword(String input) {
        String newPassword = input
        for(;;){ // infinite for
            String iterated = revPassword(newPassword)
            newPassword = iterated
            if (isValidPassword(newPassword)) {
                break
            }
        }

        return newPassword
    }

    protected String revPassword(String input) {
        String newEnd = ''
        def i = 0
        Integer length = input.size() - 1
        // Use recursion to trim off the string a little at a time
        for (i = 0; i <= length; i++) {
            String lastChar = input.substring(input.length() - 1, input.length())
            input = input.substring(0, input.length() - 1)
            String revedChar = revChar(lastChar)
            newEnd = "$revedChar$newEnd"
            if (revedChar != 'a') {
                break
            }
        }
        return input + newEnd
    }

    private String revChar(String c) {
        int ascii = (int) c
        ++ascii
        // If greater than 122 ('z') revert to 97 ('a')
        if (ascii > 122) {
            ascii = 97
        }
        return Character.toString ((char) ascii)
    }

    protected boolean isValidPassword(String input) {
        return containsLetterStraight(input) && !hasBlacklistedCharacter(input) && containsDuplicatePairs(input)
    }

    protected boolean containsLetterStraight(String input) {
        return input.find(/(?:abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz)/) != null
    }

    protected boolean hasBlacklistedCharacter(String input) {
        return input.find(/[iol]/) != null
    }

    protected boolean containsDuplicatePairs(String input) {
        return input.findAll(/(\w)\1+/).size() > 1
    }
}

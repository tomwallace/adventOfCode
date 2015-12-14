import grails.test.mixin.TestFor
import spock.lang.Specification

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayFiveService)
class DayFiveServiceSpecification extends Specification {


    def "test isStringNicePartOne"() {
        when:
        def result = service.isStringNicePartOne(input)

        then:
        expected == result

        where:
        expected | input
        true   | 'ugknbfddgicrmopn'
        true   | 'aaa'
        false  | 'jchzalrnumimnmhp'
        false  | 'haegwjzuvuyypxyu'
        false  | 'dvszwmarrgswjxmb'
    }

    def "test isStringNicePartTwo"() {
        when:
        def result = service.isStringNicePartTwo(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        true   | 'qjhvhtzxzqqjkmpb'
        true   | 'xxyxx'
        true   | 'yzsmlbnftftgwadz'
        false  | 'aaa'
        false  | 'uurcxstgmygtbstg'
        false  | 'ieodomkazucvgmuy'
        false  | 'bjhvhtzxzqqqjkmpb'
        true | "xilodxfuxphuiiii"
        true | "dwkasufidwjrjfbf"
        true | "vebzobmdbzvjnjtk"
        true | "pyxpyvvipbqibiox"
        true | "ygduolbysehdudra"
        true | "weeguqeheeiigrue"
        true | "jiyahihykjjkdaya"
        true | "qmqaqsajmqwhetpk"
        true | "ywvwhrwhkaubvkbl"
        true | "nrmsononytuwslsa"
        true | "mxopokqffisxosci"
        true | "khtkhcvelidjdena"
        true | "sesylkpvbndrdhsy"
        true | "wknkurddcknbdleg"
        true | "aacuiiwgaannunmm"
        true | "kpavhqkukugocsxu"
        true | "agnggugngadrcxoc"
        true | "uhttadmdmhidpyjw"
        true | "pzkkkkwrlvxiuysn"
        true | "tdfvkreormspprer"
        true | "olylnjtkxgrubmtk"
        true | "wbabpirnpcsmpipw"
        true | "vxsluutrwskslnye"
        true | "fdmzppzphhpzyuiz"
        true | "oehmvziiqwkzhzib"
        true | "wnpxvmxvllxalulm"
        true | "bkkkkcwegvypbrio"
        true | "hauwpjjwowbunbjj"
        true | "awkftfagrfzywkhs"
        true | "uedtpzxyubeveuek"
        true | "kvshzltcrrururty"
        true | "dcjmxyzbzpohzprl"
        true | "qhgzujhgdruowoug"
        true | "mdngnobasfpewlno"
        true | "dwmxqudvxqdenrur"
        true | "hpmbxtpfosbsjixt"
        true | "sysxssmvewyfjrve"
        true | "eiquffofoadmbuhk"
        true | "vjhrkiwmunuiwqau"
        true | "ndxmskrwrqysrndf"
        true | "ypxoyjelhllhbeog"
        true | "liliptyoqujensfi"
        true | "gxjzahtgbgbabtht"
        true | "qkcdtkwuaquajazz"
        true | "pqedgfojyjybfbzr"
        true | "lotxrswlxbxlxufs"
        true | "apsbnbkiopopytgu"
        true | "uvbhdtvaypasaswa"
        true | "jqwcwuuisrclircv"
        true | "banrornfkcymmkcc"
        true | "xlqwdrytzwnxzwzv"
        true | "ohgqnqhfimyuqhvi"
        true | "bauieohjejamzien"
        true | "tstwsswswrxlzrqs"
        true | "yzsmlbnftftgwadz"
    }

    def "test containsDoubleLetter"() {
        when:
        def result = service.containsDoubleLetter(input)

        then:
        expected == result

        where:
        expected | input
        true   | 'xyx'
        true   | 'abcdefeghi'
        true   | 'efe'
        true   | 'aaa'
        false  | 'abcdefg'
        false  | 'abcadefdg'
        false  | 'a'
        false  | 'ab'
    }

    def "test containsDuplicatePairs"() {
        when:
        def result = service.containsDuplicatePairs(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        true   | 'xyxy'
        true   | 'aabcdefgaa'
        true   | 'qjhvhtzxzqqjkmpb'
        true   | 'qaaadfbeaa'
        true   | 'xilodxfuxphuiiii'
        false   | 'aaa'
        false   | 'qaaadfbea'
    }

    def "test with actual part a"() {
        when:
        def result = service.countNiceStringsPartOne(service.PUZZLE_INPUT)

        then:
        result
        result == 255
    }

    def "test with actual part b"() {
        when:
        def result = service.countNiceStringsPartTwo(service.PUZZLE_INPUT)

        then:
        result
        result == 55
    }


}

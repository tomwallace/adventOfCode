import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by tomwallace on 12/7/15.
 */
class DayNineteenService {

    final static String PUZZLE_INPUT = "ORnPBPMgArCaCaCaSiThCaCaSiThCaCaPBSiRnFArRnFArCaCaSiThCaCaSiThCaCaCaCaCaCaSiRnFYFArSiRnMgArCaSiRnPTiTiBFYPBFArSiRnCaSiRnTiRnFArSiAlArPTiBPTiRnCaSiAlArCaPTiTiBPMgYFArPTiRnFArSiRnCaCaFArRnCaFArCaSiRnSiRnMgArFYCaSiRnMgArCaCaSiThPRnFArPBCaSiRnMgArCaCaSiThCaSiRnTiMgArFArSiThSiThCaCaSiRnMgArCaCaSiRnFArTiBPTiRnCaSiAlArCaPTiRnFArPBPBCaCaSiThCaPBSiThPRnFArSiThCaSiThCaSiThCaPTiBSiRnFYFArCaCaPRnFArPBCaCaPBSiRnTiRnFArCaPRnFArSiRnCaCaCaSiThCaRnCaFArYCaSiRnFArBCaCaCaSiThFArPBFArCaSiRnFArRnCaCaCaFArSiRnFArTiRnPMgArF"

    List<Integer> STEPS_MAKE_MOLECULE

    final static String PUZZLE_RULES = """Al => ThF
Al => ThRnFAr
B => BCa
B => TiB
B => TiRnFAr
Ca => CaCa
Ca => PB
Ca => PRnFAr
Ca => SiRnFYFAr
Ca => SiRnMgAr
Ca => SiTh
F => CaF
F => PMg
F => SiAl
H => CRnAlAr
H => CRnFYFYFAr
H => CRnFYMgAr
H => CRnMgYFAr
H => HCa
H => NRnFYFAr
H => NRnMgAr
H => NTh
H => OB
H => ORnFAr
Mg => BF
Mg => TiMg
N => CRnFAr
N => HSi
O => CRnFYFAr
O => CRnMgAr
O => HP
O => NRnFAr
O => OTi
P => CaP
P => PTi
P => SiRnFAr
Si => CaSi
Th => ThCa
Ti => BP
Ti => TiTi
e => HF
e => NAl
e => OMg"""

    // http://adventofcode.com/day/19

    /*
Rudolph the Red-Nosed Reindeer is sick! His nose isn't shining very brightly, and he needs medicine.

Red-Nosed Reindeer biology isn't similar to regular reindeer biology; Rudolph is going to need custom-made medicine. Unfortunately, Red-Nosed Reindeer chemistry isn't similar to regular reindeer chemistry, either.

The North Pole is equipped with a Red-Nosed Reindeer nuclear fusion/fission plant, capable of constructing any Red-Nosed Reindeer molecule you need. It works by starting with some input molecule and then doing a series of replacements, one per step, until it has the right molecule.

However, the machine has to be calibrated before it can be used. Calibration involves determining the number of molecules that can be generated in one step from a given starting point.

For example, imagine a simpler machine that supports only the following replacements:

H => HO
H => OH
O => HH
Given the replacements above and starting with HOH, the following molecules could be generated:

HOOH (via H => HO on the first H).
HOHO (via H => HO on the second H).
OHOH (via H => OH on the first H).
HOOH (via H => OH on the second H).
HHHH (via O => HH).
So, in the example above, there are 4 distinct molecules (not five, because HOOH appears twice) after one replacement from HOH. Santa's favorite molecule, HOHOHO, can become 7 distinct molecules (over nine replacements: six from H, and three from O).

The machine replaces without regard for the surrounding characters. For example, given the string H2O, the transition H => OO would result in OO2O.

Your puzzle input describes all of the possible replacements and, at the bottom, the medicine molecule for which you need to calibrate the machine. How many distinct molecules can be created after all the different ways you can do one replacement on the medicine molecule?

     */
    Integer countDistinctMolecules(String input, String rules) {
        List<String> molecules = []
        List<List> data = getReplacementRules(rules)

        data.each { d ->
            List<Integer> indexsOfMatch = []
            int index = input.indexOf(d[0]);
            while (index >= 0) {
                indexsOfMatch << index
                index = input.indexOf(d[0], index + 1)
            }
            indexsOfMatch.each { idx ->
                String newChar = d[1]
                String firstPart = input.substring(0, idx)
                String lastPart = input.substring(idx + d[0].size(), input.length())
                String molecule = "${firstPart}${newChar}${lastPart}"
                if (molecule != input) {
                    molecules << molecule
                }
            }
        }

        return molecules.unique().size()
    }

    Integer countMinFabricateMolecule(String desiredMolecule, String rulesString) {
        STEPS_MAKE_MOLECULE = []
        List<List> rules = getReplacementRules(rulesString)

        String start = 'e'

        makeMoleculeRecursive(desiredMolecule, start, rules, 0)

        return STEPS_MAKE_MOLECULE.sort()[0]
    }

    protected void makeMoleculeRecursive(String desiredMolecule, String current, List<List> rules, Integer counter) {
        if (current.size() > desiredMolecule.size()) {
            return
        }
        if (current == desiredMolecule) {
            println("Found one >> ${current}")
            STEPS_MAKE_MOLECULE << counter
            return
        }
        rules.each { d ->
            List<Integer> indexsOfMatch = []
            int index = current.indexOf(d[0]);
            while (index >= 0) {
                indexsOfMatch << index
                index = current.indexOf(d[0], index + 1)
            }
            indexsOfMatch.each { idx ->
                String newChar = d[1]
                String firstPart = current.substring(0, idx)
                String lastPart = current.substring(idx + d[0].size(), current.length())
                String molecule = "${firstPart}${newChar}${lastPart}"
                makeMoleculeRecursive(desiredMolecule, molecule, rules, counter + 1)
            }
        }
    }

    protected List<List> getReplacementRules (String input) {
        List<List> result = []
        input.tokenize("\n").each { String line ->
            List<String> lineResults = line.findAll(/(\w+)/)
            result << lineResults
        }
        return result
    }

}

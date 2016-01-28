import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by tomwallace on 12/7/15.
 */
class DayNineteenService {

    final static String PUZZLE_INPUT = "ORnPBPMgArCaCaCaSiThCaCaSiThCaCaPBSiRnFArRnFArCaCaSiThCaCaSiThCaCaCaCaCaCaSiRnFYFArSiRnMgArCaSiRnPTiTiBFYPBFArSiRnCaSiRnTiRnFArSiAlArPTiBPTiRnCaSiAlArCaPTiTiBPMgYFArPTiRnFArSiRnCaCaFArRnCaFArCaSiRnSiRnMgArFYCaSiRnMgArCaCaSiThPRnFArPBCaSiRnMgArCaCaSiThCaSiRnTiMgArFArSiThSiThCaCaSiRnMgArCaCaSiRnFArTiBPTiRnCaSiAlArCaPTiRnFArPBPBCaCaSiThCaPBSiThPRnFArSiThCaSiThCaSiThCaPTiBSiRnFYFArCaCaPRnFArPBCaCaPBSiRnTiRnFArCaPRnFArSiRnCaCaCaSiThCaRnCaFArYCaSiRnFArBCaCaCaSiThFArPBFArCaSiRnFArRnCaCaCaFArSiRnFArTiRnPMgArF"


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
e => OMg
"""

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

    // Was completely stumped on this part.  I kept getting the response for  - NRnBSiRnCaRnFArYFArFArF
    // which went no further.
    // Got help from balda132's response on https://www.reddit.com/r/adventofcode/comments/3xflz8/day_19_solutions/
    Integer solvePartTwo(String originalMolecule, String rulesString) {
        List<List> rules = getReplacementRules(rulesString)
        Map reverseReplacements = [:]
        rules.each { rule ->
            reverseReplacements["${rule[1]}"] = rule[0]
        }
        int result = 0;
        while ((result = findMolecule(0, originalMolecule, reverseReplacements)) == -1)
        ;
        System.out.println("Part Two: " + result);
        return result
    }

    private int findMolecule(int depth,
                                    String molecule,
                                    Map reverseReplacements) {
        if (molecule.equals("e")) {
            return depth;
        } else {
            List<String> keys = new ArrayList<>(reverseReplacements.keySet());
            boolean replaced = false;
            while (!replaced) {
                String toReplace = keys.remove(new Random().nextInt(keys.size()));
                Matcher matcher = Pattern.compile(toReplace).matcher(molecule);
                if (matcher.find()) {
                    molecule = replaceTwo(molecule, reverseReplacements.get(toReplace), matcher);
                    replaced = true;
                }
                if (keys.isEmpty()) {
                    return -1;
                }
            }
            return findMolecule(depth + 1, molecule, reverseReplacements);
        }
    }

    private String replaceTwo(String original, String replacement, Matcher matcher) {
        int begin = matcher.start(0);
        int end = matcher.end(0);
        StringBuilder newMolecule = new StringBuilder();
        newMolecule.append(original.substring(0, begin));
        newMolecule.append(replacement);
        newMolecule.append(original.substring(end));
        return newMolecule.toString();
    }



    protected List<List> getReplacementRules (String input) {
        List<List> result = []
        input.tokenize("\n").each { String line ->
            List<String> lineResults = line.findAll(/(\w+)/)
            result << lineResults
        }
        return result.sort { -it[1].size() }
    }

}

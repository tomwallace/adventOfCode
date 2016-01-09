/**
 * Created by tomwallace on 12/7/15.
 */
class DayTwentyThreeService {

    final static String PUZZLE_INPUT = """jio a, +19
inc a
tpl a
inc a
tpl a
inc a
tpl a
tpl a
inc a
inc a
tpl a
tpl a
inc a
inc a
tpl a
inc a
inc a
tpl a
jmp +23
tpl a
tpl a
inc a
inc a
tpl a
inc a
inc a
tpl a
inc a
tpl a
inc a
tpl a
inc a
tpl a
inc a
inc a
tpl a
inc a
inc a
tpl a
tpl a
inc a
jio a, +8
inc b
jie a, +4
tpl a
inc a
jmp +2
hlf a
jmp -7"""

    // http://adventofcode.com/day/23

    /*
Little Jane Marie just got her very first computer for Christmas from some unknown benefactor. It comes with instructions and an example program, but the computer itself seems to be malfunctioning. She's curious what the program does, and would like you to help her run it.

The manual explains that the computer supports two registers and six instructions (truly, it goes on to remind the reader, a state-of-the-art technology). The registers are named a and b, can hold any non-negative integer, and begin with a value of 0. The instructions are as follows:

hlf r sets register r to half its current value, then continues with the next instruction.
tpl r sets register r to triple its current value, then continues with the next instruction.
inc r increments register r, adding 1 to it, then continues with the next instruction.
jmp offset is a jump; it continues with the instruction offset away relative to itself.
jie r, offset is like jmp, but only jumps if register r is even ("jump if even").
jio r, offset is like jmp, but only jumps if register r is 1 ("jump if one", not odd).
All three jump instructions work with an offset relative to that instruction. The offset is always written with a prefix + or - to indicate the direction of the jump (forward or backward, respectively). For example, jmp +1 would simply continue with the next instruction, while jmp +0 would continuously jump back to itself forever.

The program exits when it tries to run an instruction beyond the ones defined.

For example, this program sets a to 2, because the jio instruction causes it to skip the tpl instruction:

inc a
jio a, +2
tpl a
inc a
What is the value in register b when the program in your puzzle input is finished executing?
     */

    Map openTuringLock(String input, Map regs) {
        List<String> instructs = input.tokenize("\n")

        def i = 0
        for (i = 0; i < instructs.size(); i++) {
            String line = instructs[i]
            List<String> parts = line.tokenize(' ')

            if (parts[0] == 'inc') {
                ++regs["${parts[1]}"]
            }

            if (parts[0] == 'hlf') {
                regs["${parts[1]}"] = (regs["${parts[1]}"]/2).toInteger()
            }

            if (parts[0] == 'tpl') {
                regs["${parts[1]}"] = regs["${parts[1]}"] * 3
            }

            if (parts[0] == 'jmp') {
                i += (parts[1].toInteger() - 1)
            }

            if (parts[0] == 'jie') {
                String letter =  parts[1].substring(0, 1)
                if ((regs["${letter}"] % 2) == 0) {
                    i += (parts[2].toInteger() - 1)
                }
            }

            if (parts[0] == 'jio') {
                String letter =  parts[1].substring(0, 1)
                if (regs["${letter}"]  == 1) {
                    i += (parts[2].toInteger() - 1)
                }
            }

        }

        return regs
    }

}

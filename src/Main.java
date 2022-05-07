import java.util.*;

public class Main {
    private static Map<String, Register> registerHashMap = new HashMap<>(134);
    private static List<Sentence> sentenceList = new ArrayList<>(128);
    private static Counter counter = Counter.getCounter();
    private static final String MOV_REGISTER_INTEGER_REGEX = "mov [a-z]{1,10} ((-(10000|([0-9]){0,4}))|0|(10000|([0-9]{0,4})))";
    private static final String MOV_REGISTER_REGISTER_REGEX = "(mov) ([a-z]{1,10}) ([a-z]{1,10})";
    private static final String JNZ_REGISTER_INTEGER_REGEX = "(jnz) ([a-z]{1,10}) (-[1-9][0-9]{0,1})";
    private static final String INC_REGISTER_REGEX = "(inc) ([a-z]{1,10})";
    private static final String DEC_REGISTER_REGEX = "(dec) ([a-z]{1,10})";

    //  双击回车结束输入
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        while (true) {
            if (counter.getInstructionCount() == 100) break;
            String s = in.nextLine();
            if ("".equals(s)) break;
            else {
                Sentence sentence = makeSentence(s);
                if (sentence == null) {
                    System.out.println("input error");
                    return;
                }
                sentenceList.add(sentence);
                counter.incInstructionCount();
            }
        }
        int index = 0;
        while (true) {
            if (counter.getRegisterCount() == 100 || counter.getRunCount() == 1000000000) break;
            if (index == sentenceList.size()) {
                registerHashMap.forEach(
                        (k, v) -> System.out.println(v.getName() + " " + v.getValue())
                );
                break;
            }
            Sentence sentence = sentenceList.get(index);
            int i = handleSentence(sentence);
            index += i;
        }


    }

    private static int handleSentence(Sentence sentence) throws Exception {
        Instruction instruction = sentence.getInstruction();
        String currentRegister = sentence.getCurrentRegister();
        String operatedRegister = sentence.getOperatedRegister();
        Integer operatedInteger = sentence.getOperatedInteger();
        if (instruction.equals(Instruction.mov) && operatedInteger != null) {
            Register register = new Register(currentRegister, operatedInteger);
            registerHashMap.put(register.getName(), register);
            counter.incRegisterCount();
            return 1;
        }

        if (instruction.equals(Instruction.mov) && operatedRegister != null) {
            if (registerHashMap.containsKey(sentence.getOperatedRegister())) {
                if (!registerHashMap.containsKey(currentRegister)) {
                    Register register = new Register(
                            sentence.getCurrentRegister(),
                            registerHashMap.get(sentence.getOperatedRegister()).getValue()
                    );
                    registerHashMap.put(register.getName(), register);
                    counter.incRegisterCount();
                } else {
                    registerHashMap.get(currentRegister).setValue(registerHashMap.get(operatedRegister).getValue());
                }
                return 1;
            }
            throw new Exception("operateRegister is null " + sentence.toString());
        }

        if (instruction.equals(Instruction.inc)) {
            if (registerHashMap.containsKey(currentRegister)) {
                Register register = registerHashMap.get(currentRegister);
                register.setValue(register.getValue() + 1);
                return 1;
            } else {
                throw new Exception("currentRegister is null " + sentence.toString());
            }

        }

        if (instruction.equals(Instruction.dec)) {
            if (registerHashMap.containsKey(currentRegister)) {
                Register register = registerHashMap.get(currentRegister);
                register.setValue(register.getValue() - 1);
                return 1;
            } else {
                throw new Exception("currentRegister is null " + sentence.toString());
            }
        }

        if (instruction.equals(Instruction.jnz)) {
            if (registerHashMap.containsKey(currentRegister)) {
                Register register = registerHashMap.get(currentRegister);
                if (register.getValue() != 0) {
                    return operatedInteger;
                } else {
                    return 1;
                }
            }else {
                throw new Exception("currentRegister is null " + sentence.toString());
            }
        }


        return 0;
    }

    public static Sentence makeSentence(String input) throws Exception {

        if (input.matches(MOV_REGISTER_INTEGER_REGEX)) {
            String[] params = input.split(" ");
            Integer value = Integer.valueOf(params[2]);
            return new Sentence(Instruction.mov, params[1], value);
        }

        if (input.matches(MOV_REGISTER_REGISTER_REGEX)) {
            String[] params = input.split(" ");
            return new Sentence(Instruction.mov, params[1], params[2]);
        }

        if (input.matches(JNZ_REGISTER_INTEGER_REGEX)) {
            String[] params = input.split(" ");
            Integer value = Integer.parseInt(params[2]);
            return new Sentence(Instruction.jnz, params[1], value);
        }

        if (input.matches(INC_REGISTER_REGEX)) {
            String[] params = input.split(" ");
            return new Sentence(Instruction.inc, params[1]);
        }

        if (input.matches(DEC_REGISTER_REGEX)) {
            String[] params = input.split(" ");
            return new Sentence(Instruction.dec, params[1]);
        }

        return null;


    }

}

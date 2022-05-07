public final class Sentence {
    private Instruction instruction;
    private String currentRegister;
    private String operatedRegister;
    private Integer operatedInteger;

    @Override
    public String toString() {
        return "Sentence{" +
                "instruction=" + instruction +
                ", currentRegister='" + currentRegister + '\'' +
                ", operatedRegister='" + operatedRegister + '\'' +
                ", operatedInteger=" + operatedInteger +
                '}';
    }

    public Sentence(Instruction instruction, Integer operatedInteger) {
        this.instruction = instruction;
        this.operatedInteger = operatedInteger;
    }

    public Sentence(Instruction instruction, String currentRegister, Integer operatedInteger) {
        this.instruction = instruction;
        this.currentRegister = currentRegister;
        this.operatedInteger = operatedInteger;
    }

    public Sentence(Instruction instruction, String currentRegister) {
        this.instruction = instruction;
        this.currentRegister = currentRegister;
    }

    public Sentence(Instruction instruction, String currentRegister, String operatedRegister) {
        this.instruction = instruction;
        this.currentRegister = currentRegister;
        this.operatedRegister = operatedRegister;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public String getCurrentRegister() {
        return currentRegister;
    }

    public void setCurrentRegister(String currentRegister) {
        this.currentRegister = currentRegister;
    }

    public String getOperatedRegister() {
        return operatedRegister;
    }

    public void setOperatedRegister(String operatedRegister) {
        this.operatedRegister = operatedRegister;
    }

    public Integer getOperatedInteger() {
        return operatedInteger;
    }

    public void setOperatedInteger(Integer operatedInteger) {
        this.operatedInteger = operatedInteger;
    }
}

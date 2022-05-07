public class Counter {

    private Integer registerCount;
    private Integer instructionCount;

    private Integer runCount;

    public void incRegisterCount(){
        this.registerCount++;
    }

    public void incInstructionCount(){
        this.instructionCount++;
    }

    public void incRunCount(){
        this.runCount++;
    }

    public void decRegisterCount(){
        this.registerCount--;
    }

    public void decInstructionCount(){
        this.instructionCount--;
    }

    public void decRunCount(){
        this.runCount--;
    }



    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public Integer getInstructionCount() {
        return instructionCount;
    }

    public void setInstructionCount(Integer instructionCount) {
        this.instructionCount = instructionCount;
    }

    public Integer getRunCount() {
        return runCount;
    }

    public void setRunCount(Integer runCount) {
        this.runCount = runCount;
    }

    private Counter() {
        this.registerCount = 0;
        this.instructionCount = 0;
        this.runCount = 0;
    }

    public static Counter counter = new Counter();

    public static Counter getCounter(){
        return counter;
    }

}

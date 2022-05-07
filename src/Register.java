public final class Register {
    private String name;
    private Integer value;

    @Override
    public String toString() {
        return "Register{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public Register(String name, Integer value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

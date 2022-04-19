package bean;
public class Currentnum {
    private String name;
    private long num;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"num\":")
                .append(num);
        sb.append('}');
        return sb.toString();
    }
}

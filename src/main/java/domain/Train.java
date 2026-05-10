package domain;

public class Train {
    private String id;
    private int totalCapacity;

    public Train(String id, int totalCapacity){
        this.id = id;
        this.totalCapacity = totalCapacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id='" + id + '\'' +
                ", totalCapacity=" + totalCapacity +
                '}';
    }
}



public class Vehicle {
    private String type;
    private int size;
    private int cost;
    private Date dateAvailable;
    public Vehicle(String type, int size, int cost, Date dateAvailable) {
        this.type = type;
        
        if (size == 2 || size == 5 || size == 7) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
        
        if (cost > 0) {
            this.cost = cost;
        } else {
            throw new IllegalArgumentException("Cost must be positive");
        }
        
        this.dateAvailable = dateAvailable;
    }

    public String getType(){
        return type;
    }

    public int getSize(){
        return size;
    }

    public int getCost(){
        return cost;
    }

    public Date getDateAvailable(){
        return dateAvailable;
    }

    //setters
    public void setDateAvailable(Date newDateAvailable) {
     
    }
    

    public void setSize(int size) {
        if (size == 2 || size == 5 || size == 7) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Invalid size: " + size);
        }
    }
    
    public void setCost(int cost) {
        if (cost > 0) {
            this.cost = cost;
        } else {
            throw new IllegalArgumentException("Cost must be positive");
        }
    }

    @Override
public String toString() {
    return "Vehicle{" +
           "type='" + type + '\'' +
           ", size=" + size +
           ", cost=" + cost +
           ", dateAvailable=" + dateAvailable +
           '}';
}

    
}


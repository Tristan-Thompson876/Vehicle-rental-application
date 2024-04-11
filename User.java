public class User {
    private String name;
    private String password;
    private int budget;
    
    public User(String name, String password, int budget){
        this.name = name;
        this.password = password;
        this.budget = budget;

    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public int getBudget(){
        return budget;
    }
    
    public void setBudget(int budget){
        this.budget = budget;
    }
}

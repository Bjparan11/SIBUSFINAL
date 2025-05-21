package dbconnect;


public class Session { 
    
    private static Session instance;
    private int iid;
    private String fname;
    private String lname;
    private String username;
    private String type;
    private String email;
    private String phone;
    private String status;

    
    private Session(){
    //Private cons. prevents instance
    }

    public static synchronized Session getInstance() {
        if(instance == null)
        {
            instance = new Session();
        }
        return instance;
    }

    public static boolean isInstanceEmpty() {
        return instance == null;
    }
    
        public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
        public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }
    
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
}

}

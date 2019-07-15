package np.edu.sg.practical7;

public class UserData {
    private String MyUserName;
    private String MyPassword;

    public UserData(){}

    public UserData(String myUserName,String Password){
        MyUserName = myUserName;
        MyPassword = Password;
    }

    public String getMyUserName(){
        return MyUserName;
    }
    public void setMyUserName(String myUserName){
        MyUserName = myUserName;
    }

    public String getMyPassword(){
        return MyPassword;
    }
    public void setMyPassword(String myPassword){
        MyPassword = myPassword;
    }
}

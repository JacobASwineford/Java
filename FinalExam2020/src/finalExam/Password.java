package finalExam;

/**
 *
 * @author cjones
 */
public class Password {
   public  String password;
   public  String hashValue; 
   
   public  Password(String password, String hash){
       this.password =password;
       this.hashValue = hash;
   }

}

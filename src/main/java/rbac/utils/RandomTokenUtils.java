package rbac.utils;

import java.util.Random;

/**
 * @author MiracloW
 */
public class RandomTokenUtils {
    
    private static Random random = new Random();

    private static final int BOUND = 100000;

    public static int createRandomRecToken(){
        return random.nextInt(BOUND);
    }
    
    public static int updateRandomRecToken(int originRectoken){
        int recToken = random.nextInt(BOUND);
        while(recToken == originRectoken)
            recToken = random.nextInt(BOUND);
        return recToken;
    }

    private RandomTokenUtils(){

    }
}

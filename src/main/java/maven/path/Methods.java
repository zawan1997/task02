package maven.path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Methods {

    public float avg(List<Integer> num){
        float sum = 0f;
        for(int i = 0; i< num.size(); i++){
            sum +=num.get(i);
        }
        return sum / num.size();
    }
    
}

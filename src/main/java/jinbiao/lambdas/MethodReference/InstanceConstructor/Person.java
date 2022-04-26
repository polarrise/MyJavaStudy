package jinbiao.lambdas.MethodReference.InstanceConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String name="张三";
    private int age=23;
    public Person(){

    }
    public Person( int x){
        this.age=x;
    }
    public Person( String x){
        this.name=x;
    }
}

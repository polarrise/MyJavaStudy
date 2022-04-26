package jinbiao.lambdas.MethodReference.InstanceConstructor;

@FunctionalInterface
public interface PersonBuilder {
    //根据传递的姓名，创建Perosn对象
    Person builderPerson(String name);
}

package jinbiao.lambdas.MethodReference.ArrayConstructor;

@FunctionalInterface
public interface ArrayBuilder {
    //创建int类型数组的方法，参数传递数组的长度，返回创建好的int类型数组
    int[] builderArray(int length);
}

package jinbiao.lambdas.MethodReference.thisReMethod;

/**
 * 通过this引用成员方法
 * this代表当前对象，如果需要引用的方法就是当前类中的成员方法，那么可以使用 this::成员方法的格式来使用方法引用
 */
@FunctionalInterface
public interface MyWallet {
    void buy();
}

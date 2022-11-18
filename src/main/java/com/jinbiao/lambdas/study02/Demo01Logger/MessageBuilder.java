package com.jinbiao.lambdas.study02.Demo01Logger;

/**
 源码注释：该注解是定义一个lambda表达式的基础， 即是否是函数式接口可以标注也可以不标注。
 函数式接口必须有一个精确的抽象方法，但排除以下两种：
 ①java8的default，他们不属于抽象方法。
 ②如果该接口声明的一个抽象方法覆盖了任意一个object的方法，也排除掉。

 注意：@FuncationlInterface不能标注在注解、类以及枚举上。一旦使用该注解来定义接口，
 编译器将会强制检查该接口是否确实有且仅有一个抽象方法，否则将会报错。需要注意的是，即使不使用该注解，
 只要满足函数式接口的定义，这仍然是一个函数式接口，使用起来都一样。
 */
@FunctionalInterface
public interface MessageBuilder {
    //定义一个拼接消息的抽象方法,返回被拼接的消息
    public abstract String builderMessage();
}

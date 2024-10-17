package com.jinbiao.HelperClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public class LambdaUtil {

    private static final List<Product> productList;
    static {
        productList = new ArrayList<Product>() {
            {
                add(new Product("苹果", 5.2d, true));
                add(new Product("苹果", 8.0d,false));
                add(new Product("榴莲", 20.0d,true));
                add(new Product("榴莲", 25.0d,false));
                add(new Product( "橘子", 3.3d,true));
            }
        };
    }

    /**
     * 满足断言则消费
     * @param predicate
     * @param consumer
     */
    private static void matchThenCounsumer(Predicate<List<Product>> predicate, Consumer<List<Product>> consumer){
        if(predicate.test(productList)){
            consumer.accept(productList);
        }
    }

    private static void byCounsumer(Consumer<List<Product>> consumer){
        consumer.accept(productList);
    }

    private static Double bySupplier(Supplier<Double> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {

        // 满足断言则执行消费者,如果产品列表中存在销售中的产品,则把销售中的产品价格相加
        matchThenCounsumer(products -> products.stream()
                                               .filter(Product::getIsSelling)
                                               .count() > 1,
                           products -> {
                                Double price = products.stream()
                                                       .filter(Product::getIsSelling)
                                                       .map(Product::getPrice)
                                                       .reduce(0d, Double::sum);
                                log.info(String.valueOf(price));
                           }
        );

        byCounsumer(products->{
            if( products.stream().filter(Product::getIsSelling).count() > 1){
                Double price = products.stream()
                        .filter(Product::getIsSelling)
                        .map(Product::getPrice)
                        .reduce(0d, Double::sum);
                log.info(String.valueOf(price));
            }
        });

        Double v = bySupplier(() -> {
            return productList.stream().filter(Product::getIsSelling).count() > 1 ?
                    productList.stream()
                            .filter(Product::getIsSelling)
                            .map(Product::getPrice)
                            .reduce(0d, Double::sum) : 0d;
        });
        log.info(String.valueOf(v));

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Accessors(chain = true)
    static class Product{
        private String name;
        private Double price;
        private Boolean isSelling;
    }
}

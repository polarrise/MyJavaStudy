package com.jinbiao.HelperClass.blindBoxActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 需求:现在我们要来设计一个抽奖功能，要求可以设置礼物，并能设置每种礼物的概率。
 */
public class DrawLotteryService {
  /**
   * 思路:
   * 将奖品按集合中顺序概率计算成所占比例区间，放入比例集合。并产生一个随机数加入其中，排序。排序后，随机数落在哪个区间，就表示那个区间的奖品被抽中。
   * 返回的随机数在集合中的索引，该索引就是奖品集合中的索引。比例区间的计算通过概率相加获得。
   * 如上图：假设抽中苹果的概率为0.2，香蕉的概率为0.3，西瓜的概率为0.5。我们把它们做成一个数组按概率从小到大排列。然后生成一个0-1的随机数，如果落到哪里，对应的就是奖品。

   * @param giftList
   * @return
   */
  public static int drawGift(List<Gift> giftList) {
    if (null != giftList && giftList.size() > 0) {
      List<Double> orgProbList = new ArrayList<Double>(giftList.size());  //礼物概率的集合
      for (Gift gift : giftList) {
        // 按顺序将概率添加到集合中
        orgProbList.add(gift.getProb());
      }
      return draw(orgProbList);
    }
    return -1;
  }

  public static int draw(List<Double> giftProbList) {
    List<Double> sortRateList = new ArrayList<>();      // 计算概率总和
    Double sumRate = 0D;
    for (Double prob : giftProbList) {
      sumRate += prob;
    }
    if (sumRate != 0) {
      double rate = 0D; // 概率所占比例
      for (Double prob : giftProbList) {
        rate += prob;            //构建一个比例区段组成的集合(避免概率和不为1)
        sortRateList.add(rate / sumRate);
      }
      // 随机生成一个随机数，并排序  double random = Math.random();
      ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
      double random = threadLocalRandom.nextDouble(0, 1);
      sortRateList.add(random);
      Collections.sort(sortRateList);         // 返回该随机数在比例集合中的索引
      return sortRateList.indexOf(random);
    }
    return -1;
  }

  /**
   * 盲盒奖品：未中奖（概率10%）、
   * 5元无门槛券（概率10%）、
   * 20元优惠券（概率5%）、
   * 50元优惠券（概率3%）、
   * 100元优惠券（概率2%）、
   * 200小法盾（概率40%）
   * 500小法盾（概率30%）
   */
  public static void main(String[] args) {
    Gift thanks = new Gift();
    thanks.setId(1);
    thanks.setName("未中奖");
    thanks.setProb(0.1D);

    Gift coupon_5 = new Gift();
    coupon_5.setId(2);
    coupon_5.setName("5元无门槛券");
    coupon_5.setProb(0.1D);

    Gift coupon_20 = new Gift();
    coupon_20.setId(3);
    coupon_20.setName("20元优惠券");
    coupon_20.setProb(0.05D);

    Gift coupon_50 = new Gift();
    coupon_50.setId(4);
    coupon_50.setName("50元优惠券");
    coupon_50.setProb(0.03D);

    Gift coupon_100 = new Gift();
    coupon_100.setId(5);
    coupon_100.setName("100元优惠券");
    coupon_100.setProb(0.02D);

    Gift integral_200 = new Gift();
    integral_200.setId(6);
    integral_200.setName("200小法盾");
    integral_200.setProb(0.4D);

    Gift integral_500 = new Gift();
    integral_500.setId(7);
    integral_500.setName("500小法盾");
    integral_500.setProb(0.3D);

    List<Gift> list = new ArrayList<Gift>();
    list.add(thanks);
    list.add(coupon_5);
    list.add(coupon_20);
    list.add(coupon_50);
    list.add(coupon_100);
    list.add(integral_200);
    list.add(integral_500);
    int index = drawGift(list);  //传入礼物集合--得到抽到的那个礼物的索引所在位置

    System.out.println(list.get(index));}
}
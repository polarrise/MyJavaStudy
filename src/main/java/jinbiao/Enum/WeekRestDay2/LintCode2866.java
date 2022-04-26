package jinbiao.Enum.WeekRestDay2;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;


/**
 * @author jinbiao
 * @date 2022/1/11
 * @apiNote
 */
public class LintCode2866 {
    public static EnumMap<WeekDay, String> getDays() {
        WeekDay[] wd = WeekDay.values();
        EnumMap<WeekDay, String> em=new EnumMap(WeekDay.class);
        Arrays.stream(wd).forEach(a->{
            switch(a){
                case SUN:
                case SAT: em.put(a,"rest"); break;
                case MON:
                case TUE:
                case WED:
                case THU:
                case FRI: em.put(a,"work"); break;
            }
        });
        return em;
    }
    public void test(){

    }
    public static void main(String[] args) {
        EnumMap<WeekDay, String> map = LintCode2866.getDays();
        for (Map.Entry<WeekDay, String> i : map.entrySet()) {
            System.out.println(i.getKey() + " is a " + i.getValue() + " day.");
        }


    }
}

package jinbiao.Enum.WeekRestDay3;

/**
 * @author jinbiao
 * @date 2022/1/11
 * @apiNote
 */
public enum LintCode2867 {
    SUN("Restday"), MON("Workday"), TUE("Workday"), WED("Workday"), THU("Workday"), FRI("Workday"), SAT("Restday");
    private final String day;

    LintCode2867(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public static LintCode2867[] getWorkDay() {
        LintCode2867[] wd = {LintCode2867.MON, LintCode2867.THU, LintCode2867.WED, LintCode2867.THU, LintCode2867.FRI};
        return wd;
    }
    public static LintCode2867[] getRestDay() {
        LintCode2867[] wd = {LintCode2867.SAT, LintCode2867.SUN};
        return wd;
    }
    public static void main(String[] args) throws Exception {
        LintCode2867[] work = LintCode2867.getWorkDay();
        LintCode2867[] rest = LintCode2867.getRestDay();

        StringBuilder sb = new StringBuilder();
        sb.append("WorkDay: \n");
        for (LintCode2867 d : work) {
            sb.append(d + " is a " + d.getDay() + ".\n");
        }
        sb.append("RestDay: \n");
        for (LintCode2867 d : rest) {
            sb.append(d + " is a " + d.getDay() + ".\n");
        }
        System.out.print(sb.toString());
    }
}

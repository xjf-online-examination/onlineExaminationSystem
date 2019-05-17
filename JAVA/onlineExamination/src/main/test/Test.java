/**
 * <p>Title: Test</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-15 22:06
 */
public class Test {

    @org.junit.Test
    public void test1() {
        System.out.println();

        String str;
        int intFlag = (int)(Math.random() * 100000);

        String flag = String.valueOf(intFlag);
        if (flag.length() == 5 && flag.substring(0, 1).equals("9"))
        {
            str = intFlag + "" + System.currentTimeMillis();
        } else
        {
            intFlag = intFlag + 10000;
            str = intFlag + "" + System.currentTimeMillis();
        }
        System.out.println(str);
    }
}
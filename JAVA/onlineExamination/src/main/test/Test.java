import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.PO.ExamQuestions;

import java.util.Arrays;

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

    private Float multiSelect(String studentAnswer, String standardAnswer, Integer score) {
        char[] studentAnswers = studentAnswer.toUpperCase().toCharArray();
        Arrays.sort(studentAnswers);
        char[] standardAnswers = standardAnswer.toUpperCase().toCharArray();
        Arrays.sort(standardAnswers);

        String stuAnswer = String.valueOf(studentAnswers);
        String standAnswer = String.valueOf(standardAnswers);
        if (stuAnswer.equals(standAnswer)) {
            return (float) score;
        } else if (this.find(standAnswer, stuAnswer)) {
            return (float) score/2;
        } else {
            return 0f;
        }
    }

    @org.junit.Test
    public void test2() {
        float ss = this.multiSelect("DB", "ABD", 2);
        System.out.println(ss);
    }

    private boolean find(String source, String target) {
        char[] c1 = source.toCharArray();
        char[] c2 = target.toCharArray();
        int i = 0;
        int j;
        while (i < source.length() - 1) {
            j = 0;
            while (c1[i] == c2[j] && j < target.length() - 1) {
                i++;
                j++;
            }
            if (j == target.length() - 1) {
//                return i - target.length() + 1;
                return true;
            }
            i = i - j + 1;

        }
        return false;
    }

    @org.junit.Test
    public void test3() {
        System.out.println(this.find("abd", "dc"));
    }

    @org.junit.Test
    public void test4() {
        ExamQuestionsSaveDTO examQuestionsSaveDTO = new ExamQuestionsSaveDTO();
        examQuestionsSaveDTO.setOptionA("A");
        examQuestionsSaveDTO.setOptionB("B");
        examQuestionsSaveDTO.setOptionC("C");
        examQuestionsSaveDTO.setOptionD("D");
        examQuestionsSaveDTO.setOptionE("E");

        ExamQuestions examQuestions = new ExamQuestions();
        examQuestions.setABCD(examQuestionsSaveDTO);

        System.out.println(examQuestions.toString());
    }

    @org.junit.Test
    public void test5() {

    }
}
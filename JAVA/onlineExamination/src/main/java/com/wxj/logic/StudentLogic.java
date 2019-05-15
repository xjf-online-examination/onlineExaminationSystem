package com.wxj.logic;

import com.wxj.constant.SystemConstant;
import com.wxj.model.PO.Achievement;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * <p>Title: StudentLogic</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-12 18:34
 */
@Component
public class StudentLogic {
    /**
     * 判断这份考卷是否批完 true：没批完 false：批完
     * @param achievementList
     * @return
     */
    public boolean noScore(Collection<Achievement> achievementList) {
        Iterator it = achievementList.iterator();
        while (it.hasNext()) {
            Achievement achievement = (Achievement) it.next();
            if (SystemConstant.NOUGHT == achievement.getFinisFlag()) {
                return true;
            }
        }
        return false;
    }

    public Float countScore(Collection<Achievement> achievementList) {
        Iterator it = achievementList.iterator();
        Float score = 0f;
        while (it.hasNext()) {
            Achievement achievement = (Achievement) it.next();
            score += achievement.getScore();
        }
        return score;
    }
}
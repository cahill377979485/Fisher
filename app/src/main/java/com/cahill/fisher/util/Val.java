package com.cahill.fisher.util;

import com.cahill.fisher.R;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/10/26 0026 下午 1:41
 * @desc
 */
public class Val {
    public static final float ITEM_HEIGHT = UIHelper.getPx(R.dimen.b180);
    public static final int TYPE_SSS = 3;
    public static final int TYPE_SS = 2;
    public static final int TYPE_S = 1;
    public static final int TYPE_NORMAL = 0;
    public static final int PRIORITY_NORMAL = 0;
    public static final int PRIORITY_LEVEL1 = 1;
    public static final int  [] PRIORITIES = {PRIORITY_NORMAL, PRIORITY_LEVEL1};//优先级：SSS+必选=2+3=5 ,SSS=2,SS+必选=1+3=4,SS=1,S+必选=0+3=3,S=0
    public static final String DATA = "DATA";
    public static final int [] TYPE_BGS = {R.mipmap.bg_normal, R.mipmap.bg_s, R.mipmap.bg_ss, R.mipmap.bg_sss};
}

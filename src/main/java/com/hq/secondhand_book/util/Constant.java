package com.hq.secondhand_book.util;


public class Constant {
    /**系统是否可用状态
     * USABLE-可用  UN_USABLE-不可用*/
    public static final Integer USABLE = 1;
    public static final Integer UN_USABLE = 0;

    /**订单状态
     * DELIVERY-发货中  RECEIVED-已收货 CANCEL-已取消*/
    public static final Integer DELIVERY = 0;
    public static final Integer RECEIVED = 1;
    public static final Integer CANCEL = 2;

    /**用户角色
     * MEMBER-普通会员  ADMIN-管理员 */
    public static final Integer MEMBER = 0;
    public static final Integer ADMIN = 1;

    /**用户角色
     * CANCELSTART-未收藏  START-收藏 */
    public static final Integer CANCELSTART = 0;
    public static final Integer START = 1;
}

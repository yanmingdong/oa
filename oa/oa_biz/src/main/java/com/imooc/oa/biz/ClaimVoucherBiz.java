package com.imooc.oa.biz;

import com.imooc.oa.entity.ClaimVoucher;
import com.imooc.oa.entity.ClaimVoucherItem;
import com.imooc.oa.entity.DealRecord;

import java.util.List;

public interface ClaimVoucherBiz {

    /**
     * 保存报销单
     * @param claimVoucher
     * @param claimVoucherItems
     */
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> claimVoucherItems);

    /**
     * 获取报销单
     * @param id
     * @return
     */
    ClaimVoucher get(int id);

    /**
     * 获取报销单的详细
     * @param cvid
     * @return
     */
    List<ClaimVoucherItem> getItems(int cvid);

    /**
     * 获取报销单的操作记录
     * @param cvid
     * @return
     */
    List<DealRecord> getRecords(int cvid);

    /**
     * 查询个人报销单
     * @param sn
     * @return
     */
    List<ClaimVoucher> getForSelf(String sn);

    /**
     * 查询个人待处理报销单
     * @param sn
     * @return
     */
    List<ClaimVoucher> getForDeal(String sn);

    /**
     * 修改报销单
     * @param claimVoucher
     * @param items
     */
    void update(ClaimVoucher claimVoucher,List<ClaimVoucherItem> items);

    /**
     * 提交报销单
     * @param id
     */
    void submit(int id);

    void deal(DealRecord dealRecord);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.fund.service;

import java.awt.print.Pageable;
import java.util.List;
import net.luoteng.fund.entity.FundRecord;
import net.luoteng.fund.enums.FundRecordOperation;
import net.luoteng.service.CrudService;
import org.springframework.data.domain.Page;

/**
 * fund record service
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
public interface FundRecordService extends CrudService<FundRecord> {
    
    /**
     * 根据用户获取资金记录
     * 
     * @param userId
     * @param operations
     * @param pageable
     * @return 
     */
    Page<FundRecord> listByUser(String userId, List<FundRecordOperation> operations, Pageable pageable);
    
}

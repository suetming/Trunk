/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.fund.service.impl;

import java.awt.print.Pageable;
import java.util.List;
import net.luoteng.fund.entity.FundRecord;
import net.luoteng.fund.enums.FundRecordOperation;
import net.luoteng.fund.service.FundRecordService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 */
@Component
public class FundRecordServiceImpl implements FundRecordService {

    @Override
    public Page<FundRecord> listByUser(String userId, List<FundRecordOperation> operations, Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FundRecord save(FundRecord t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FundRecord get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

package com.winter.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

public class Bill {
    private String billNum;

    private String payeeAccount;

    private String payeeName;

    private BigDecimal payeeSave;

    private BigDecimal payeeLoan;

    private BigDecimal payeeLoanRatio;

    private String drawerAccount;

    private String drawerName;

    private Date issuanceDate;

    private Date expiryDate;

    private BigDecimal billAmount;

    private String billState;

    private String billApprovalState;

    private String batchNum;

    private BigDecimal accountingChange;

    private String accountingState;

    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum == null ? null : billNum.trim();
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount == null ? null : payeeAccount.trim();
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    public BigDecimal getPayeeSave() {
        return payeeSave;
    }

    public void setPayeeSave(BigDecimal payeeSave) {
        this.payeeSave = payeeSave;
    }

    public BigDecimal getPayeeLoan() {
        return payeeLoan;
    }

    public void setPayeeLoan(BigDecimal payeeLoan) {
        this.payeeLoan = payeeLoan;
    }

    public BigDecimal getPayeeLoanRatio() {
        return payeeLoanRatio;
    }

    public void setPayeeLoanRatio(BigDecimal payeeLoanRatio) {
        this.payeeLoanRatio = payeeLoanRatio;
    }

    public String getDrawerAccount() {
        return drawerAccount;
    }

    public void setDrawerAccount(String drawerAccount) {
        this.drawerAccount = drawerAccount == null ? null : drawerAccount.trim();
    }

    public String getDrawerName() {
        return drawerName;
    }

    public void setDrawerName(String drawerName) {
        this.drawerName = drawerName == null ? null : drawerName.trim();
    }

    public Date getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState == null ? null : billState.trim();
    }

    public String getBillApprovalState() {
        return billApprovalState;
    }

    public void setBillApprovalState(String billApprovalState) {
        this.billApprovalState = billApprovalState == null ? null : billApprovalState.trim();
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    public BigDecimal getAccountingChange() {
        return accountingChange;
    }

    public void setAccountingChange(BigDecimal accountingChange) {
        this.accountingChange = accountingChange;
    }

    public String getAccountingState() {
        return accountingState;
    }

    public void setAccountingState(String accountingState) {
        this.accountingState = accountingState == null ? null : accountingState.trim();
    }
}
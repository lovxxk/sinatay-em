package cn.com.sinosoft.ebusiness.ali.ldc;

import java.io.Serializable;

public class LDCode extends LDCodeKey implements Serializable{
    private String codeName;

    private String codeEAlias;

    private String comCode;

    private String otherSign;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    public String getCodeEAlias() {
        return codeEAlias;
    }

    public void setCodeEAlias(String codeEAlias) {
        this.codeEAlias = codeEAlias == null ? null : codeEAlias.trim();
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode == null ? null : comCode.trim();
    }

    public String getOtherSign() {
        return otherSign;
    }

    public void setOtherSign(String otherSign) {
        this.otherSign = otherSign == null ? null : otherSign.trim();
    }
}
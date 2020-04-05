package com.sinosoft.business.po;

import java.io.Serializable;
import java.util.Date;

import com.sinosoft.po.BaseDbInfoPo;

public class PubZoncode extends BaseDbInfoPo implements Serializable {
    private String zonecode;

    private String cnname;

    private String enname;

    private Short zonelevel;

    private Short zonetype;

    private Short povertystatus;

    private Short locationtype;

    private String othercode;

    private String notes;

    private Date bdate;

    private Date edate;

    private String gbzonecode;

    private String customise;

    private String zonename;

    private Short years;

    private String border;
    private String xposition;
    private String yposition;

    private String minority;

    public String getXposition() {
		return xposition;
	}

	public void setXposition(String xposition) {
		this.xposition = xposition;
	}

	public String getYposition() {
		return yposition;
	}

	public void setYposition(String yposition) {
		this.yposition = yposition;
	}

	private static final long serialVersionUID = 1L;

    public String getZonecode() {
        return zonecode;
    }

    public void setZonecode(String zonecode) {
        this.zonecode = zonecode == null ? null : zonecode.trim();
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname == null ? null : cnname.trim();
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public Short getZonelevel() {
        return zonelevel;
    }

    public void setZonelevel(Short zonelevel) {
        this.zonelevel = zonelevel;
    }

    public Short getZonetype() {
        return zonetype;
    }

    public void setZonetype(Short zonetype) {
        this.zonetype = zonetype;
    }

    public Short getPovertystatus() {
        return povertystatus;
    }

    public void setPovertystatus(Short povertystatus) {
        this.povertystatus = povertystatus;
    }

    public Short getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(Short locationtype) {
        this.locationtype = locationtype;
    }

    public String getOthercode() {
        return othercode;
    }

    public void setOthercode(String othercode) {
        this.othercode = othercode == null ? null : othercode.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getGbzonecode() {
        return gbzonecode;
    }

    public void setGbzonecode(String gbzonecode) {
        this.gbzonecode = gbzonecode == null ? null : gbzonecode.trim();
    }

    public String getCustomise() {
        return customise;
    }

    public void setCustomise(String customise) {
        this.customise = customise == null ? null : customise.trim();
    }

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename == null ? null : zonename.trim();
    }

    public Short getYears() {
        return years;
    }

    public void setYears(Short years) {
        this.years = years;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border == null ? null : border.trim();
    }

    public String getMinority() {
        return minority;
    }

    public void setMinority(String minority) {
        this.minority = minority == null ? null : minority.trim();
    }
}
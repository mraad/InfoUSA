package com.esri;

import java.io.Serializable;

/**
 */
public class InfoUSA implements Serializable
{
    private static final long serialVersionUID = -80358541187703216L;

    public long objectId;
    public String name;
    public String addr;
    public String city;
    public String state;
    public String zip;
    public String sic;
    public String naicsExt;
    public long salesVol;
    public String hdbrch;
    public long numEmp;
    public String empSize;
    public String frnCode;
    public String isCode;
    public String sqft;
    public long subNum;
    public long ultNum;
    public String matchCode;
    public long locNum;
    public double y;
    public double x;

    public InfoUSA()
    {
    }

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(final String addr)
    {
        this.addr = addr;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(final String city)
    {
        this.city = city;
    }

    public String getEmpSize()
    {
        return empSize;
    }

    public void setEmpSize(final String empSize)
    {
        this.empSize = empSize;
    }

    public String getFrnCode()
    {
        return frnCode;
    }

    public void setFrnCode(final String frnCode)
    {
        this.frnCode = frnCode;
    }

    public String getHdbrch()
    {
        return hdbrch;
    }

    public void setHdbrch(final String hdbrch)
    {
        this.hdbrch = hdbrch;
    }

    public String getCode()
    {
        return isCode;
    }

    public void setCode(final String code)
    {
        isCode = code;
    }

    public double getY()
    {
        return y;
    }

    public void setY(final double y)
    {
        this.y = y;
    }

    public long getLocNum()
    {
        return locNum;
    }

    public void setLocNum(final long locNum)
    {
        this.locNum = locNum;
    }

    public double getX()
    {
        return x;
    }

    public void setX(final double x)
    {
        this.x = x;
    }

    public String getMatchCode()
    {
        return matchCode;
    }

    public void setMatchCode(final String matchCode)
    {
        this.matchCode = matchCode;
    }

    public String getNaicsExt()
    {
        return naicsExt;
    }

    public void setNaicsExt(final String naicsExt)
    {
        this.naicsExt = naicsExt;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public long getNumEmp()
    {
        return numEmp;
    }

    public void setNumEmp(final long numEmp)
    {
        this.numEmp = numEmp;
    }

    public long getObjectId()
    {
        return objectId;
    }

    public void setObjectId(final long objectId)
    {
        this.objectId = objectId;
    }

    public long getSalesVol()
    {
        return salesVol;
    }

    public void setSalesVol(final long salesVol)
    {
        this.salesVol = salesVol;
    }

    public String getSic()
    {
        return sic;
    }

    public void setSic(final String sic)
    {
        this.sic = sic;
    }

    public String getSqft()
    {
        return sqft;
    }

    public void setSqft(final String sqft)
    {
        this.sqft = sqft;
    }

    public String getState()
    {
        return state;
    }

    public void setState(final String state)
    {
        this.state = state;
    }

    public long getSubNum()
    {
        return subNum;
    }

    public void setSubNum(final long subNum)
    {
        this.subNum = subNum;
    }

    public long getUltNum()
    {
        return ultNum;
    }

    public void setUltNum(final long ultNum)
    {
        this.ultNum = ultNum;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(final String zip)
    {
        this.zip = zip;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof InfoUSA))
        {
            return false;
        }

        final InfoUSA infoUSA = (InfoUSA) o;

        if (objectId != infoUSA.objectId)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return (int) (objectId ^ (objectId >>> 32));
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("InfoUSA");
        sb.append("{addr='").append(addr).append('\'');
        sb.append(", objectId=").append(objectId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zip='").append(zip).append('\'');
        sb.append(", sic='").append(sic).append('\'');
        sb.append(", naicsExt='").append(naicsExt).append('\'');
        sb.append(", salesVol=").append(salesVol);
        sb.append(", hdbrch='").append(hdbrch).append('\'');
        sb.append(", numEmp=").append(numEmp);
        sb.append(", empSize='").append(empSize).append('\'');
        sb.append(", frnCode='").append(frnCode).append('\'');
        sb.append(", isCode='").append(isCode).append('\'');
        sb.append(", sqft='").append(sqft).append('\'');
        sb.append(", subNum=").append(subNum);
        sb.append(", ultNum=").append(ultNum);
        sb.append(", matchCode='").append(matchCode).append('\'');
        sb.append(", locNum=").append(locNum);
        sb.append(", y=").append(y);
        sb.append(", x=").append(x);
        sb.append('}');
        return sb.toString();
    }


}

package ua.pp.myshko.coefcalculator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author M. Chernenko
 */
@XmlRootElement(name = "params")
public class Params {

    private int v2;
    private int v3;
    private int v4;

    public int getV2() {
        return v2;
    }

    @XmlElement
    public void setV2(int v2) {
        this.v2 = v2;
    }

    public int getV3() {
        return v3;
    }

    @XmlElement
    public void setV3(int v3) {
        this.v3 = v3;
    }

    public int getV4() {
        return v4;
    }

    @XmlElement
    public void setV4(int v4) {
        this.v4 = v4;
    }
}

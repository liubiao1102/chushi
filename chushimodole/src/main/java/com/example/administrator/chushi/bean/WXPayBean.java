package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/10/16.
 */

public class WXPayBean {

    /**
     * code : 200
     * datas : {"pay_price":"5051.00","appid":"wx76c60c8c929e5061","sign":"B694D4806017DF107616B18A8A75CBDC","package1":"Sign=WXPay","partnerid":"1489640482","prepayid":"wx201710161503119c358630910890005266","noncestr":"xhl5xnwbq06vdvwq8q7elnzyr118m7tq","timestamp":"1508137392"}
     */
    private int code;
    private DatasEntity datas;

    public void setCode(int code) {
        this.code = code;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public int getCode() {
        return code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * pay_price : 5051.00
         * appid : wx76c60c8c929e5061
         * sign : B694D4806017DF107616B18A8A75CBDC
         * package1 : Sign=WXPay
         * partnerid : 1489640482
         * prepayid : wx201710161503119c358630910890005266
         * noncestr : xhl5xnwbq06vdvwq8q7elnzyr118m7tq
         * timestamp : 1508137392
         */
        private String pay_price;
        private String appid;
        private String sign;
        private String package1;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public void setPackage1(String package1) {
            this.package1 = package1;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPay_price() {
            return pay_price;
        }

        public String getAppid() {
            return appid;
        }

        public String getSign() {
            return sign;
        }

        public String getPackage1() {
            return package1;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }
    }
}

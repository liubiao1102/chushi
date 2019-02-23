package com.example.administrator.chushi.bean;

/**
 * Created by Administrator on 2017/10/12.
 */

public class AliPayBean {


    /**
     * code : 200
     * datas : {"orderStr":"app_id=2017091508739592&biz_content=%7B%22body%22%3A%22ali+qr+pay%22%2C%22subject%22%3A%22fdsafdg%22%2C%22out_trade_no%22%3A%2220171011185227%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A%221%22%2C%22passback_params%22%3A%22123123%22%2C%22store_id%22%3A%221%22%2C%22timeout_express%22%3A%229m%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F114.215.83.139%2Fcfc%2Fapi.php%2FPayment%2Fparam.php&sign_type=RSA2×tamp=2017-10-16+14%3A55%3A38&version=1.0&sign=crTpyqAbrsk7Hl52D6e2ild4ibe%2BJ9oIGWuVLDNoKaIa58VOa0IjZocFioE6tSEuHrp7JXWH7glYcXz4JOSvB98i2Lvfz%2FA82TWGKNWwrTqoPRXSc2589tLZg%2BqKm9TXviouentykIAHMWBC9vNxd9A5o3G8fXAWR0jOVLOiplGIEBc2Ju6Sp2hOQKeut0fi3WjJVR2BZZyCTY1vLPjvRa8I7zt7nXQ9B2MMqxW0%2FMB%2FOauhFLV27TcmAiUt6s7WwyZ8Zzrrq%2Bm6LfJkXFPynjb%2BmGc08pDcY8KeDNWvwQ%2BvUANp4VNcGZgn45EAhCkxetpqJYunrfAdZ3ahvUfdXg%3D%3D","pay_price":"5051.00"}
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
         * orderStr : app_id=2017091508739592&biz_content=%7B%22body%22%3A%22ali+qr+pay%22%2C%22subject%22%3A%22fdsafdg%22%2C%22out_trade_no%22%3A%2220171011185227%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A%221%22%2C%22passback_params%22%3A%22123123%22%2C%22store_id%22%3A%221%22%2C%22timeout_express%22%3A%229m%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F114.215.83.139%2Fcfc%2Fapi.php%2FPayment%2Fparam.php&sign_type=RSA2×tamp=2017-10-16+14%3A55%3A38&version=1.0&sign=crTpyqAbrsk7Hl52D6e2ild4ibe%2BJ9oIGWuVLDNoKaIa58VOa0IjZocFioE6tSEuHrp7JXWH7glYcXz4JOSvB98i2Lvfz%2FA82TWGKNWwrTqoPRXSc2589tLZg%2BqKm9TXviouentykIAHMWBC9vNxd9A5o3G8fXAWR0jOVLOiplGIEBc2Ju6Sp2hOQKeut0fi3WjJVR2BZZyCTY1vLPjvRa8I7zt7nXQ9B2MMqxW0%2FMB%2FOauhFLV27TcmAiUt6s7WwyZ8Zzrrq%2Bm6LfJkXFPynjb%2BmGc08pDcY8KeDNWvwQ%2BvUANp4VNcGZgn45EAhCkxetpqJYunrfAdZ3ahvUfdXg%3D%3D
         * pay_price : 5051.00
         */
        private String orderStr;
        private String pay_price;

        public void setOrderStr(String orderStr) {
            this.orderStr = orderStr;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public String getOrderStr() {
            return orderStr;
        }

        public String getPay_price() {
            return pay_price;
        }
    }
}

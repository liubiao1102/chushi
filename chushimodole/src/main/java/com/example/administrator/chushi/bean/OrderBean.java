package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

public class OrderBean {

    /**
     * msg : 成功
     * code : 200
     * data : {"address":{"area":"太原市，辖小店区，测试小区1","address":"测试地址666","v_id":"17010101","x_id":"170101","mobile":"18710010271","c_id":"1701","id":"5","status":"1","username":"孤雪狼丶沐风"},"ticked":{"ticket_user_id":"4","money":"16","ticket_id":"1","title":"全民红包"},"price":{"total_price":"156","youhui_price":0,"yun_price":"8.00","kou_price":"20","order_price":"0.1","order_id":"382","point_price":"143.9","point":14390},"peisong_time":[{"time":[{"start_time":"13:00","end_time":"16:00"}],"day":"2017-09-21"},{"time":[{"start_time":"10:00","end_time":"12:00"},{"start_time":"13:00","end_time":"16:00"}],"day":"2017-09-22"}]}
     */
    private String msg;
    private int code;
    private DataEntity data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * address : {"area":"太原市，辖小店区，测试小区1","address":"测试地址666","v_id":"17010101","x_id":"170101","mobile":"18710010271","c_id":"1701","id":"5","status":"1","username":"孤雪狼丶沐风"}
        * ticked : {"ticket_user_id":"4","money":"16","ticket_id":"1","title":"全民红包"}
        * price : {"total_price":"156","youhui_price":0,"yun_price":"8.00","kou_price":"20","order_price":"0.1","order_id":"382","point_price":"143.9","point":14390}
        * peisong_time : [{"time":[{"start_time":"13:00","end_time":"16:00"}],"day":"2017-09-21"},{"time":[{"start_time":"10:00","end_time":"12:00"},{"start_time":"13:00","end_time":"16:00"}],"day":"2017-09-22"}]
                */
        private AddressEntity address;
        private TickedEntity ticked;
        private PriceEntity price;
        private List<PeisongTimeEntity> peisong_time;
        private GiftBean gift;
        private String check_activity;

        public String getCheck_activity() {
            return check_activity;
        }

        public void setCheck_activity(String check_activity) {
            this.check_activity = check_activity;
        }

        public GiftBean getGift() {
            return gift;
        }

        public void setGift(GiftBean gift) {
            this.gift = gift;
        }
        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public void setTicked(TickedEntity ticked) {
            this.ticked = ticked;
        }

        public void setPrice(PriceEntity price) {
            this.price = price;
        }

        public void setPeisong_time(List<PeisongTimeEntity> peisong_time) {
            this.peisong_time = peisong_time;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public TickedEntity getTicked() {
            return ticked;
        }

        public PriceEntity getPrice() {
            return price;
        }

        public List<PeisongTimeEntity> getPeisong_time() {
            return peisong_time;
        }

        public static class GiftBean {
            /**
             * order_gift :
             * pro_gift :
             */

            private String order_gift;
            private String pro_gift;

            public String getOrder_gift() {
                return order_gift;
            }

            public void setOrder_gift(String order_gift) {
                this.order_gift = order_gift;
            }

            public String getPro_gift() {
                return pro_gift;
            }

            public void setPro_gift(String pro_gift) {
                this.pro_gift = pro_gift;
            }
        }

        public static class AddressEntity {
            /**
             * area : 太原市，辖小店区，测试小区1
             * address : 测试地址666
             * v_id : 17010101
             * x_id : 170101
             * mobile : 18710010271
             * c_id : 1701
             * id : 5
             * status : 1
             * username : 孤雪狼丶沐风
             */
            private String area;
            private String address;
            private String v_id;
            private String x_id;
            private String mobile;
            private String c_id;
            private String id;
            private String status;
            private String username;

            public void setArea(String area) {
                this.area = area;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setV_id(String v_id) {
                this.v_id = v_id;
            }

            public void setX_id(String x_id) {
                this.x_id = x_id;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getArea() {
                return area;
            }

            public String getAddress() {
                return address;
            }

            public String getV_id() {
                return v_id;
            }

            public String getX_id() {
                return x_id;
            }

            public String getMobile() {
                return mobile;
            }

            public String getC_id() {
                return c_id;
            }

            public String getId() {
                return id;
            }

            public String getStatus() {
                return status;
            }

            public String getUsername() {
                return username;
            }
        }

        public static class TickedEntity {
            /**
             * ticket_user_id : 4
             * money : 16
             * ticket_id : 1
             * title : 全民红包
             */
            private String ticket_user_id;
            private String money;
            private String ticket_id;
            private String title;
            private String aaa;

            public void setAaa(String aaa) {
                this.aaa = aaa;
            }

            public String getAaa() {
                return aaa;
            }

            public void setTicket_user_id(String ticket_user_id) {
                this.ticket_user_id = ticket_user_id;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public void setTicket_id(String ticket_id) {
                this.ticket_id = ticket_id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTicket_user_id() {
                return ticket_user_id;
            }

            public String getMoney() {
                return money;
            }

            public String getTicket_id() {
                return ticket_id;
            }

            public String getTitle() {
                return title;
            }
        }

        public static class PriceEntity {
            /**
             * total_price : 156
             * youhui_price : 0
             * yun_price : 8.00
             * kou_price : 20
             * order_price : 0.1
             * order_id : 382
             * point_price : 143.9
             * point : 14390
             */
            private String total_price;
            private int youhui_price;
            private String yun_price;
            private String kou_price;
            private String order_price;
            private String order_id;
            private String point_price;
            private String point;

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public void setYouhui_price(int youhui_price) {
                this.youhui_price = youhui_price;
            }

            public void setYun_price(String yun_price) {
                this.yun_price = yun_price;
            }

            public void setKou_price(String kou_price) {
                this.kou_price = kou_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public void setPoint_price(String point_price) {
                this.point_price = point_price;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getTotal_price() {
                return total_price;
            }

            public int getYouhui_price() {
                return youhui_price;
            }

            public String getYun_price() {
                return yun_price;
            }

            public String getKou_price() {
                return kou_price;
            }

            public String getOrder_price() {
                return order_price;
            }

            public String getOrder_id() {
                return order_id;
            }

            public String getPoint_price() {
                return point_price;
            }

            public String getPoint() {
                return point;
            }
        }

        public static class PeisongTimeEntity {
            /**
             * time : [{"start_time":"13:00","end_time":"16:00"}]
             * day : 2017-09-21
             */
            private List<TimeEntity> time;
            private String day;


            public void setTime(List<TimeEntity> time) {
                this.time = time;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public List<TimeEntity> getTime() {
                return time;
            }

            public String getDay() {
                return day;
            }

            public static class TimeEntity {
                /**
                 * start_time : 13:00
                 * end_time : 16:00
                 */
                private String start_time;
                private String end_time;
                private String id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public String getStart_time() {
                    return start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }
            }
        }
    }
}

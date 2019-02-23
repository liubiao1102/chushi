package com.example.administrator.chushi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */

public class AllCityBean {
    /**
     * child : [{"child":[{"id":"17010101","name":"测试小区1"}],"id":"170101","name":"辖小店区"},{"child":[{"id":"","name":""}],"id":"170102","name":" 迎泽区"},{"child":[{"id":"","name":""}],"id":"170103","name":"杏花岭区"},{"child":[{"id":"","name":""}],"id":"170104","name":"尖草坪区"},{"child":[{"id":"","name":""}],"id":"170105","name":"万柏林区"},{"child":[{"id":"","name":""}],"id":"170106","name":"晋源区"},{"child":[{"id":"","name":""}],"id":"170107","name":"古交市"},{"child":[{"id":"","name":""}],"id":"170108","name":"清徐县"},{"child":[{"id":"","name":""}],"id":"170109","name":"阳曲县"},{"child":[{"id":"","name":""}],"id":"170110","name":"娄烦县"}]
     * id : 1701
     * name : 太原市
     */

    private String id;
    private String name;
    private List<ChildBeanX> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildBeanX> getChild() {
        return child;
    }

    public void setChild(List<ChildBeanX> child) {
        this.child = child;
    }

    public static class ChildBeanX {
        /**
         * child : [{"id":"17010101","name":"测试小区1"}]
         * id : 170101
         * name : 辖小店区
         */

        private String id;
        private String name;
        private List<ChildBean> child;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * id : 17010101
             * name : 测试小区1
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }


//    /**
//     * id : 1
//     * name : 北京市
//     * child : [{"id":"101","name":"东城区","child":[{"id":"17010103","name":"东直门"}]},{"id":"102","name":"西城区","child":{"id":"","name":""}},{"id":"103","name":"崇文区","child":{"id":"","name":""}},{"id":"104","name":"宣武区","child":{"id":"","name":""}},{"id":"105","name":"朝阳区","child":{"id":"","name":""}},{"id":"106","name":"海淀区","child":{"id":"","name":""}},{"id":"107","name":"丰台区","child":{"id":"","name":""}},{"id":"108","name":"石景山区","child":{"id":"","name":""}},{"id":"109","name":"门头沟区","child":{"id":"","name":""}},{"id":"110","name":"房山区","child":{"id":"","name":""}},{"id":"111","name":"通州区","child":{"id":"","name":""}},{"id":"112","name":"顺义区","child":{"id":"","name":""}},{"id":"113","name":"昌平区","child":{"id":"","name":""}},{"id":"114","name":"大兴区","child":{"id":"","name":""}},{"id":"115","name":"平谷县","child":{"id":"","name":""}},{"id":"116","name":"怀柔县","child":{"id":"","name":""}},{"id":"117","name":"密云县","child":{"id":"","name":""}},{"id":"118","name":"延庆县","child":{"id":"","name":""}}]
//     */
//
//    private String id;
//    private String name;
//    private List<ChildBeanX> child;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<ChildBeanX> getChild() {
//        return child;
//    }
//
//    public void setChild(List<ChildBeanX> child) {
//        this.child = child;
//    }
//
//    public static class ChildBeanX {
//        /**
//         * id : 101
//         * name : 东城区
//         * child : [{"id":"17010103","name":"东直门"}]
//         */
//
//        private String id;
//        private String name;
//        private List<ChildBean> child;
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public List<ChildBean> getChild() {
//            return child;
//        }
//
//        public void setChild(List<ChildBean> child) {
//            this.child = child;
//        }
//
//        public static class ChildBean {
//            /**
//             * id : 17010103
//             * name : 东直门
//             */
//
//            private String id;
//            private String name;
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//        }
//    }



}

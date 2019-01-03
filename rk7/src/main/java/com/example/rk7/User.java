package com.example.rk7;

import java.util.List;

public class User {
        public int code;
        public Data datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getDatas() {
        return datas;
    }

    public void setDatas(Data datas) {
        this.datas = datas;
    }

    public class Data{
            public List<Car> cart_list;
            public String sum;
            public int cart_count;

        public List<Car> getCart_list() {
            return cart_list;
        }

        public void setCart_list(List<Car> cart_list) {
            this.cart_list = cart_list;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public int getCart_count() {
            return cart_count;
        }

        public void setCart_count(int cart_count) {
            this.cart_count = cart_count;
        }

        public class Car{
                public String goods_name;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }
        }
        }
}

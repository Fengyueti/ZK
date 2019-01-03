package com.example.lenovo.fengyue0102.entity;

import java.util.List;

public class User {
    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        public TJ tuijian;

        public TJ getTuijian() {
            return tuijian;
        }

        public void setTuijian(TJ tuijian) {
            this.tuijian = tuijian;
        }

        public class TJ{
            public List<LL> list;

            public List<LL> getList() {
                return list;
            }

            public void setList(List<LL> list) {
                this.list = list;
            }

            public class LL{
                public int pid;
                public String title;
                public double price;
                public String images;

                public int getPid() {
                    return pid;
                }

                public void setPid(int pid) {
                    this.pid = pid;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }
            }
        }
    }
}

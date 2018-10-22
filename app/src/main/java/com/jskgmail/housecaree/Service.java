package com.jskgmail.housecaree;

public class Service {
    public String ServiceName;
    public int image;

    public Service() {

    }

    public Service(String serviceName) {
        ServiceName = serviceName;
    }

    public Service(String serviceName, int image) {
        ServiceName = serviceName;
        this.image = image;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

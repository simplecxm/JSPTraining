package com.simple.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simple.common.ServerResponse;
import com.simple.dao.OrderMapper;
import com.simple.pojo.Order;
import com.simple.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by S I M P L E on 2018/01/04
 */
@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    public ServerResponse<Order> createOrder(String orderNo, String userId, String shippingId, String payment, int paymentType, String postage, int status) {
        int resultCount = orderMapper.createOrder(orderNo, userId, shippingId, payment, paymentType, postage, status);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("创建订单成功");
        }
        return ServerResponse.createByErrorMessage("订单生成失败");
    }

    public ServerResponse<PageInfo> getOrderList(String userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.getOrderList(userId);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
        if (orderPageInfo.getSize() > 0) {
            return ServerResponse.createBySuccess("查询到该用户的订单", orderPageInfo);
        }
        return ServerResponse.createByErrorMessage("该用户没有任何订单");
    }

}
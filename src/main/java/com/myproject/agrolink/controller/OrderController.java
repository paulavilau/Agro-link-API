package com.myproject.agrolink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.agrolink.entity.FundsDistrib;
import com.myproject.agrolink.entity.Order;
import com.myproject.agrolink.entity.OrderItem;
import com.myproject.agrolink.entity.OrderStatus;
import com.myproject.agrolink.requestmodel.FundsDistribRequest;
import com.myproject.agrolink.requestmodel.ModifyOrderRequest;
import com.myproject.agrolink.requestmodel.ModifyOrderStatusRequest;
import com.myproject.agrolink.requestmodel.OrderItemRequest;
import com.myproject.agrolink.requestmodel.OrderRequest;
import com.myproject.agrolink.requestmodel.OrderStatusNotifRequest;
import com.myproject.agrolink.requestmodel.OrderStatusRequest;
import com.myproject.agrolink.service.OrderService;

@RestController
@RequestMapping("/agrolink/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        Order order = orderService.addOrder(orderRequest);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/addOrderItem")
    public OrderItem addItemToOrder(@RequestBody OrderItemRequest orderItemRequest) throws Exception {
        return orderService.addOrderItem(orderItemRequest);
    }

    @PostMapping("/secure/addOrderStatus")
    public OrderStatus addNewOrderStatus(@RequestBody OrderStatusRequest orderStatusRequest) throws Exception {
        return orderService.addOrderStatus(orderStatusRequest);
    }

    @GetMapping("/secure/findOrderStatus")
    public OrderStatus findResponse(@RequestParam Integer orderId, @RequestParam Integer farmId) throws Exception {
        return orderService.findOrderStatusByOrderIdAndFarmId(orderId, farmId);
    }

    @PatchMapping("/modifyOrderStatus")
    public ResponseEntity<String> setStatus(@RequestBody ModifyOrderStatusRequest modifyOrderStatusRequest) throws Exception {
        orderService.modifyOrderStatus(modifyOrderStatusRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

     @PutMapping("/modifyOrder")
    public ResponseEntity<String> setOrder(@RequestBody ModifyOrderRequest modifyOrderRequest) throws Exception {
        orderService.modifyOrder(modifyOrderRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addOrderStatusNotif")
    public ResponseEntity<String> addNotif(@RequestBody OrderStatusNotifRequest orderStatusNotifRequest)
            throws Exception {
        orderService.addOrderStatusNotif(orderStatusNotifRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/secure/distributeFunds")
    public void distribFunds(@RequestBody FundsDistribRequest fundsDistribRequest) throws Exception {
        orderService.addFundsDistrib(fundsDistribRequest);
    }

    @GetMapping("/isFundsDistrib")
    public ResponseEntity<String> isFundsDistrib(@RequestParam Integer orderStatusId) throws Exception {
        orderService.isFundsDistrib(orderStatusId);

        return new ResponseEntity<>(HttpStatus.OK); 
    }

    @GetMapping("/findFundsDistrib")
    public FundsDistrib findFundsDistrib(@RequestParam Integer orderStatusId) throws Exception {
        return orderService.findFundsDistrib(orderStatusId);
    }
}